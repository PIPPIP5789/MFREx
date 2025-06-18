package com.pippip5789.mfrex.core.content.tailor;

import net.minecraft.util.IStringSerializable;

public enum TailorBenchRecipeType implements IStringSerializable {
    LOOM_SHAPED_RECIPE,
    LOOM_SHAPELESS_RECIPE,
    NONE;

    private TailorBenchRecipeType() {
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public static TailorBenchRecipeType deserialize(String name) {
        TailorBenchRecipeType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TailorBenchRecipeType type = var1[var3];
            if (type.getName().equals(name)) {
                return type;
            }
        }

        return NONE;
    }

    public static TailorBenchRecipeType getByNameWithModId(String name, String modId) {
        TailorBenchRecipeType[] var2 = values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            TailorBenchRecipeType type = var2[var4];
            if ((modId + ":" + type.getName()).equals(name)) {
                return type;
            }
        }

        return NONE;
    }
}