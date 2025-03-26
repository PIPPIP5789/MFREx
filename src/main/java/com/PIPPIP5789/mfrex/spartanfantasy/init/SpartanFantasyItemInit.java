package com.pippip5789.mfrex.spartanfantasy.init;

import com.pippip5789.mfrex.core.MFREx;
import com.pippip5789.mfrex.spartanfantasy.utils.SpartanFantasyMatConverter;
import com.pippip5789.mfrex.spartanfantasy.utils.SpartanFantasyUtils;
import com.oblivioussp.spartanweaponry.api.DamageHelper;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import static minefantasy.mfr.init.MineFantasyMaterials.*;

public class SpartanFantasyItemInit {

    public static final Set<SpartanFantasyMatConverter> MATERIALS_TO_REGISTER = new HashSet<>();

    //public static final ToolMaterialEx BLACK_STEEL = new SpartanFantasyMatConverter(BLACK_STEEL_ID, SpartanFantasyUtils.spartanMatFromToolMat(BLACK_STEEL_ID, Item.ToolMaterial.IRON, 2565927, 7500402));
    private static final Set<Item> item_set = new HashSet<>();

    static {
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(PIG_IRON.name, SpartanFantasyUtils.spartanMatFromToolMat(PIG_IRON.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(OBSIDIAN.name, SpartanFantasyUtils.spartanMatFromToolMat(OBSIDIAN.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(BLACK_STEEL.name, SpartanFantasyUtils.spartanMatFromToolMat(BLACK_STEEL.name, Item.ToolMaterial.IRON, 2763049, 1381653)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(RED_STEEL.name, SpartanFantasyUtils.spartanMatFromToolMat(RED_STEEL.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(BLUE_STEEL.name, SpartanFantasyUtils.spartanMatFromToolMat(BLUE_STEEL.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(ADAMANTIUM.name, SpartanFantasyUtils.spartanMatFromToolMat(ADAMANTIUM.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(MITHRIL.name, SpartanFantasyUtils.spartanMatFromToolMat(MITHRIL.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(IGNOTUMITE.name, SpartanFantasyUtils.spartanMatFromToolMat(IGNOTUMITE.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(MITHIUM.name, SpartanFantasyUtils.spartanMatFromToolMat(MITHIUM.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(ENDERFORGE.name, SpartanFantasyUtils.spartanMatFromToolMat(ENDERFORGE.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(TUNGSTEN.name, SpartanFantasyUtils.spartanMatFromToolMat(TUNGSTEN.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
        MATERIALS_TO_REGISTER.add(new SpartanFantasyMatConverter(COMPOSITE_ALLOY.name, SpartanFantasyUtils.spartanMatFromToolMat(COMPOSITE_ALLOY.name, Item.ToolMaterial.IRON, 10286224, 2063670)));
    }
    
    public static void initItems() {

        for (SpartanFantasyMatConverter mat : MATERIALS_TO_REGISTER) {
            /*ItemParryingDagger;
            ItemQuarterstaff;
            ItemScythe;

            ItemParryingDagger parryingDagger = new ItemParryingDagger("spartanfantasy_parrying_dagger_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.PARRYING_DAGGER, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(, glaive.getRegistryName(), mat.material);
            item_set.add(glaive);

            ItemGlaive glaive = new ItemGlaive("spartanfantasy_glaive_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.GLAIVE, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(glaive, glaive.getRegistryName(), mat.material);
            item_set.add(glaive);

            ItemBoomerang boomerang = new ItemBoomerang("spartanfantasy_boomerang_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.BOOMERANG, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(boomerang, boomerang.getRegistryName(), mat.material);
            item_set.add(boomerang);*/

            ItemLongsword longsword = new ItemLongsword("spartanfantasy_longsword_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.LONGSWORD, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(longsword, longsword.getRegistryName(), mat.material);
            item_set.add(longsword);

            ItemSaber saber = new ItemSaber("spartanfantasy_saber_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.SABER, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(saber, saber.getRegistryName(), mat.material);
            item_set.add(saber);

            ItemRapier rapier = new ItemRapier("spartanfantasy_rapier_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.RAPIER, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(rapier, rapier.getRegistryName(), mat.material);
            item_set.add(rapier);

            ItemPike pike = new ItemPike("spartanfantasy_pike_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.PIKE, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(pike, pike.getRegistryName(), mat.material);
            item_set.add(pike);

            ItemThrowingAxe throwing_axe = new ItemThrowingAxe("spartanfantasy_throwing_axe_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.THROWING_AXE, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(throwing_axe, throwing_axe.getRegistryName(), mat.material);
            item_set.add(throwing_axe);

            ItemThrowingKnife throwing_knife = new ItemThrowingKnife("spartanfantasy_throwing_knife_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.THROWING_KNIFE, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(throwing_knife, throwing_knife.getRegistryName(), mat.material);
            item_set.add(throwing_knife);

            ItemLongbow longbow = new ItemLongbow("spartanfantasy_longbow_" + mat.name, MFREx.MODID, mat.material);
            ModelRenderRegistry.addItemToRegistry(longbow, longbow.getRegistryName(), mat.material);
            item_set.add(longbow);

            ItemJavelin javelin = new ItemJavelin("spartanfantasy_javelin_" + mat.name, MFREx.MODID, mat.material, DamageHelper.getDamage(DamageHelper.WeaponType.JAVELIN, mat.material.getMaterial().getAttackDamage()));
            ModelRenderRegistry.addItemToRegistry(javelin, javelin.getRegistryName(), mat.material);
            item_set.add(javelin);

            SpartanWeaponryAPI.registerColourHandler(mat.material, longsword, saber, rapier, pike, throwing_axe, throwing_knife, longbow, javelin);
        }
        for (Item item : item_set) {
            item.setCreativeTab(CreativeTabsSW.TAB_SW);
        }
    }

    public static void register(RegistryEvent.Register<Item> event) {
        System.out.println("SpartanFantasy registering items");

        IForgeRegistry<Item> registry = event.getRegistry();

        for (Item item : item_set) {
            registry.register(item);
        }
    }

}
