package com.pippip5789.mfrex.core.content.masonry;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class MasonryBenchCraftMatrix extends InventoryCrafting {

    private IMasonryBench crafter;

    public MasonryBenchCraftMatrix(IMasonryBench crafter, Container instance, int xSize, int ySize) {
        super(instance, xSize, ySize);
        this.crafter = crafter;
    }

}
