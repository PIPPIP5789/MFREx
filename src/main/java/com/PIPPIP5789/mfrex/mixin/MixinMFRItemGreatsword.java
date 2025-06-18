package com.pippip5789.mfrex.mixin;

import minefantasy.mfr.item.ItemWeaponMFR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemWeaponMFR.class)
public class MixinMFRItemGreatsword {

    @Inject(method = "canWeaponParry", at = @At("HEAD"), remap = false, cancellable = true)
    public void canWeaponParry(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }

}
