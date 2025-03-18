package com.PIPPIP5789.mfrex.pits_of_fantasy_charcoal.init;

import charcoalPit.blocks.BlockBloomeryHatch;
import charcoalPit.blocks.BlockCreosoteCollector;
import charcoalPit.blocks.BlocksRegistry;
import charcoalPit.fluids.FluidsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PitsOfCharcoalFantasyBlockInit {

    public static BlockCreosoteCollector firebrickCreosoteCollector;

    public static void initBlocks() {
        //firebrickCreosoteCollector = new BlockCreosoteCollector("firebrick_creosote_collector", true);
        //firebrickCreosoteCollector.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public static void register(RegistryEvent.Register<Block> event) {
        //event.getRegistry().register(firebrickCreosoteCollector);
    }

}
