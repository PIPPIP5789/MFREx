package com.PIPPIP5789.mfrex.main;

import com.PIPPIP5789.mfrex.recipe.BlastFurnaceRecipeLoader;
import com.PIPPIP5789.mfrex.recipe.CrucibleRecipeLoader;
import com.PIPPIP5789.mfrex.recipe.QuernRecipeLoader;
import com.PIPPIP5789.mfrex.registry.OreRegistry;
import minefantasy.mfr.recipe.RecipeLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
//import org.apache.logging.log4j.Logger;


@Mod(modid = MFRExMain.MODID, name = MFRExMain.NAME, version = MFRExMain.VERSION, dependencies = "after:minefantasyreforged")
public class MFRExMain
{
    public static final String MODID = "mfrex";
    public static final String NAME = "MineFantasyExtras";
    public static final String VERSION = "0.1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        OreRegistry.INSTANCE.postInit();
        QuernRecipeLoader.INSTANCE.postInit();
        CrucibleRecipeLoader.INSTANCE.postInit();
        BlastFurnaceRecipeLoader.INSTANCE.postInit();
    }

}
