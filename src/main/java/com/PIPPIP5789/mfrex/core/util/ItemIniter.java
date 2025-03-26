package com.PIPPIP5789.mfrex.core.util;

import com.PIPPIP5789.mfrex.betterwithfantasy.init.BetterWithFantasyItemInit;
import com.PIPPIP5789.mfrex.core.MFREx;
import com.PIPPIP5789.mfrex.core.init.MFRExItemInit;
import com.PIPPIP5789.mfrex.rusticfantasy.init.RusticFantasyItemInit;
import com.PIPPIP5789.mfrex.spartanfantasy.init.SpartanFantasyItemInit;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(MFREx.MODID)
@Mod.EventBusSubscriber(modid = MFREx.MODID)
public class ItemIniter {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        MFRExItemInit.register(event);

        if(Loader.isModLoaded("rustic")) {
            RusticFantasyItemInit.register(event);
        }
        if(Loader.isModLoaded("betterwithmods")) {
            BetterWithFantasyItemInit.register(event);
        }
        if(Loader.isModLoaded("spartanweaponry")) {
            SpartanFantasyItemInit.register(event);
        }
        if(Loader.isModLoaded("charcoal_pit")) {
            //PitsOfCharcoalFantasyItemInit.register(event);
        }
    }

}
