package com.PIPPIP5789.mfrex.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.sun.jna.platform.unix.X11;
import minefantasy.mfr.MineFantasyReforged;
import minefantasy.mfr.api.MineFantasyReforgedAPI;
import minefantasy.mfr.api.refine.Alloy;
import minefantasy.mfr.recipe.RecipeLoader;
import minefantasy.mfr.registry.DataLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static minefantasy.mfr.recipe.RecipeLoader.deserializeIngredient;

public class CrucibleRecipeLoader extends RecipeLoader {


    public static final CrucibleRecipeLoader INSTANCE = new CrucibleRecipeLoader();
    private static final String TYPE = "alloy";
    private static final String CUSTOM_RECIPE_DIRECTORY = "config/minefantasyreforged/custom/recipes/crucible_recipes/";

    public static final List<JsonObject> list = new ArrayList<>();

    /*
{
    "name": "brass",
    "type": "AlloyRecipe",
    "crucible_tier": 0,
    "pattern": "CTT"
    "key": {
        "C": {
            "nbt": "{mf_custom_materials:{main_material:\"copper\"}}",
            "item": "minefantasyreforged:bar"
        },
        "T": {
            "nbt": "{mf_custom_materials:{main_material:\"tin\"}}",
            "item": "minefantasyreforged:bar"
        }
    },
    "result": {
        "nbt": "{mf_custom_materials:{main_material:\"bronze\"}}",
        "item": "minefantasyreforged:bar"
    }
}
     */

    private CrucibleRecipeLoader() {} // no instances!

    @Override
    protected void parse(String name, JsonObject json) {
        String type = JsonUtils.getString(json, "type");

        //ItemStack output = JsonUtils.getBoolean(json, "output", false);
        String recipe = JsonUtils.getString(json, "pattern", "");
        int crucible_tier = JsonUtils.getInt(json, "crucible_tier", 0);
        ItemStack resultStack = deserializeIngredient(JsonUtils.getJsonObject(json, "result")).getMatchingStacks()[0];
        JsonObject key = JsonUtils.getJsonObject(json, "key");
        ArrayList<ItemStack> itemRecipe = new ArrayList<ItemStack>();

        for (int i = 0; i < recipe.length(); i++) {
            String str = recipe.substring(i, i + 1);
            ItemStack part = deserializeIngredient(JsonUtils.getJsonObject(key, str)).getMatchingStacks()[0];
            itemRecipe.add(part);
        }

        MineFantasyReforgedAPI.addAlloy(new Alloy(resultStack, crucible_tier, itemRecipe));
    }

    public void postInit() {
        createCustomDataDirectory(CUSTOM_RECIPE_DIRECTORY);
        loadRegistry(TYPE, null, CUSTOM_RECIPE_DIRECTORY);
    }

    @Override
    public void loadRegistry(String type, String defaultDirectory, String configDirectory) {
        MineFantasyReforged.LOG.info("Loading " + type + " registry entries from config directory");
        loadRegistryFiles(new File(configDirectory), "", type);
    }

}
