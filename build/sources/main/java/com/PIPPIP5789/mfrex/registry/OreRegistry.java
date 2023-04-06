package com.PIPPIP5789.mfrex.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import minefantasy.mfr.MineFantasyReforged;
import minefantasy.mfr.api.MineFantasyReforgedAPI;
import minefantasy.mfr.init.MineFantasyItems;
import minefantasy.mfr.material.CustomMaterial;
import minefantasy.mfr.material.MetalMaterial;
import minefantasy.mfr.recipe.refine.BloomRecipe;
import minefantasy.mfr.registry.DataLoader;
import minefantasy.mfr.util.FileUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

/*

{
          "type": "AlloyRecipe",
          "crucible_tier": 0,
          "recipe_time": 35,
          "pattern": [
            "   ",
            "   ",
            "   "
          ],
          "key": {
            "R": {
              "item": "minefantasyreforged:rivet"
            },
            "E": {
              "item": "minefantasyreforged:engin_anvil_tools"
            },
            "S": {
              "nbt": "{mf_custom_materials:{main_material:\"steel\"}}",
              "item": "minefantasyreforged:metal_hunk"
            },
            "I": {
              "nbt": "{mf_custom_materials:{main_material:\"iron\"}}",
              "item": "minefantasyreforged:metal_hunk"
            }
          },
          "result": {
            "item": "minefantasyreforged:iron_frame"
          }
       }












 */




import static minefantasy.mfr.recipe.RecipeLoader.deserializeIngredient;

public class OreRegistry extends DataLoader {

    private static final String ORES = "ores";

    private static final String TYPE = "ore";

    public static final OreRegistry INSTANCE = new OreRegistry();

    private static final String CUSTOM_RECIPE_DIRECTORY = "config/minefantasyreforged/custom/registry";

    public void postInit() {
        createCustomDataDirectory(CUSTOM_RECIPE_DIRECTORY);
        loadRegistry(TYPE, null, CUSTOM_RECIPE_DIRECTORY);
    }

    @Override
    protected void parse(String name, JsonObject json) {
        String mod = JsonUtils.getString(json, "mod");
        JsonArray ores = JsonUtils.getJsonArray(json, "ores");

        for (JsonElement e : ores) {
            JsonObject ore = JsonUtils.getJsonObject(e, "");
            ItemStack input = deserializeIngredient(JsonUtils.getJsonObject(ore, "ore")).getMatchingStacks()[0];
            ItemStack output = MineFantasyItems.bar(JsonUtils.getString(ore, "bar"));

            BloomRecipe.addRecipe(input, output);
            MineFantasyReforgedAPI.addFurnaceRecipe(input, output, 0);
        }
    }

    @Override
    public void loadRegistry(String type, String defaultDirectory, String configDirectory) {
        MineFantasyReforged.LOG.info("Loading " + type + " registry entries from config directory");
        loadRegistryFiles(new File(configDirectory), "", type);
    }

    public void loadRegistryFiles(File source, String base, String type) {
        FileUtils.findFiles(source, base, (root, file) -> {
            String extension = FilenameUtils.getExtension(file.toString());

            if (!extension.equals(JSON_FILE_EXT)) {
                return;
            }

            Path relative = root.relativize(file);
            if (relative.getNameCount() > 1) {
                String modName = relative.getName(0).toString();
                String fileName = FilenameUtils.removeExtension(relative.getFileName().toString());

                if (!Loader.isModLoaded(modName) || !fileName.equals(ORES)) {
                    return;
                }

                JsonObject jsonObject = fileToJsonObject(file, relative, type);
                parse(fileName, jsonObject);
            }
        });
    }

}
