package com.PIPPIP5789.mfrex.betterwithfantasy;

import betterwithmods.common.registry.block.recipe.StateIngredient;
import betterwithmods.common.registry.heat.BWMHeatRegistry;
import com.PIPPIP5789.mfrex.betterwithfantasy.init.BetterWithFantasyItemInit;
import com.PIPPIP5789.mfrex.betterwithfantasy.init.BetterWithFantasyRecipeInit;
import com.google.common.collect.Lists;
import minefantasy.mfr.init.MineFantasyBlocks;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BetterWithFantasyModule {

    public static void preInit(FMLPreInitializationEvent event) {
        BetterWithFantasyItemInit.initItems();
    }

    public static void init(FMLInitializationEvent event) {
        try {
            BetterWithFantasyItemInit.initOreDict();
            initHeatSources(event);
        }
        catch(ClassNotFoundException e) {
        }
    }

    public static void postInit(FMLPostInitializationEvent event) {
        BetterWithFantasyRecipeInit.unregisterUnusedRecipes();
    }

    private static void initHeatSources(FMLInitializationEvent event) {
        for(int i = 0; i < MineFantasyBlocks.FIREPIT.getBlockState().getValidStates().size(); i++) {
            IBlockState state = MineFantasyBlocks.FIREPIT.getBlockState().getValidStates().get(i);
            if(state.getValue(PropertyBool.create("burning"))) {
                for (int tot = 0; tot < 4 ; tot++) {
                    BWMHeatRegistry.addHeatSource(new StateIngredient(Lists.newArrayList(MineFantasyBlocks.FIREPIT.getDefaultState().withProperty(PropertyBool.create("burning"), true).withProperty(PropertyBool.create("under"), false)), Lists.newArrayList(new ItemStack(Item.getItemFromBlock(MineFantasyBlocks.FIREPIT), 1))), 1);
                }
            }
        }
        /*for(int i = 0; i < MineFantasyBlocks.FORGE.getBlockState().getValidStates().size(); i++) {
            IBlockState state = MineFantasyBlocks.FORGE.getBlockState().getValidStates().get(i);
            if(state.getValue(PropertyBool.create("burning"))) {
                for (int tot = 0; tot < 4 ; tot++) {
                    BWMHeatRegistry.addHeatSource(new StateIngredient(Lists.newArrayList(MineFantasyBlocks.FORGE.getDefaultState().withProperty(PropertyBool.create("burning"), true).withProperty(PropertyBool.create("under"), false).withProperty(PropertyInteger.create("fuel_count", 0, 3), tot)), Lists.newArrayList(new ItemStack(Item.getItemFromBlock(MineFantasyBlocks.FORGE), 1))), 1);
                }
            }
        }*/
    }

}
