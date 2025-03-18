package com.PIPPIP5789.mfrex.pyrofantasy.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;

public class MoltenFluid extends Fluid {

    public MoltenFluid(String fluidName, Color color) {
        super(fluidName, FluidRegistry.getFluid("lava").getStill(), FluidRegistry.getFluid("lava").getFlowing(), color);
    }

}
