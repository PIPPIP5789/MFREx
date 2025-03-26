package com.pippip5789.mfrex.core.content.masonry;

import javax.annotation.Nonnull;
import minefantasy.mfr.constants.Skill;
import minefantasy.mfr.util.CustomToolHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MasonryBenchShapedRecipe extends MasonryBenchRecipeBase {
    protected int width;
    protected int height;
    protected boolean shouldMirror;

    public MasonryBenchShapedRecipe(ItemStack output, NonNullList<Ingredient> inputs, int toolTier, int MasonryBenchTier, int craftTime, String toolType, SoundEvent soundOfCraft, String research, Skill skillUsed, int skillXp, float vanillaXp, int dirtyProgressAmount, boolean shouldMirror, int width, int height) {
        super(output, inputs, toolTier, MasonryBenchTier, craftTime, toolType, soundOfCraft, research, skillUsed, skillXp, vanillaXp, dirtyProgressAmount);
        this.shouldMirror = shouldMirror;
        this.width = width;
        this.height = height;
    }

    public boolean matches(MasonryBenchCraftMatrix matrix, @Nonnull World world) {
        for (int i = 0; i <= matrix.getWidth() - this.width; ++i) {
            for (int j = 0; j <= matrix.getHeight() - this.height; ++j) {
                if (this.checkMatch(matrix, i, j, true) && this.shouldMirror) {
                    return true;
                }

                if (this.checkMatch(matrix, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatch(InventoryCrafting matrix, int x, int y, boolean b) {
        for (int matrixX = 0; matrixX < 4; ++matrixX) {
            for (int matrixY = 0; matrixY < 4; ++matrixY) {
                int recipeX = matrixX - x;
                int recipeY = matrixY - y;
                Ingredient ingredient = Ingredient.EMPTY;
                if (recipeX >= 0 && recipeY >= 0 && recipeX < this.width && recipeY < this.height) {
                    if (b) {
                        ingredient = (Ingredient) this.inputs.get(this.width - recipeX - 1 + recipeY * this.width);
                    } else {
                        ingredient = (Ingredient) this.inputs.get(recipeX + recipeY * this.width);
                    }
                }

                ItemStack inputItem = matrix.getStackInRowAndColumn(matrixX, matrixY);
                boolean i = !inputItem.isEmpty();
                boolean j = !ingredient.apply(ItemStack.EMPTY);
                if (i || j) {
                    if (inputItem == null && ingredient != null || inputItem != null && ingredient == null) {
                        return false;
                    }

                    if (inputItem.isEmpty()) {
                        return false;
                    }

                    if (!ingredient.apply(inputItem)) {
                        return false;
                    }

                    if (!CustomToolHelper.doesMatchForRecipe(ingredient, inputItem)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public int getRecipeSize() {
        return this.width * this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
