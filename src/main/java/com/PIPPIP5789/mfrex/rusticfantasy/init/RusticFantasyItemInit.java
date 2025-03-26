package com.pippip5789.mfrex.rusticfantasy.init;

import minefantasy.mfr.init.MineFantasyItems;
import minefantasy.mfr.item.ItemFoodMFR;
import minefantasy.mfr.item.ItemMultiFood;
import minefantasy.mfr.item.ItemUnfinishedFood;
import minefantasy.mfr.util.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import rustic.common.blocks.crops.BlockHerbBase;
import rustic.common.blocks.crops.Herbs;
import rustic.common.items.ItemHerbEdible;
import rustic.common.items.ModItems;

import static rustic.common.blocks.crops.Herbs.CLOUDSBLUFF_CROP;

public class RusticFantasyItemInit {

    public static Item veggieSandwitch = Utils.nullValue();
    public static Item salad = Utils.nullValue();
    public static Item grapeJam = Utils.nullValue();
    public static Item grapeJamRoll = Utils.nullValue();

    public static Item rawPastaNoodles = Utils.nullValue();
    public static Item pastaNoodles = Utils.nullValue();
    public static Item tomatoSauce = Utils.nullValue();
    public static Item meatSauce = Utils.nullValue();
    public static Item pasta = Utils.nullValue();
    public static Item meatPasta = Utils.nullValue();

    //public static ItemHerbEdible garlic = Utils.nullValue();

    public static void initOreDict() throws ClassNotFoundException {
        OreDictionary.registerOre("berry", ModItems.WILDBERRIES);
        OreDictionary.registerOre("dyeWhite", new ItemStack(Items.DYE, 1, 15));
        OreDictionary.registerOre("dyeOrange", new ItemStack(Items.DYE, 1, 14));
        OreDictionary.registerOre("dyeMagenta", new ItemStack(Items.DYE, 1, 15));
        OreDictionary.registerOre("dyeLightBlue", new ItemStack(Items.DYE, 1, 12));
        OreDictionary.registerOre("dyeYellow", new ItemStack(Items.DYE, 1, 11));
        OreDictionary.registerOre("dyeGreen", new ItemStack(Items.DYE, 1, 10));
        OreDictionary.registerOre("dyePink", new ItemStack(Items.DYE, 1, 9));
        OreDictionary.registerOre("dyeGrey", new ItemStack(Items.DYE, 1, 8));
        OreDictionary.registerOre("dyeCyan", new ItemStack(Items.DYE, 1, 6));
        OreDictionary.registerOre("dyeBlue", new ItemStack(Items.DYE, 1, 4));
        OreDictionary.registerOre("dyeBrown", new ItemStack(Items.DYE, 1, 3));
        OreDictionary.registerOre("dyeGreen", new ItemStack(Items.DYE, 1, 2));
        OreDictionary.registerOre("dyeBlack", new ItemStack(Items.DYE, 1, 0));
    }

    public static void initItems() {
        grapeJam = new ItemFoodMFR("jam_grape", 3, 2, false).setReturnItem(MineFantasyItems.CLAY_POT).setMaxStackSize(8);;
        grapeJamRoll = new ItemFoodMFR("jam_grape_roll", 5, 2, false).setFoodStats(2, 0.5F, 0.3F, 0.4F).setMaxStackSize(4);;

        veggieSandwitch = new ItemMultiFood("sandwitch_veggie", 2, 5, 5, false, 1).setFoodStats(1, 0.1F, 1.0F, 0.3F).setMaxStackSize(1);
        salad = new ItemFoodMFR("salad", 6, 4, false).setFoodStats(1, 0.1F, 0.2F, 0.0F).setReturnItem(Items.BOWL).setMaxStackSize(1);

        rawPastaNoodles = new ItemUnfinishedFood("pasta_noodles_raw").setMaxStackSize(64);
        pastaNoodles = new ItemFoodMFR("pasta_noodles", 2, 0, false);
        tomatoSauce = new ItemFoodMFR("tomato_sauce", 2, 1, false).setReturnItem(Items.BOWL).setMaxStackSize(1);
        meatSauce = new ItemFoodMFR("meat_sauce", 6, 4, true).setReturnItem(Items.BOWL).setMaxStackSize(1);
        pasta = new ItemFoodMFR("pasta", 6, 2, false).setFoodStats(1, 0.1F, 0.8F, 0.4F).setReturnItem(Items.BOWL).setMaxStackSize(1);
        meatPasta = new ItemFoodMFR("pasta_meat", 8, 4, true).setFoodStats(2, 0.1F, 1.0F, 0.6F).setReturnItem(Items.BOWL).setMaxStackSize(1);
    }

    public static void initHerbs() {
        //garlic = new ItemHerbEdible(RusticFantasyBlockInit.garlic_crop, 2, 2);
    }

    public static void register(RegistryEvent.Register<Item> event) {
        System.out.println("RusticFantasy registering items");

        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(grapeJam);
        registry.register(grapeJamRoll);
        registry.register(veggieSandwitch);
        registry.register(salad);

        registry.register(rawPastaNoodles);
        /*registry.register(pastaNoodles);
        registry.register(tomatoSauce);
        registry.register(meatSauce);
        registry.register(pasta);
        registry.register(meatPasta);*/
    }

}
