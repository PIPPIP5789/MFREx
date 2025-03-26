package com.pippip5789.mfrex.spartanfantasy;

import com.pippip5789.mfrex.spartanfantasy.init.SpartanFantasyItemInit;
import com.pippip5789.mfrex.spartanfantasy.init.SpartanFantasyRecipeInit;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class SpartanFantasyModule {

    public static void preInit(FMLPreInitializationEvent event) {
        SpartanFantasyItemInit.initItems();
    }

    public static void init(FMLInitializationEvent event) {
        /*try {
            PitsOfCharcoalFantasyItemInit.initOreDict();
        }
        catch(ClassNotFoundException e) {
        }*/
    }

    public static void postInit(FMLPostInitializationEvent event) {
        SpartanFantasyRecipeInit.removeOldRecipes();
    }


}
