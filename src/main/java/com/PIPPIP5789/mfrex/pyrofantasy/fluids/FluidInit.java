package com.PIPPIP5789.mfrex.pyrofantasy.fluids;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FluidInit {

    public static MoltenFluid molten_steel;
    public static MoltenFluid molten_pig_iron;
    public static MoltenFluid molten_black_steel;
    public static MoltenFluid molten_blue_steel;
    public static MoltenFluid molten_red_steel;
    public static MoltenFluid molten_adamantium;
    public static MoltenFluid molten_mithril;
    public static MoltenFluid molten_ignotumite;
    public static MoltenFluid molten_mithium;
    public static MoltenFluid molten_enderforge;
    public static MoltenFluid molten_tungsten;

    public static void preInitFluids(FMLPreInitializationEvent event) {
        initFluids();
        registerFluids();
        addFluidRecipes();
    }

    private static void initFluids() {
        //molten_blue_steel = new MoltenFluid("molten_blue_steel", new Color(13, 13, 217));
    }

    private static void registerFluids() {
        //FluidRegistry.registerFluid(molten_blue_steel);
        //FluidRegistry.addBucketForFluid(molten_blue_steel);
    }

    private static void addFluidRecipes() {
        //RecipeRegistry.meltingRecipes.add(new ItemMeltingRecipe(Ingredient.fromItem(MineFantasyItems.BLUE_STEEL_INGOT), new FluidStack(molten_blue_steel, RecipeRegistry.INGOT_AMOUNT)));
    }

}
