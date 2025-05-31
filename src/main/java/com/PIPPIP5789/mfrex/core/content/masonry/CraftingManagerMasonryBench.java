package com.pippip5789.mfrex.core.content.masonry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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

public class CraftingManagerMasonryBench {
    public static final String RECIPE_FOLDER_PATH = "/recipes_mfr/masonry_bench_recipes/";
    public static final String CONFIG_RECIPE_DIRECTORY = "config/MineFantasyReforged/custom/recipes/masonry_bench_recipes/";
    private static final IForgeRegistry<MasonryBenchRecipeBase> MASONRY_BENCH_RECIPES = (new RegistryBuilder()).setName(new ResourceLocation("minefantasyreforged", "masonry_bench_recipes")).setType(MasonryBenchRecipeBase.class).setMaxID(67108863).disableSaving().allowModification().create();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final MasonryBenchRecipeFactory factory = new MasonryBenchRecipeFactory();

    public CraftingManagerMasonryBench() {
    }

    public static void init() {
    }

    public static Collection<MasonryBenchRecipeBase> getRecipes() {
        return MASONRY_BENCH_RECIPES.getValuesCollection();
    }

    public static void loadRecipes() {
        ModContainer modContainer = Loader.instance().activeModContainer();
        FileUtils.createCustomDataDirectory("config/MineFantasyReforged/custom/recipes/masonry_bench_recipes/");
        Loader.instance().getActiveModList().forEach((m) -> {
            CraftingHelper.loadFactories(m, "assets/" + m.getModId() + "/recipes_mfr/masonry_bench_recipes/", new CraftingHelper.FactoryLoader[]{CraftingHelper.CONDITIONS});
        });
        loadRecipes(modContainer, new File("config/MineFantasyReforged/custom/recipes/masonry_bench_recipes/"), "");
        Loader.instance().getActiveModList().forEach((m) -> {
            loadRecipes(m, m.getSource(), "assets/" + m.getModId() + "/recipes_mfr/masonry_bench_recipes/");
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
                        if (MasonryBenchRecipeType.getByNameWithModId(type, mod.getModId()) != MasonryBenchRecipeType.NONE) {
                            MasonryBenchRecipeBase recipe = factory.parse(ctx, json);
                            if (CraftingHelper.processConditions(json, "conditions", ctx)) {
                                addRecipe(recipe, mod.getModId().equals("minefantasyreforged"), key);
                            }
                        } else {
                            MineFantasyReforged.LOG.info("Skipping recipe {} of type {} because it's not a MFR Masonry Bench recipe", key, type);
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

    public static void addRecipe(MasonryBenchRecipeBase recipe, boolean checkForExistence, ResourceLocation key) {
        ItemStack itemStack = recipe.getMasonryBenchRecipeOutput();
        //if (ConfigCrafting.isMasonryBenchItemCraftable(itemStack)) {
            NonNullList<ItemStack> subItems = NonNullList.create();
            recipe.setRegistryName(key);
            itemStack.getItem().getSubItems(itemStack.getItem().getCreativeTab(), subItems);
            if (subItems.stream().anyMatch((s) -> {
                return recipe.getMasonryBenchRecipeOutput().isItemEqual(s);
            }) && (!checkForExistence || !MASONRY_BENCH_RECIPES.containsKey(recipe.getRegistryName()))) {
                MASONRY_BENCH_RECIPES.register(recipe);
            }
        //}

    }

    public static MasonryBenchRecipeBase findMatchingRecipe(IMasonryBench MasonryBench, MasonryBenchCraftMatrix matrix, World world) {
        Iterator<MasonryBenchRecipeBase> recipeIterator = getRecipes().iterator();
        MasonryBenchRecipeBase MasonryBenchRecipeBase = null;

        while(recipeIterator.hasNext()) {
            MasonryBenchRecipeBase rec = (MasonryBenchRecipeBase)recipeIterator.next();
            System.out.println("Waka " + rec.getName() + " : " + rec.getCraftingResult());
            if (rec.matches(matrix, world)) {
                MasonryBenchRecipeBase = rec;
                break;
            }
        }

        if (MasonryBenchRecipeBase != null) {
            MasonryBench.setProgressMax(MasonryBenchRecipeBase.getCraftTime());
            return MasonryBenchRecipeBase;
        } else {
            return null;
        }
    }

    public static MasonryBenchRecipeBase getRecipeByName(String name, boolean isNullable) {
        ResourceLocation resourceLocation = new ResourceLocation("minefantasyreforged:" + name);
        if (!MASONRY_BENCH_RECIPES.containsKey(resourceLocation) && !isNullable) {
            MineFantasyReforged.LOG.error("Masonry Bench Recipe Registry does not contain recipe: {}", name);
        }

        return (MasonryBenchRecipeBase)MASONRY_BENCH_RECIPES.getValue(resourceLocation);
    }

    public static List<MasonryBenchRecipeBase> getRecipesByName(String... names) {
        List<MasonryBenchRecipeBase> recipes = new ArrayList();
        String[] var2 = names;
        int var3 = names.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String name = var2[var4];
            recipes.add(getRecipeByName(name, false));
        }

        return recipes;
    }

    public static String getRecipeName(MasonryBenchRecipeBase recipe) {
        ResourceLocation recipeLocation = MASONRY_BENCH_RECIPES.getKey(recipe);
        return recipeLocation != null ? recipeLocation.getPath() : "";
    }
}
