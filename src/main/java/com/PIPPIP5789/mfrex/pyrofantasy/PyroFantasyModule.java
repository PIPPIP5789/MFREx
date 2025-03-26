package com.PIPPIP5789.mfrex.pyrofantasy;

import com.PIPPIP5789.mfrex.pyrofantasy.init.ToolRegistryInit;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class PyroFantasyModule {

    public static void preInit(FMLPreInitializationEvent event) {
        ToolRegistryInit.registerHammers();
    }

    public static void init(FMLInitializationEvent event) {
    }

    public static void postInit(FMLPostInitializationEvent event) {
    }

}
