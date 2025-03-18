package com.PIPPIP5789.mfrex.core.content.masonry;

import net.minecraft.util.IStringSerializable;

public enum MasonryBenchRecipeType implements IStringSerializable {
    MASONRY_BENCH_SHAPED_RECIPE,
    MASONRY_BENCH_SHAPELESS_RECIPE,
    NONE;

    private MasonryBenchRecipeType() {
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public static MasonryBenchRecipeType deserialize(String name) {
        MasonryBenchRecipeType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MasonryBenchRecipeType type = var1[var3];
            if (type.getName().equals(name)) {
                return type;
            }
        }

        return NONE;
    }

    public static MasonryBenchRecipeType getByNameWithModId(String name, String modId) {
        MasonryBenchRecipeType[] var2 = values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            MasonryBenchRecipeType type = var2[var4];
            if ((modId + ":" + type.getName()).equals(name)) {
                return type;
            }
        }

        return NONE;
    }
}