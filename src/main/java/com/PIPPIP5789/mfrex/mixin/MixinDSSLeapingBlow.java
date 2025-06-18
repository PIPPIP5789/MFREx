package com.pippip5789.mfrex.mixin;

import dynamicswordskills.entity.DSSPlayerInfo;
import dynamicswordskills.skills.*;
import dynamicswordskills.util.PlayerUtils;
import dynamicswordskills.util.TargetUtils;
import minefantasy.mfr.item.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeapingBlow.class)
public abstract class MixinDSSLeapingBlow extends SkillActive {

    public MixinDSSLeapingBlow(String translationKey) {
        super(translationKey);
    }

    private MixinDSSLeapingBlow(Dash skill) {
        super(skill);
    }

    @Inject(method = "canUse", at = @At("HEAD"), remap = false, cancellable = true)
    public void canUse(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        Item item = player.getHeldItemMainhand().getItem();
        cir.setReturnValue(super.canUse(player) && !this.isActive() && (item instanceof ItemWaraxe || item instanceof ItemWarhammer || item instanceof ItemMace) && !TargetUtils.isInLiquid(player));
        cir.cancel();
    }

}
