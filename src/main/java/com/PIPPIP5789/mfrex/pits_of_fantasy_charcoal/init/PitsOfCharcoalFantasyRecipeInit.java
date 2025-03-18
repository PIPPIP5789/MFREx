package com.PIPPIP5789.mfrex.pits_of_fantasy_charcoal.init;

import charcoalPit.blocks.BlockCeramicPot;
import charcoalPit.blocks.BlockPotteryKiln;
import charcoalPit.items.ItemsRegistry;
import charcoalPit.tile.TileCeramicPot;
import charcoalPit.tile.TileClayPot;
import com.PIPPIP5789.mfrex.core.util.RecipeIniter;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import minefantasy.mfr.recipe.CarpenterCraftMatrix;
import minefantasy.mfr.recipe.CarpenterShapedRecipe;
import minefantasy.mfr.recipe.CraftingManagerCarpenter;
import minefantasy.mfr.recipe.factories.CarpenterRecipeFactory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Loader;
import rustic.common.blocks.ModBlocks;
import rustic.common.blocks.crops.Herbs;
import rustic.common.crafting.AdvancedCondenserRecipe;
import rustic.common.crafting.Recipes;
import rustic.common.potions.PotionsRustic;

import charcoalPit.blocks.BlocksRegistry;

public class PitsOfCharcoalFantasyRecipeInit extends RecipeIniter {

    public static void removeOldRecipes() {
        mfrRemoveRecipes(BlocksRegistry.thatch);
        mfrRemoveRecipes(ItemsRegistry.clayPot);
        mfrRemoveRecipes(ItemsRegistry.clay_Pot);
        mfrRemoveRecipes(Item.getItemFromBlock(BlocksRegistry.reinforcedBrick));
        mfrRemoveRecipes(Item.getItemFromBlock(BlocksRegistry.clayPot));
        mfrRemoveRecipes(Item.getItemFromBlock(BlocksRegistry.hatch));
        /*CraftingManagerCarpenter.getRecipes().add(new CarpenterShapedRecipe(
                new ItemStack(Blocks.AIR, 1),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getIngredients(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getToolTier(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getCarpenterTier(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getCraftTime(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getSkillXp(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getVanillaXp(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getToolType().getName(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getSound(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getRequiredResearch(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getSkill(),
     true,
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getWidth(),
                CraftingManagerCarpenter.getRecipeByName("bloomery", true).getHeight()
        ));*/
    }

}
