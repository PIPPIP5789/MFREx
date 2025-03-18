package com.PIPPIP5789.mfrex.mixin;

import betterwithmods.common.registry.block.recipe.BlockIngredient;
import betterwithmods.common.registry.heat.BWMHeatRegistry;
import minefantasy.mfr.init.MineFantasyBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BWMHeatRegistry.HeatSource.class)
public abstract class MixinBWEHeatSource {

    @Shadow private BlockIngredient ingredient;

    @Inject(method = "getHeat", at = @At("HEAD"), remap = false)
    public void getHeat(CallbackInfoReturnable<Integer> cir) {
        System.out.println("Waka -> " + MineFantasyBlocks.FORGE.getBlockState());
    }

}
