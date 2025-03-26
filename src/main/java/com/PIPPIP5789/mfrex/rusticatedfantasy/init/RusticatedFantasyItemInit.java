package com.pippip5789.mfrex.rusticatedfantasy.init;

import minefantasy.mfr.init.MineFantasyItems;
import minefantasy.mfr.item.ItemFoodMFR;
import minefantasy.mfr.util.Utils;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RusticatedFantasyItemInit {

    public static Item peachJam = Utils.nullValue();
    public static Item pearJam = Utils.nullValue();
    public static Item persimmonJam = Utils.nullValue();

    public static Item peachJamRoll = Utils.nullValue();
    public static Item pearJamRoll = Utils.nullValue();
    public static Item persimmonJamRoll = Utils.nullValue();

    public static void initOreDict() throws ClassNotFoundException {
        //OreDictionary.registerOre("berry", ModItems.WILDBERRIES);
    }

    public static void initItems() {
        peachJam = new ItemFoodMFR("jam_peach", 4, 2, false).setReturnItem(MineFantasyItems.CLAY_POT).setMaxStackSize(8);
        pearJam = new ItemFoodMFR("jam_pear", 4, 2, false).setReturnItem(MineFantasyItems.CLAY_POT).setMaxStackSize(8);
        persimmonJam = new ItemFoodMFR("jam_persimmon", 4, 2, false).setReturnItem(MineFantasyItems.CLAY_POT).setMaxStackSize(8);

        peachJamRoll = new ItemFoodMFR("jam_peach_roll", 6, 2, false).setFoodStats(2, 0.5F, 0.4F, 0.6F).setMaxStackSize(4);
        pearJamRoll = new ItemFoodMFR("jam_pear_roll", 6, 2, false).setFoodStats(2, 0.5F, 0.4F, 0.6F).setMaxStackSize(4);
        persimmonJamRoll = new ItemFoodMFR("jam_persimmon_roll", 6, 2, false).setFoodStats(2, 0.5F, 0.4F, 0.6F).setMaxStackSize(4);
        //veggieSandwitch = new ItemMultiFood("sandwitch_veggie", 2, 5, 5, false, 1).setFoodStats(1, 0.1F, 1.0F, 0.3F).setMaxStackSize(1);
    }

    public static void register(RegistryEvent.Register<Item> event) {
        System.out.println("RusticatedFantasy registering items");

        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(peachJam);
        registry.register(pearJam);
        registry.register(persimmonJam);

        registry.register(peachJamRoll);
        registry.register(pearJamRoll);
        registry.register(persimmonJamRoll);
    }

}
