package com.pippip5789.mfrex.core.content.tailor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import minefantasy.mfr.constants.Skill;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.Iterator;

public class TailorBenchRecipeFactory {

    public TailorBenchRecipeFactory() {
    }

    public TailorBenchRecipeBase parse(JsonContext context, JsonObject json) {
        String type = JsonUtils.getString(json, "type");
        TailorBenchRecipeType recipeType = TailorBenchRecipeType.deserialize(type);
        switch (recipeType) {
            case LOOM_SHAPED_RECIPE:
                return this.parseShaped(context, json);
            case LOOM_SHAPELESS_RECIPE:
                return this.parseShapeless(context, json);
            default:
                return null;
        }
    }

    private TailorBenchRecipeBase parseShapeless(JsonContext context, JsonObject json) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        Iterator var4 = JsonUtils.getJsonArray(json, "ingredients").iterator();

        while (var4.hasNext()) {
            JsonElement ele = (JsonElement) var4.next();
            ingredients.add(CraftingHelper.getIngredient(ele, context));
        }

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for shapeless recipe");
        } else {
            Skill skill = Skill.fromName(JsonUtils.getString(json, "skill", "none"));
            String research = JsonUtils.getString(json, "research", "none");
            int skillXp = JsonUtils.getInt(json, "skill_xp", 0);
            float vanillaXp = JsonUtils.getFloat(json, "vanilla_xp", 0.0F);
            String sound = JsonUtils.getString(json, "sound", "minecraft:block.wood.hit");
            String tool_type = JsonUtils.getString(json, "tool_type", "none");
            int craft_time = JsonUtils.getInt(json, "craft_time", 0);
            int tool_tier = JsonUtils.getInt(json, "tool_tier", 0);
            ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
            return new TailorBenchShapelessRecipe(result, ingredients, tool_tier, -1, craft_time, tool_type, (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(sound)), research, skill, skillXp, vanillaXp);
        }
    }

    private TailorBenchRecipeBase parseShaped(JsonContext context, JsonObject json) {
        ShapedOreRecipe recipe = ShapedOreRecipe.factory(context, json);
        Skill skill = Skill.fromName(JsonUtils.getString(json, "skill", "none"));
        String research = JsonUtils.getString(json, "research", "none");
        int skillXp = JsonUtils.getInt(json, "skill_xp", 0);
        float vanillaXp = JsonUtils.getFloat(json, "vanilla_xp", 0.0F);
        String sound = JsonUtils.getString(json, "sound", "minecraft:block.wood.hit");
        String tool_type = JsonUtils.getString(json, "tool_type", "none");
        int craft_time = JsonUtils.getInt(json, "craft_time", 0);
        int tool_tier = JsonUtils.getInt(json, "tool_tier", 0);
        return new TailorBenchShapedRecipe(recipe.getRecipeOutput(), recipe.getIngredients(), tool_tier, -1, craft_time, tool_type, (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(sound)), research, skill, skillXp, vanillaXp, true, recipe.getRecipeWidth(), recipe.getRecipeHeight());
    }

}