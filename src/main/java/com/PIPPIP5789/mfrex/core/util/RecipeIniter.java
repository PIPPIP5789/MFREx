package com.pippip5789.mfrex.core.util;

import betterwithmods.BWMod;
import com.pippip5789.mfrex.core.MFREx;
import com.google.common.collect.Lists;
import minefantasy.mfr.recipe.DummyRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@GameRegistry.ObjectHolder(MFREx.MODID)
@Mod.EventBusSubscriber(modid = MFREx.MODID)
public abstract class RecipeIniter {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if(Loader.isModLoaded(BWMod.MODID)) {
            //BetterWithFantasyRecipeInit.registerBWMRecipes();
        }
    }

    //To be used on stubborn recipes that don't get removed by just calling .remove
    protected static void manualRemover(Item item) {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;
        ArrayList<IRecipe> recipe = Lists.newArrayList(recipeRegistry.getValues());
        for (IRecipe r : recipe) {
            ItemStack output = r.getRecipeOutput();
            if (output.getItem() == item) {
                recipeRegistry.remove(r.getRegistryName());
            }
        }
    }

    protected static void mfrRemoveRecipes(final Block output) {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;

        recipeRegistry.remove(output.getRegistryName());
    }

    //Here if I decide to use it later
    protected static void mfrRemoveRecipes(final Item output) {
        mfrRemoveRecipes(recipe -> {
            final ItemStack recipeOutput = recipe.getRecipeOutput();
            return !recipeOutput.isEmpty() && recipeOutput.getItem() == output;
        });
    }

    protected static int mfrRemoveRecipes(final Predicate<IRecipe> predicate) {
        final IForgeRegistry<IRecipe> registry = ForgeRegistries.RECIPES;
        final List<IRecipe> toRemove = new ArrayList<>();

        for (final IRecipe recipe : registry) {
            if (predicate.test(recipe)) {
                toRemove.add(recipe);
            }
        }

        toRemove.forEach(recipe -> {
            final ResourceLocation registryName = Objects.requireNonNull(recipe.getRegistryName());
            final IRecipe replacement = new DummyRecipe().setRegistryName(registryName);
            registry.register(replacement);
        });

        return 0;
    }

}
