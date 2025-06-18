package com.pippip5789.mfrex.core.content.tailor;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class TailorBenchCraftMatrix extends InventoryCrafting {

    private ITailorBench crafter;

    public TailorBenchCraftMatrix(ITailorBench crafter, Container instance, int xSize, int ySize) {
        super(instance, xSize, ySize);
        this.crafter = crafter;
    }

}
