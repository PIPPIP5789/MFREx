package com.PIPPIP5789.mfrex.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import minefantasy.mfr.MineFantasyReforged;
import minefantasy.mfr.api.MineFantasyReforgedAPI;
import minefantasy.mfr.api.refine.Alloy;
import minefantasy.mfr.constants.Skill;
import minefantasy.mfr.recipe.RecipeLoader;
import minefantasy.mfr.recipe.refine.QuernRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlastFurnaceRecipeLoader extends RecipeLoader {

    public static final BlastFurnaceRecipeLoader INSTANCE = new BlastFurnaceRecipeLoader();
    private static final String TYPE = "blastfurnace";
    private static final String CUSTOM_RECIPE_DIRECTORY = "config/minefantasyreforged/custom/recipes/blastfurnace_recipes/";

    public static final List<JsonObject> list = new ArrayList<>();

    /*
       {
            "type": "BlastFurnaceRecipe",
            "input": {
                "item": "minecraft:sand"
            },
            "result": {
                "item": "minefantasyreforged:salt"
            }
       }
     */

    private BlastFurnaceRecipeLoader() {} // no instances!

    public void postInit() {
        createCustomDataDirectory(CUSTOM_RECIPE_DIRECTORY);
        loadRegistry(TYPE, null, CUSTOM_RECIPE_DIRECTORY);
    }

    @Override
    protected void parse(String name, JsonObject json) {
        String type = JsonUtils.getString(json, "type");
        ItemStack inputStack = deserializeIngredient(JsonUtils.getJsonObject(json, "input")).getMatchingStacks()[0];
        ItemStack resultStack = deserializeIngredient(JsonUtils.getJsonObject(json, "result")).getMatchingStacks()[0];

        MineFantasyReforgedAPI.addBlastFurnaceRecipe(inputStack.getItem(), resultStack);
    }

    @Override
    public void loadRegistry(String type, String defaultDirectory, String configDirectory) {
        MineFantasyReforged.LOG.info("Loading " + type + " registry entries from config directory");
        loadRegistryFiles(new File(configDirectory), "", type);
    }

}
