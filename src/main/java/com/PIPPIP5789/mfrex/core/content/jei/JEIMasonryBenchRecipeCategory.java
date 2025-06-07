package com.pippip5789.mfrex.core.content.jei;

import com.pippip5789.mfrex.core.MFREx;
import com.pippip5789.mfrex.core.content.jei.JEIMasonryBenchRecipe;
import com.pippip5789.mfrex.core.content.masonry.CraftingManagerMasonryBench;
import com.pippip5789.mfrex.core.content.masonry.MasonryBenchRecipeBase;
import com.pippip5789.mfrex.core.init.MFRExBlockInit;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import minefantasy.mfr.MineFantasyReforged;
import minefantasy.mfr.init.MineFantasyBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * JEI recipe category implementation for all "recipes" in the MasonryBench bench.
 */
public class JEIMasonryBenchRecipeCategory implements IRecipeCategory<JEIMasonryBenchRecipe> {

    static final String UID = "mfrex:masonry";

    static final ResourceLocation TEXTURE = new ResourceLocation(MFREx.MODID, "textures/gui/masonry_bench_background.png");
    private final IDrawable icon;

    static final int WIDTH = 134;
    static final int HEIGHT = 100;

    private final IDrawable background;

    public JEIMasonryBenchRecipeCategory(IRecipeCategoryRegistration registry) {
        IGuiHelper iGuiHelper = registry.getJeiHelpers().getGuiHelper();
        background = iGuiHelper.createDrawable(TEXTURE, 0, 0, WIDTH, HEIGHT);
        icon = iGuiHelper.createDrawableIngredient(new ItemStack(MFRExBlockInit.masonryBench));
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return I18n.format("integration.jei.category." + UID);
    }

    @Override
    public String getModName() {
        return MFREx.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    /**
     * Set the {@link IRecipeLayout} properties from the {@link IRecipeWrapper} and {@link IIngredients}.
     *
     * @param recipeLayout  the layout that needs its properties set.
     * @param recipeWrapper the recipeWrapper, for extra information.
     * @param ingredients   the ingredients, already set by the recipeWrapper
     * @since JEI 3.11.0
     */
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, JEIMasonryBenchRecipe recipeWrapper, IIngredients ingredients) {

        // Okay, they're not technically *slots* but to all intents and purposes, that's how they behave
        IGuiItemStackGroup slots = recipeLayout.getItemStacks();

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        // Init ingredient slots, 4x4 grid
        for (int x = 0; x < MasonryBenchRecipeBase.MAX_WIDTH; x++) {
            for (int y = 0; y < MasonryBenchRecipeBase.MAX_HEIGHT; y++) {
                int slot = y * MasonryBenchRecipeBase.MAX_WIDTH + x;
                slots.init(slot, true, 1 + x * 18, 1 + y * 18);
            }
        }

        // Init output slot
        slots.init(16, false, 112, 28);

        // Assign ingredients to slots
        for (int j = 0; j < inputs.size(); j++) {
            slots.set(j, inputs.get(j));
        }
        // Assign outputs to slot
        for (List<ItemStack> output : outputs) {
            slots.set(16, output);
        }
    }

    /**
     * Generates all the MFR MasonryBench recipes for JEI.
     */
    public static Collection<JEIMasonryBenchRecipe> generateRecipes(IStackHelper stackHelper) {
        return new ArrayList<>(generateMasonryBenchRecipes(stackHelper));
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    private static Collection<JEIMasonryBenchRecipe> generateMasonryBenchRecipes(IStackHelper stackHelper) {

        List<JEIMasonryBenchRecipe> recipes = new ArrayList<>();
        Collection<MasonryBenchRecipeBase> MasonryBenchRecipes = CraftingManagerMasonryBench.getRecipes();

        for (MasonryBenchRecipeBase MasonryBenchRecipe : MasonryBenchRecipes) {
            recipes.add(new JEIMasonryBenchRecipe(MasonryBenchRecipe, stackHelper));
        }

        return recipes;

    }
}