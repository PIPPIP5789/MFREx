package com.PIPPIP5789.mfrex.pyrofantasy.init;

//import com.codetaylor.mc.pyrotech.modules.ModuleBase;
//import com.codetaylor.mc.athenaeum.module.ModuleRegistry;
//import com.codetaylor.mc.athenaeum.reference.ModuleMaterials;
//import com.codetaylor.mc.athenaeum.registry.Registry;

import com.PIPPIP5789.mfrex.core.util.RecipeIniter;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.core.item.ItemMaterial;
import com.codetaylor.mc.pyrotech.modules.hunting.ModuleHunting;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasic;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasicConfig;
import com.codetaylor.mc.pyrotech.modules.tech.basic.init.recipe.ChoppingBlockRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.SoakingPotRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachine;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.BrickSawmillRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.StoneSawmillRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.recipe.BrickSawmillRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.machine.recipe.StoneSawmillRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.refractory.ModuleTechRefractory;
import com.codetaylor.mc.pyrotech.modules.tool.ModuleTool;
import minefantasy.mfr.init.MineFantasyItems;
import minefantasy.mfr.init.MineFantasyMaterials;
import minefantasy.mfr.recipe.DummyRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class PyroFantasyRecipeInit extends RecipeIniter {

    public static void addNewRecipes() {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;

        //ModuleTechBasic.Registries.WORKTABLE_RECIPE.register(new WorktableRecipe());

        StoneSawmillRecipesAdd.registerSawmillRecipeWood(ModuleTechMachine.Registries.STONE_SAWMILL_RECIPES, "timber", MineFantasyItems.TIMBER.construct(MineFantasyMaterials.Names.OAK_WOOD), new OreIngredient("slabWood"), 1.0);
        //StoneSawmillRecipesAdd.registerSawmillRecipeWood(ModuleTechMachine.Registries.STONE_SAWMILL_RECIPES, "board", ItemMaterial.EnumType.BOARD.asStack(), Ingredient.fromStacks(MineFantasyItems.TIMBER.construct(MineFantasyMaterials.Names.OAK_WOOD)), 1.0);
        BrickSawmillRecipesAdd.registerInheritedRecipes(ModuleTechMachine.Registries.STONE_SAWMILL_RECIPES, ModuleTechMachine.Registries.BRICK_SAWMILL_RECIPES);

        ModuleTechBasic.Registries.SOAKING_POT_RECIPE.register(new SoakingPotRecipe(ItemMaterial.EnumType.BOARD_TARRED.asStack(), Ingredient.fromStacks(MineFantasyItems.TIMBER.construct(MineFantasyMaterials.Names.OAK_WOOD)), new FluidStack(ModuleTechRefractory.Fluids.WOOD_TAR, 50), true, (7 * 60 * 20) / 4).setRegistryName(ModuleTechBasic.MOD_ID, "board_tarred"));

        // ---------------------------------------------------------------------



        //ModuleTechMachine.Registries.STONE
        //BloomeryRecipe.
    }

    public static void removeOldRecipes() {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;

        recipeRegistry.remove(ModuleHunting.Items.CRUDE_SPEAR.getRegistryName());
        mfrRemoveRecipes(ModuleTool.Items.CRUDE_AXE);
        mfrRemoveRecipes(ModuleTool.Items.CRUDE_HOE);
        mfrRemoveRecipes(ModuleTool.Items.CRUDE_PICKAXE);
        mfrRemoveRecipes(ModuleTool.Items.CRUDE_SHOVEL);
        mfrRemoveRecipes(ModuleCore.Items.CRUDE_HAMMER);

        StoneSawmillRecipe.removeRecipes(Ingredient.fromStacks(ItemMaterial.EnumType.BOARD.asStack()));
        BrickSawmillRecipe.removeRecipes(Ingredient.fromStacks(ItemMaterial.EnumType.BOARD.asStack()));

        // ---------------------------------------------------------------------------------------------

        recipeRegistry.remove(MineFantasyItems.TIMBER.getRegistryName());
        mfrRemoveRecipes(MineFantasyItems.DRY_ROCKS);

        //CraftingManagerCarpenter.getInstance().recipes.remove("");
        /*removeCarpRecipe("stone_axe");
        removeCarpRecipe("stone_hammer");
        removeCarpRecipe("stone_hoe");
        removeCarpRecipe("stone_knife");
        removeCarpRecipe("stone_mace");
        removeCarpRecipe("stone_pick");
        removeCarpRecipe("stone_spade");
        removeCarpRecipe("stone_spear");
        removeCarpRecipe("stone_sword");
        removeCarpRecipe("stone_tongs");
        removeCarpRecipe("stone_waraxe");
        removeCarpRecipe("sharp_rock");
        removeCarpRecipe("sharp_rock-2");*/

        /*Map<ItemStack, ItemStack> SmeltingRecipes = FurnaceRecipes.instance().getSmeltingList();
        ItemStack oreItem = null;
        for(ItemStack item : SmeltingRecipes.keySet()) {
            if(item.getItem() == Item.getItemFromBlock(ore)) {
                oreItem = item;
            }
        }
        if(oreItem != null) {
            FurnaceRecipes.instance().getSmeltingList().remove(oreItem);
        }*/
    }

}
