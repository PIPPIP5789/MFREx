package com.PIPPIP5789.mfrex.mixin;

import minefantasy.mfr.item.ArrowType;
import minefantasy.mfr.item.ItemArrowMFR;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBow.class)
public class MixinMinecraftItemBow {

    @Inject(method = "isArrow", at = @At("HEAD"), remap = false, cancellable = true)
    protected void isArrow(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.getItem() instanceof ItemArrow || stack.getItem() instanceof ItemArrowMFR);
    }

}
