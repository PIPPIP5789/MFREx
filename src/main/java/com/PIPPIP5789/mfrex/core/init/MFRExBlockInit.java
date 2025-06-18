package com.pippip5789.mfrex.core.init;

import com.pippip5789.mfrex.core.content.masonry.BlockMasonryBench;
import com.pippip5789.mfrex.core.content.masonry.TileEntityMasonryBench;
import com.pippip5789.mfrex.core.content.tailor.BlockTailorBench;
import com.pippip5789.mfrex.core.content.tailor.TileEntityTailorBench;
import minefantasy.mfr.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class MFRExBlockInit {

    public static BlockMasonryBench masonryBench = (BlockMasonryBench) Utils.nullValue();
    public static BlockTailorBench tailorBench = (BlockTailorBench) Utils.nullValue();

    public static void initBlocks() {
        masonryBench = new BlockMasonryBench("masonry_bench");
        tailorBench = new BlockTailorBench("tailor_bench");
    }

    public static void register(RegistryEvent.Register<Block> event) {
        System.out.println("MFREx registering blocks");

        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(masonryBench);
        registerTile(TileEntityMasonryBench.class, "masonry_bench_tile");
        registry.register(tailorBench);
        registerTile(TileEntityTailorBench.class, "tailor_bench_tile");
    }

    private static void registerTile(Class<? extends TileEntity> teClass, String teId) {
        GameRegistry.registerTileEntity(teClass, new ResourceLocation("mfrex", teId));
    }

}
