package com.PIPPIP5789.mfrex.core;

import betterwithmods.BWMod;
import com.PIPPIP5789.mfrex.animaniafantasy.AnimaniaFantasyModule;
import com.PIPPIP5789.mfrex.betterwithfantasy.BetterWithFantasyModule;
import com.PIPPIP5789.mfrex.core.init.MFRExBlockInit;
import com.PIPPIP5789.mfrex.core.init.MFRExItemInit;
import com.PIPPIP5789.mfrex.core.init.MFRExRecipeInit;
import com.PIPPIP5789.mfrex.core.util.EventHandler;
import com.PIPPIP5789.mfrex.fantasy_corn.FantasyCornModule;
import com.PIPPIP5789.mfrex.pits_of_fantasy_charcoal.PitsOfCharcoalFantasyModule;
import com.PIPPIP5789.mfrex.pyrofantasy.PyroFantasyModule;
import com.PIPPIP5789.mfrex.rusticatedfantasy.RusticatedFantasyModule;
import com.PIPPIP5789.mfrex.rusticfantasy.RusticFantasyModule;
import com.animania.Animania;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import minefantasy.mfr.MineFantasyReforged;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import panda.corn.SimpleCorn;
import rustic.core.Rustic;

/*

    Recommended config options:
    *

 */

@Mod(modid = MFREx.MODID, name = MFREx.NAME, version = MFREx.VERSION, dependencies = "after:" + MineFantasyReforged.MOD_ID + ";after:" + SimpleCorn.MODID + ";after:" + Rustic.MODID + ";after:" + BWMod.MODID + ";after:" + ModSpartanWeaponry.ID + ";after:" + Animania.MODID)
public class MFREx {
    public static final String MODID = "mfrex";
    public static final String NAME = "MFREx";
    public static final String VERSION = "2.2";

    @Mod.Instance(MODID)
    public static MFREx INSTANCE;

    //@SidedProxy(clientSide = "com.PIPPIP5789.betterwithfantasy.proxy.ClientProxy", serverSide = "com.PIPPIP5789.betterwithfantasy.proxy.ServerProxy")
    //public static CommonProxy PROXY;
    //public ItemRegistry itemRegistry;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        MFRExBlockInit.initBlocks();
        MFRExItemInit.initItems();

        if(Loader.isModLoaded(SimpleCorn.MODID)) {
            FantasyCornModule.preInit(event);
        }
        if(Loader.isModLoaded(Rustic.MODID)) {
            RusticFantasyModule.preInit(event);
        }
        if(Loader.isModLoaded(Animania.MODID)) {
            AnimaniaFantasyModule.preInit(event);
        }
        if(Loader.isModLoaded(BWMod.MODID)) {
            BetterWithFantasyModule.preInit(event);
        }
        if(Loader.isModLoaded(ModSpartanWeaponry.ID)) {
            //SpartanFantasyModule.preInit(event);
        }
        if(Loader.isModLoaded("charcoal_pit")) {
            PitsOfCharcoalFantasyModule.preInit(event);
        }
        if(Loader.isModLoaded("rusticatedfruits")) {
            RusticatedFantasyModule.preInit(event);
        }
        if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
            PyroFantasyModule.preInit(event);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        MFRExItemInit.initOreDict();

        if(Loader.isModLoaded(SimpleCorn.MODID)) {
            FantasyCornModule.init(event);
        }
        if(Loader.isModLoaded(Rustic.MODID)) {
            RusticFantasyModule.init(event);
        }
        if(Loader.isModLoaded(Animania.MODID)) {
            AnimaniaFantasyModule.init(event);
        }
        if(Loader.isModLoaded(BWMod.MODID)) {
            BetterWithFantasyModule.init(event);
        }
        if(Loader.isModLoaded(ModSpartanWeaponry.ID)) {
            //SpartanFantasyModule.init(event);
        }
        if(Loader.isModLoaded("rusticatedfruits")) {
            RusticatedFantasyModule.init(event);
        }
        if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
            PyroFantasyModule.init(event);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MFRExRecipeInit.removeOldRecipes();

        if(Loader.isModLoaded(SimpleCorn.MODID)) {
            FantasyCornModule.postInit(event);
        }
        if(Loader.isModLoaded(Rustic.MODID)) {
            RusticFantasyModule.postInit(event);
        }
        if(Loader.isModLoaded(Animania.MODID)) {
            AnimaniaFantasyModule.postInit(event);
        }
        if(Loader.isModLoaded(BWMod.MODID)) {
            BetterWithFantasyModule.postInit(event);
        }
        if(Loader.isModLoaded(ModSpartanWeaponry.ID)) {
            //SpartanFantasyModule.postInit(event);
        }
        if(Loader.isModLoaded("charcoal_pit")) {
            PitsOfCharcoalFantasyModule.postInit(event);
        }
        if(Loader.isModLoaded("rusticatedfruits")) {
            RusticatedFantasyModule.postInit(event);
        }
        if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
            PyroFantasyModule.postInit(event);
        }

        /*System.out.println("Translator's Key: ");
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 1).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 1).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 2).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 2).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 3).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 3).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 4).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 4).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 5).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 5).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 6).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 6).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 7).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 7).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 8).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 8).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
        System.out.println(new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getDisplayName() + " : " + new ItemStack(BWMBlocks.SINGLE_MACHINES, 1, 0).getTranslationKey());
    */}

}