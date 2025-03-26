package com.pippip5789.mfrex.core.util;

import com.pippip5789.mfrex.core.MFREx;
import com.pippip5789.mfrex.core.init.MFRExBlockInit;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(MFREx.MODID)
@Mod.EventBusSubscriber(modid = MFREx.MODID)
public class BlockIniter {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event) {
        MFRExBlockInit.register(event);

        if(Loader.isModLoaded("charcoal_pit")) {
            //PitsOfCharcoalFantasyBlockInit.register(event);
        }
    }

}