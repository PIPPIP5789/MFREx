package com.PIPPIP5789.mfrex.pits_of_fantasy_charcoal.init;

import minefantasy.mfr.init.MineFantasyItems;
import minefantasy.mfr.item.ItemFoodMFR;
import minefantasy.mfr.item.ItemMultiFood;
import minefantasy.mfr.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import rustic.common.items.ItemHerbEdible;
import rustic.common.items.ModItems;

import static com.PIPPIP5789.mfrex.pits_of_fantasy_charcoal.init.PitsOfCharcoalFantasyBlockInit.firebrickCreosoteCollector;

public class PitsOfCharcoalFantasyItemInit {

    public static ItemBlock firebrickCreosoteCollectorItem;

    public static void initOreDict() throws ClassNotFoundException {
    }

    public static void initItems() {
        //firebrickCreosoteCollectorItem = new ItemBlock(firebrickCreosoteCollector);
        //firebrickCreosoteCollectorItem.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public static void initHerbs() {
        //garlic = new ItemHerbEdible(RusticatedFantasyBlockInit.garlic_crop, 2, 2);
    }

    public static void register(RegistryEvent.Register<Item> event) {
        //event.getRegistry().register(firebrickCreosoteCollectorItem.setRegistryName(firebrickCreosoteCollector.getRegistryName()));
    }

}
