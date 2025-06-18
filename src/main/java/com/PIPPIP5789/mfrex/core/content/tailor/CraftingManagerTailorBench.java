package com.pippip5789.mfrex.core.content.tailor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import minefantasy.mfr.MineFantasyReforged;
import minefantasy.mfr.util.FileUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CraftingManagerTailorBench {
    public static final String RECIPE_FOLDER_PATH = "/recipes_mfr/loom_recipes/";
    public static final String CONFIG_RECIPE_DIRECTORY = "config/MineFantasyReforged/custom/recipes/loom_recipes/";
    private static final IForgeRegistry<TailorBenchRecipeBase> Tailor_BENCH_RECIPES = (new RegistryBuilder()).setName(new ResourceLocation("minefantasyreforged", "loom_recipes")).setType(TailorBenchRecipeBase.class).setMaxID(67108863).disableSaving().allowModification().create();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final TailorBenchRecipeFactory factory = new TailorBenchRecipeFactory();

    public CraftingManagerTailorBench() {
    }

    public static void init() {
    }

    public static Collection<TailorBenchRecipeBase> getRecipes() {
        return Tailor_BENCH_RECIPES.getValuesCollection();
    }

    public static void loadRecipes() {
        ModContainer modContainer = Loader.instance().activeModContainer();
        FileUtils.createCustomDataDirectory("config/MineFantasyReforged/custom/recipes/loom_recipes/");
        Loader.instance().getActiveModList().forEach((m) -> {
            CraftingHelper.loadFactories(m, "assets/" + m.getModId() + "/recipes_mfr/loom_recipes/", new CraftingHelper.FactoryLoader[]{CraftingHelper.CONDITIONS});
        });
        loadRecipes(modContainer, new File("config/MineFantasyReforged/custom/recipes/loom_recipes/"), "");
        Loader.instance().getActiveModList().forEach((m) -> {
            loadRecipes(m, m.getSource(), "assets/" + m.getModId() + "/recipes_mfr/loom_recipes/");
        });
        Loader.instance().setActiveModContainer(modContainer);
    }

    private static void loadRecipes(ModContainer mod, File source, String base) {
        JsonContext ctx = new JsonContext(mod.getModId());
        FileUtils.findFiles(source, base, (root) -> {
            return FileUtils.loadConstants(source, base, ctx);
        }, (root, file) -> {
            Path relative = root.relativize(file);
            if (relative.getNameCount() > 1) {
                String extension = FilenameUtils.getExtension(file.toString());
                if (!extension.equals("json")) {
                    return;
                }

                String modName = relative.getName(relative.getNameCount() - 2).toString();
                String fileName = FilenameUtils.removeExtension(relative.getFileName().toString());
                if (!Loader.isModLoaded(modName) || fileName.startsWith("_")) {
                    return;
                }

                Loader.instance().setActiveModContainer(mod);
                if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_")) {
                    return;
                }

                ResourceLocation key = new ResourceLocation(ctx.getModId(), fileName);
                BufferedReader reader = null;

                try {
                    reader = Files.newBufferedReader(file);
                    JsonObject json = (JsonObject)JsonUtils.fromJson(GSON, reader, JsonObject.class);
                    String type = ctx.appendModId(JsonUtils.getString(json, "type"));
                    if (Loader.isModLoaded(mod.getModId())) {
                        if (TailorBenchRecipeType.getByNameWithModId(type, mod.getModId()) != TailorBenchRecipeType.NONE) {
                            TailorBenchRecipeBase recipe = factory.parse(ctx, json);
                            if (CraftingHelper.processConditions(json, "conditions", ctx)) {
                                addRecipe(recipe, mod.getModId().equals("minefantasyreforged"), key);
                            }
                        } else {
                            MineFantasyReforged.LOG.info("Skipping recipe {} of type {} because it's not a MFR Tailor Bench recipe", key, type);
                        }
                    } else {
                        MineFantasyReforged.LOG.info("Skipping recipe {} of type {} because it the mod it depends on is not loaded", key, type);
                    }
                } catch (JsonParseException var17) {
                    JsonParseException e = var17;
                    MineFantasyReforged.LOG.error("Parsing error loading recipe {}", key, e);
                } catch (IOException var18) {
                    IOException ex = var18;
                    MineFantasyReforged.LOG.error("Couldn't read recipe {} from {}", key, file, ex);
                } finally {
                    IOUtils.closeQuietly(reader);
                }
            }

        });
    }

    public static void addRecipe(TailorBenchRecipeBase recipe, boolean checkForExistence, ResourceLocation key) {
        ItemStack itemStack = recipe.getTailorBenchRecipeOutput();
        //if (ConfigCrafting.isTailorBenchItemCraftable(itemStack)) {
            NonNullList<ItemStack> subItems = NonNullList.create();
            recipe.setRegistryName(key);
            itemStack.getItem().getSubItems(itemStack.getItem().getCreativeTab(), subItems);
            if (subItems.stream().anyMatch((s) -> {
                return recipe.getTailorBenchRecipeOutput().isItemEqual(s);
            }) && (!checkForExistence || !Tailor_BENCH_RECIPES.containsKey(recipe.getRegistryName()))) {
                Tailor_BENCH_RECIPES.register(recipe);
            }
        //}

    }

    public static TailorBenchRecipeBase findMatchingRecipe(ITailorBench TailorBench, TailorBenchCraftMatrix matrix, World world) {
        Iterator<TailorBenchRecipeBase> recipeIterator = getRecipes().iterator();
        TailorBenchRecipeBase TailorBenchRecipeBase = null;

        while(recipeIterator.hasNext()) {
            TailorBenchRecipeBase rec = (TailorBenchRecipeBase)recipeIterator.next();
            if (rec.matches(matrix, world)) {
                TailorBenchRecipeBase = rec;
                break;
            }
        }

        if (TailorBenchRecipeBase != null) {
            TailorBench.setProgressMax(TailorBenchRecipeBase.getCraftTime());
            return TailorBenchRecipeBase;
        } else {
            return null;
        }
    }

    public static TailorBenchRecipeBase getRecipeByName(String name, boolean isNullable) {
        ResourceLocation resourceLocation = new ResourceLocation("mfrex:" + name);
        if (!Tailor_BENCH_RECIPES.containsKey(resourceLocation) && !isNullable) {
            MineFantasyReforged.LOG.error("Loom Recipe Registry does not contain recipe: {}", name);
        }

        return (TailorBenchRecipeBase)Tailor_BENCH_RECIPES.getValue(resourceLocation);
    }

    public static List<TailorBenchRecipeBase> getRecipesByName(String... names) {
        List<TailorBenchRecipeBase> recipes = new ArrayList();
        String[] var2 = names;
        int var3 = names.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String name = var2[var4];
            recipes.add(getRecipeByName(name, false));
        }

        return recipes;
    }

    public static String getRecipeName(TailorBenchRecipeBase recipe) {
        ResourceLocation recipeLocation = Tailor_BENCH_RECIPES.getKey(recipe);
        return recipeLocation != null ? recipeLocation.getPath() : "";
    }

    public static TailorBenchRecipeBase getRecipeByResourceLocation(ResourceLocation resourceLocation) {
        return Tailor_BENCH_RECIPES.getValue(resourceLocation);
    }

}
