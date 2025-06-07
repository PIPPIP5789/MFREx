package com.pippip5789.mfrex.core.content.jei;

import mezz.jei.Internal;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.gui.GuiHelper;
import mezz.jei.runtime.JeiHelpers;
import minefantasy.mfr.config.ConfigIntegration;
import minefantasy.mfr.init.MineFantasyBlocks;
import minefantasy.mfr.init.MineFantasyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JEIPlugin
public class JEIIntegration implements IModPlugin {

    public JEIIntegration() {
    }

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {

        if (!ConfigIntegration.jeiIntegration) {
            return;
        }

        JeiHelpers jeiHelpers = Internal.getHelpers();
        GuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registry.addRecipeCategories(new JEIMasonryBenchRecipeCategory(registry));
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {

        if (!ConfigIntegration.jeiIntegration)
            return;

        // UNUSED
        // If we want to hide some items, those can be listed here
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        // e.g.:
        // blacklist.addIngredientToBlacklist(MineFantasyItems.ANCIENT_JEWEL_ADAMANT);
        // /UNUSED

        IStackHelper stackHelper = registry.getJeiHelpers().getStackHelper();

        registry.addRecipes(JEIMasonryBenchRecipeCategory.generateRecipes(stackHelper), JEIMasonryBenchRecipeCategory.UID);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.useNbtForSubtypes(MineFantasyItems.BAR);
    }

}