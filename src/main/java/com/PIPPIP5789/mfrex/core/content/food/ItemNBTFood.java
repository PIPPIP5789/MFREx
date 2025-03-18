package com.PIPPIP5789.mfrex.core.content.food;

import minefantasy.mfr.item.ItemFoodMFR;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemNBTFood extends ItemFoodMFR {

    protected String[] components;

    public ItemNBTFood(String name, int hunger, float saturation, boolean isMeat) {
        super(name, hunger, saturation, isMeat);
    }

    public String[] getComponents() {
        return components;
    }

    // ----------------------------------------

    /*public static ItemStack getMaterial(String name) {
        if (name == null) {
            return new ItemStack(Blocks.AIR, 1);
        }

        ResourceLocation key = new ResourceLocation(MineFantasyReforged.MOD_ID, name.toLowerCase());
        CustomMaterial material = CUSTOM_MATERIALS.getValue(key);
        if (material == null) {
            return NONE;
        }
        else {
            return material;
        }
    }

    public static void addMaterial(ItemStack item, String slot, String material) {
        if (material == null || material.isEmpty()) {
            return;
        }
        NBTTagCompound nbt = getNBT(item, true);
        nbt.setString(slot, material);
    }

    public static CustomMaterial getMaterialFor(ItemStack item, String slot) {
        NBTTagCompound nbt = getNBT(item, false);
        if (nbt != null) {
            if (nbt.hasKey(slot)) {
                return getMaterial(nbt.getString(slot));
            }
        }
        return NONE;
    }*/

    public static NBTTagCompound getNBT(ItemStack item, boolean createNew, String tag) {
        if(!item.isEmpty() && item.hasTagCompound()) {
            System.out.println("Wakady -> " + item.getTagCompound());
        }
        if (!item.isEmpty() && item.hasTagCompound() && item.getTagCompound().hasKey(tag)) {
            return (NBTTagCompound) item.getTagCompound().getTag(tag);
        }
        if (createNew) {
            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound nbt2 = new NBTTagCompound();
            item.setTagCompound(nbt);
            nbt.setTag(tag, nbt2);
            return nbt2;
        }
        return null;
    }

}
