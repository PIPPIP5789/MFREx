package com.PIPPIP5789.mfrex.rusticatedfantasy;

import com.PIPPIP5789.mfrex.rusticatedfantasy.init.RusticatedFantasyItemInit;
import com.PIPPIP5789.mfrex.rusticatedfantasy.init.RusticatedFantasyRecipeInit;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class RusticatedFantasyModule {

    public static void preInit(FMLPreInitializationEvent event) {
        RusticatedFantasyItemInit.initItems();
    }

    public static void init(FMLInitializationEvent event) {
        try {
            RusticatedFantasyItemInit.initOreDict();
        }
        catch(ClassNotFoundException e) {
        }
    }

    public static void postInit(FMLPostInitializationEvent event) {
        RusticatedFantasyRecipeInit.removeOldRecipes();
    }


}
