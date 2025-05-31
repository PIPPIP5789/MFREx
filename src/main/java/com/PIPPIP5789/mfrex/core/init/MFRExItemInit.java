package com.pippip5789.mfrex.core.init;

import minefantasy.mfr.init.MineFantasyItems;
import minefantasy.mfr.item.ItemBlockBase;
import minefantasy.mfr.item.ItemFoodMFR;
import minefantasy.mfr.util.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class MFRExItemInit {

    //public static Item rawStrips = Utils.nullValue();

    /*public static Item rawBeefStrips = Utils.nullValue();
    public static Item cookedBeefStrips = Utils.nullValue();
    public static Item rawPorkStrips = Utils.nullValue();
    public static Item cookedPorkStrips = Utils.nullValue();
    public static Item rawMuttonStrips = Utils.nullValue();
    public static Item cookedMuttonStrips = Utils.nullValue();
    public static Item rawChickenStrips = Utils.nullValue();
    public static Item cookedChickenStrips = Utils.nullValue();
    public static Item rawRabbitStrips = Utils.nullValue();
    public static Item cookedRabbitStrips = Utils.nullValue();
    public static Item rawFishStrips = Utils.nullValue();
    public static Item cookedFishStrips = Utils.nullValue();
    public static Item rawSalmonStrips = Utils.nullValue();
    public static Item cookedSalmonStrips = Utils.nullValue();
    public static Item rawWolfStrips = Utils.nullValue();
    public static Item cookedWolfStrips = Utils.nullValue();
    public static Item rawHorseStrips = Utils.nullValue();
    public static Item cookedHorseStrips = Utils.nullValue();

    public static Item rawBeefChunks = Utils.nullValue();
    public static Item cookedBeefChunks = Utils.nullValue();
    public static Item rawPorkChunks = Utils.nullValue();
    public static Item cookedPorkChunks = Utils.nullValue();
    public static Item rawMuttonChunks = Utils.nullValue();
    public static Item cookedMuttonChunks = Utils.nullValue();
    public static Item rawChickenChunks = Utils.nullValue();
    public static Item cookedChickenChunks = Utils.nullValue();
    public static Item rawRabbitChunks = Utils.nullValue();
    public static Item cookedRabbitChunks = Utils.nullValue();
    public static Item rawFishChunks = Utils.nullValue();
    public static Item cookedFishChunks = Utils.nullValue();
    public static Item rawSalmonChunks = Utils.nullValue();
    public static Item cookedSalmonChunks = Utils.nullValue();
    public static Item rawWolfChunks = Utils.nullValue();
    public static Item cookedWolfChunks = Utils.nullValue();
    public static Item rawHorseChunks = Utils.nullValue();
    public static Item cookedHorseChunks = Utils.nullValue();

    public static Item rawBeefMince = Utils.nullValue();
    public static Item cookedBeefMince = Utils.nullValue();
    public static Item rawPorkMince = Utils.nullValue();
    public static Item cookedPorkMince = Utils.nullValue();
    public static Item rawMuttonMince = Utils.nullValue();
    public static Item cookedMuttonMince = Utils.nullValue();
    public static Item rawChickenMince = Utils.nullValue();
    public static Item cookedChickenMince = Utils.nullValue();
    public static Item rawRabbitMince = Utils.nullValue();
    public static Item cookedRabbitMince = Utils.nullValue();
    public static Item rawFishMince = Utils.nullValue();
    public static Item cookedFishMince = Utils.nullValue();
    public static Item rawSalmonMince = Utils.nullValue();
    public static Item cookedSalmonMince = Utils.nullValue();
    public static Item rawWolfMince = Utils.nullValue();
    public static Item cookedWolfMince = Utils.nullValue();
    public static Item rawHorseMince = Utils.nullValue();
    public static Item cookedHorseMince = Utils.nullValue();*/

    public static Item sandwich = Utils.nullValue();
    public static Item sandwichBig = Utils.nullValue();

    public static Item appleJam = Utils.nullValue();
    public static Item berryJam = Utils.nullValue();
    public static Item appleJamRoll = Utils.nullValue();
    public static Item berryJamRoll = Utils.nullValue();
    //public static Item driedBerries = Utils.nullValue();
    public static Item carrotSlices = Utils.nullValue();
    public static Item potatoSlices = Utils.nullValue();
    public static Item pottage = Utils.nullValue();

    public static void initOreDict() {
        OreDictionary.registerOre("berry", MineFantasyItems.BERRIES);
        OreDictionary.registerOre("carrot", Items.CARROT);
        OreDictionary.registerOre("potato", Items.POTATO);
    }

    public static void initItems() {
        //rawStrips = new ItemFoodMFR("raw_strips", 2, 0.2F, true);

        /*rawBeefStrips = new ItemFoodMFR("raw_strips_beef", 2, 0.2F, true);
        cookedBeefStrips = new ItemFoodMFR("cooked_strips_beef", 5, 0.5F, true);
        rawPorkStrips = new ItemFoodMFR("raw_strips_pork", 2, 0.2F, true);
        cookedPorkStrips = new ItemFoodMFR("cooked_strips_pork", 5, 0.5F, true);
        rawMuttonStrips = new ItemFoodMFR("raw_strips_mutton", 2, 0.2F, true);
        cookedMuttonStrips = new ItemFoodMFR("cooked_strips_mutton", 5, 0.5F, true);
        rawChickenStrips = new ItemFoodMFR("raw_strips_chicken", 2, 0.2F, true);
        cookedChickenStrips = new ItemFoodMFR("cooked_strips_chicken", 5, 0.5F, true);
        rawRabbitStrips = new ItemFoodMFR("raw_strips_rabbit", 2, 0.2F, true);
        cookedRabbitStrips = new ItemFoodMFR("cooked_strips_rabbit", 5, 0.5F, true);
        rawFishStrips = new ItemFoodMFR("raw_strips_fish", 2, 0.2F, true);
        cookedFishStrips = new ItemFoodMFR("cooked_strips_fish", 5, 0.5F, true);
        rawSalmonStrips = new ItemFoodMFR("raw_strips_salmon", 2, 0.2F, true);
        cookedSalmonStrips = new ItemFoodMFR("cooked_strips_salmon", 5, 0.5F, true);
        rawWolfStrips = new ItemFoodMFR("raw_strips_wolf", 2, 0.2F, true);
        cookedWolfStrips = new ItemFoodMFR("cooked_strips_wolf", 5, 0.5F, true);
        rawHorseStrips = new ItemFoodMFR("raw_strips_horse", 2, 0.2F, true);
        cookedHorseStrips = new ItemFoodMFR("cooked_strips_horse", 5, 0.5F, true);

        rawBeefChunks = new ItemFoodMFR("raw_chunks_beef", 2, 0.2F, true);
        cookedBeefChunks = new ItemFoodMFR("cooked_chunks_beef", 5, 0.5F, true);
        rawPorkChunks = new ItemFoodMFR("raw_chunks_pork", 2, 0.2F, true);
        cookedPorkChunks = new ItemFoodMFR("cooked_chunks_pork", 5, 0.5F, true);
        rawMuttonChunks = new ItemFoodMFR("raw_chunks_mutton", 2, 0.2F, true);
        cookedMuttonChunks = new ItemFoodMFR("cooked_chunks_mutton", 5, 0.5F, true);
        rawChickenChunks = new ItemFoodMFR("raw_chunks_chicken", 2, 0.2F, true);
        cookedChickenChunks = new ItemFoodMFR("cooked_chunks_chicken", 5, 0.5F, true);
        rawRabbitChunks = new ItemFoodMFR("raw_chunks_rabbit", 2, 0.2F, true);
        cookedRabbitChunks = new ItemFoodMFR("cooked_chunks_rabbit", 5, 0.5F, true);
        rawFishChunks = new ItemFoodMFR("raw_chunks_fish", 2, 0.2F, true);
        cookedFishChunks = new ItemFoodMFR("cooked_chunks_fish", 5, 0.5F, true);
        rawSalmonChunks = new ItemFoodMFR("raw_chunks_salmon", 2, 0.2F, true);
        cookedSalmonChunks = new ItemFoodMFR("cooked_chunks_salmon", 5, 0.5F, true);
        rawWolfChunks = new ItemFoodMFR("raw_chunks_wolf", 2, 0.2F, true);
        cookedWolfChunks = new ItemFoodMFR("cooked_chunks_wolf", 5, 0.5F, true);
        rawHorseChunks = new ItemFoodMFR("raw_chunks_horse", 2, 0.2F, true);
        cookedHorseChunks = new ItemFoodMFR("cooked_chunks_horse", 5, 0.5F, true);

        rawBeefMince = new ItemFoodMFR("raw_mince_beef", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedBeefMince = new ItemFoodMFR("cooked_mince_beef", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawPorkMince = new ItemFoodMFR("raw_mince_pork", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedPorkMince = new ItemFoodMFR("cooked_mince_pork", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawMuttonMince = new ItemFoodMFR("raw_mince_mutton", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedMuttonMince = new ItemFoodMFR("cooked_mince_mutton", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawChickenMince = new ItemFoodMFR("raw_mince_chicken", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedChickenMince = new ItemFoodMFR("cooked_mince_chicken", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawRabbitMince = new ItemFoodMFR("raw_mince_rabbit", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedRabbitMince = new ItemFoodMFR("cooked_mince_rabbit", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawFishMince = new ItemFoodMFR("raw_mince_fish", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedFishMince = new ItemFoodMFR("cooked_mince_fish", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawSalmonMince = new ItemFoodMFR("raw_mince_salmon", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedSalmonMince = new ItemFoodMFR("cooked_mince_salmon", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawWolfMince = new ItemFoodMFR("raw_mince_wolf", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedWolfMince = new ItemFoodMFR("cooked_mince_wolf", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        rawHorseMince = new ItemFoodMFR("raw_mince_horse", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        cookedHorseMince = new ItemFoodMFR("cooked_mince_horse", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);*/

        //beefSandwich = new ItemFoodMFR("raw_mince_beef", 2, 0.2F, true).setContainerItem(MineFantasyItems.CLAY_POT);
        //beefSandwichBig = new ItemFoodMFR("cooked_mince_beef", 5, 0.5F, true).setContainerItem(MineFantasyItems.CLAY_POT);

        appleJam = new ItemFoodMFR("jam_apple", 4, 2, false).setReturnItem(MineFantasyItems.CLAY_POT).setMaxStackSize(8);
        berryJam = new ItemFoodMFR("jam_berry", 2, 1, false).setReturnItem(MineFantasyItems.CLAY_POT).setMaxStackSize(8);;

        appleJamRoll = new ItemFoodMFR("jam_apple_roll", 6, 2, false).setFoodStats(2, 0.5F, 0.4F, 0.6F).setMaxStackSize(4);
        berryJamRoll = new ItemFoodMFR("jam_berry_roll", 4, 2, false).setFoodStats(2, 0.5F, 0.2F, 0.3F).setMaxStackSize(4);;

        //driedBerries = new ItemFoodMFR("berries_dried", 1, 1, false).setFoodStats(1, 0.1F, 0.1F, 0.1F);

        carrotSlices = new ItemFoodMFR("slices_carrot", 2, 2, false);
        potatoSlices = new ItemFoodMFR("slices_potato", 1, 1, false);

        pottage = new ItemFoodMFR("pottage",10, 12, false, 0).setFoodStats(2, 0.0F, 2.0F, 1.5F).setReturnItem(Items.BOWL).setMaxStackSize(1);
    }

    public static void register(RegistryEvent.Register<Item> event) {
        System.out.println("MFREx registering items");

        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(new ItemBlockBase(MFRExBlockInit.masonryBench));

        /*registry.register(rawBeefStrips);
        registry.register(cookedBeefStrips);
        registry.register(rawPorkStrips);
        registry.register(cookedPorkStrips);
        registry.register(rawMuttonStrips);
        registry.register(cookedMuttonStrips);
        registry.register(rawChickenStrips);
        registry.register(cookedChickenStrips);
        registry.register(rawRabbitStrips);
        registry.register(cookedRabbitStrips);
        registry.register(rawFishStrips);
        registry.register(cookedFishStrips);
        registry.register(rawSalmonStrips);
        registry.register(cookedSalmonStrips);
        registry.register(rawWolfStrips);
        registry.register(cookedWolfStrips);
        registry.register(rawHorseStrips);
        registry.register(cookedHorseStrips);

        registry.register(rawBeefChunks);
        registry.register(cookedBeefChunks);
        registry.register(rawPorkChunks);
        registry.register(cookedPorkChunks);
        registry.register(rawMuttonChunks);
        registry.register(cookedMuttonChunks);
        registry.register(rawChickenChunks);
        registry.register(cookedChickenChunks);
        registry.register(rawRabbitChunks);
        registry.register(cookedRabbitChunks);
        registry.register(rawFishChunks);
        registry.register(cookedFishChunks);
        registry.register(rawSalmonChunks);
        registry.register(cookedSalmonChunks);
        registry.register(rawWolfChunks);
        registry.register(cookedWolfChunks);
        registry.register(rawHorseChunks);
        registry.register(cookedHorseChunks);

        registry.register(rawBeefMince);
        registry.register(cookedBeefMince);
        registry.register(rawPorkMince);
        registry.register(cookedPorkMince);
        registry.register(rawMuttonMince);
        registry.register(cookedMuttonMince);
        registry.register(rawChickenMince);
        registry.register(cookedChickenMince);
        registry.register(rawRabbitMince);
        registry.register(cookedRabbitMince);
        registry.register(rawFishMince);
        registry.register(cookedFishMince);
        registry.register(rawSalmonMince);
        registry.register(cookedSalmonMince);
        registry.register(rawWolfMince);
        registry.register(cookedWolfMince);
        registry.register(rawHorseMince);
        registry.register(cookedHorseMince);*/

        registry.register(appleJam);
        registry.register(berryJam);
        registry.register(appleJamRoll);
        registry.register(berryJamRoll);

        //registry.register(driedBerries);

        registry.register(carrotSlices);
        registry.register(potatoSlices);

        registry.register(pottage);
    }

}
