package com.pippip5789.mfrex.core.content.tailor;

import minefantasy.mfr.constants.Skill;
import minefantasy.mfr.util.CustomToolHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TailorBenchShapelessRecipe extends TailorBenchRecipeBase {

    public TailorBenchShapelessRecipe(ItemStack output, NonNullList<Ingredient> inputs, int toolTier, int TailorBenchTier, int craftTime, String toolType, SoundEvent soundOfCraft, String research, Skill skillUsed, int skillXp, float vanillaXp) {
        super(output, inputs, toolTier, TailorBenchTier, craftTime, toolType, soundOfCraft, research, skillUsed, skillXp, vanillaXp);
    }

    public boolean matches(TailorBenchCraftMatrix matrix, World world) {
        NonNullList<Ingredient> ingredients = this.getIngredients();
        List<Boolean> ingredientsMatched = new ArrayList(Collections.nCopies(ingredients.size(), false));

        for(int i = 0; i < matrix.getSizeInventory(); ++i) {
            ItemStack inputItem = matrix.getStackInSlot(i);
            if (!inputItem.isEmpty()) {
                boolean matched = false;

                for(int j = 0; j < ingredients.size(); ++j) {
                    boolean passesChecks = true;
                    if (inputItem.isEmpty()) {
                        passesChecks = false;
                    }

                    if (!CustomToolHelper.doesMatchForRecipe((Ingredient)ingredients.get(j), inputItem)) {
                        passesChecks = false;
                    }

                    if (passesChecks && ((Ingredient)ingredients.get(j)).apply(inputItem)) {
                        ingredientsMatched.set(j, true);
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    return false;
                }
            }
        }

        return !ingredientsMatched.contains(false);
    }

    public int getRecipeSize() {
        return this.inputs.size();
    }
    
}
