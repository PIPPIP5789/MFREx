package com.pippip5789.mfrex.pyrofantasy.init;

import com.codetaylor.mc.pyrotech.PyrotechAPI;
import minefantasy.mfr.init.MineFantasyItems;

public class ToolRegistryInit {

    public static void registerHammers() {
        PyrotechAPI.registerHammer(MineFantasyItems.STANDARD_HAMMER, 2);
        PyrotechAPI.registerHammer(MineFantasyItems.ORNATE_HAMMER, 2);
        PyrotechAPI.registerHammer(MineFantasyItems.DRAGONFORGED_HAMMER, 2);
    }

}
