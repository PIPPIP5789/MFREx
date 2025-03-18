package com.PIPPIP5789.mfrex.core.util;

import java.util.Random;

public class FoodBonus {

    private static float beefCarb, beefFat;
    private static float porkCarb, porkFat;
    private static float muttonCarb, muttonFat;
    private static float chickenCarb, chickenFat;
    private static float rabbitCarb, rabbitFat;
    private static float fishCarb, fishFat;
    private static float salmonCarb, salmonFat;
    private static float wolfCarb, wolfFat;
    private static float horseCarb, horseFat;

    public static void preInit(long seed) {
        Random rand = new Random();
        rand.setSeed(seed);

        beefCarb += rand.nextFloat() * (0.4F - beefCarb);
        beefFat += rand.nextFloat() * (0.5F - beefFat);

        porkCarb += rand.nextFloat() * (0.4F - porkCarb);
        porkFat += rand.nextFloat() * (0.4F - porkFat);

        muttonCarb += rand.nextFloat() * (0.3F - muttonCarb);
        muttonFat += rand.nextFloat() * (0.3F - muttonCarb);

        chickenCarb += rand.nextFloat() * (0.15F - chickenCarb);
        chickenFat += rand.nextFloat() * (0.15F - chickenCarb);

        rabbitCarb += rand.nextFloat() * (0.2F - rabbitCarb);
        rabbitFat += rand.nextFloat() * (0.1F - rabbitFat);

        fishCarb += rand.nextFloat() * (0.3F - fishCarb);
        fishFat += rand.nextFloat() * (0.25F - fishFat);

        salmonCarb += rand.nextFloat() * (0.35F - salmonCarb);
        salmonFat += rand.nextFloat() * (0.3F - salmonFat);

        wolfCarb += rand.nextFloat();
        wolfFat += rand.nextFloat();

        horseCarb += rand.nextFloat();
        horseFat += rand.nextFloat();
    }

    public float getBeefCarb() {
        return beefCarb;
    }

    public float getBeefFat() {
        return beefFat;
    }

    public float getPorkCarb() {
        return porkCarb;
    }

    public float getPorkFat() {
        return porkFat;
    }

    public float getMuttonCarb() {
        return muttonCarb;
    }

    public float getMuttonFat() {
        return muttonFat;
    }

    public float getChickenCarb() {
        return chickenCarb;
    }

    public float getChickenFat() {
        return chickenFat;
    }

    public float getRabbitCarb() {
        return rabbitCarb;
    }

    public float getRabbitFat() {
        return rabbitFat;
    }

    public float getFishCarb() {
        return fishCarb;
    }

    public float getFishFat() {
        return fishFat;
    }

    public float getSalmonCarb() {
        return salmonCarb;
    }

    public float getSalmonFat() {
        return salmonFat;
    }

    public float getWolfCarb() {
        return wolfCarb;
    }

    public float getWolfFat() {
        return wolfFat;
    }

    public float getHorseCarb() {
        return horseCarb;
    }

    public float getHorseFat() {
        return horseFat;
    }

}
