package com.PIPPIP5789.mfrex.core.content.masonry;

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

public class MasonryBenchRecipeFactory {
    public MasonryBenchRecipeFactory() {
    }

    public MasonryBenchRecipeBase parse(JsonContext context, JsonObject json) {
        String type = JsonUtils.getString(json, "type");
        MasonryBenchRecipeType recipeType = MasonryBenchRecipeType.deserialize(type);
        switch (recipeType) {
            case MASONRY_BENCH_SHAPED_RECIPE:
                return this.parseShaped(context, json);
            case MASONRY_BENCH_SHAPELESS_RECIPE:
                return this.parseShapeless(context, json);
            default:
                return null;
        }
    }

    private MasonryBenchRecipeBase parseShapeless(JsonContext context, JsonObject json) {
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
            int block_tier = JsonUtils.getInt(json, "block_tier", -1);
            int dirty_progress_amount = JsonUtils.getInt(json, "dirty_progress_amount", 0);
            ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
            return new MasonryBenchShapelessRecipe(result, ingredients, tool_tier, block_tier, craft_time, tool_type, (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(sound)), research, skill, skillXp, vanillaXp, dirty_progress_amount);
        }
    }

    private MasonryBenchRecipeBase parseShaped(JsonContext context, JsonObject json) {
        ShapedOreRecipe recipe = ShapedOreRecipe.factory(context, json);
        Skill skill = Skill.fromName(JsonUtils.getString(json, "skill", "none"));
        String research = JsonUtils.getString(json, "research", "none");
        int skillXp = JsonUtils.getInt(json, "skill_xp", 0);
        float vanillaXp = JsonUtils.getFloat(json, "vanilla_xp", 0.0F);
        String sound = JsonUtils.getString(json, "sound", "minecraft:block.wood.hit");
        String tool_type = JsonUtils.getString(json, "tool_type", "none");
        int craft_time = JsonUtils.getInt(json, "craft_time", 0);
        int tool_tier = JsonUtils.getInt(json, "tool_tier", 0);
        int block_tier = JsonUtils.getInt(json, "block_tier", -1);
        int dirty_progress_amount = JsonUtils.getInt(json, "dirty_progress_amount", 1);
        boolean shouldMirror = JsonUtils.getBoolean(json, "shouldMirror", true);
        return new MasonryBenchShapedRecipe(recipe.getRecipeOutput(), recipe.getIngredients(), tool_tier, block_tier, craft_time, tool_type, (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(sound)), research, skill, skillXp, vanillaXp, dirty_progress_amount, shouldMirror, recipe.getRecipeWidth(), recipe.getRecipeHeight());
    }

}