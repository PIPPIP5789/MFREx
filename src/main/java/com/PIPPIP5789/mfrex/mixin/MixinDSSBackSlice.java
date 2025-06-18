package com.pippip5789.mfrex.mixin;

import dynamicswordskills.entity.DSSPlayerInfo;
import dynamicswordskills.skills.*;
import dynamicswordskills.util.PlayerUtils;
import minefantasy.mfr.item.ItemDagger;
import minefantasy.mfr.item.ItemKatana;
import minefantasy.mfr.item.ItemScythe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackSlice.class)
public abstract class MixinDSSBackSlice extends SkillActive {

    public MixinDSSBackSlice(String translationKey) {
        super(translationKey);
    }

    private MixinDSSBackSlice(Dash skill) {
        super(skill);
    }

    @Inject(method = "canUse", at = @At("HEAD"), remap = false, cancellable = true)
    public void canUse(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        ILockOnTarget lock = DSSPlayerInfo.get(player).getTargetingSkill();
        Item weapon = player.getHeldItemMainhand().getItem();
        cir.setReturnValue(super.canUse(player) && !this.isActive() && !player.isHandActive() && (weapon instanceof ItemDagger || weapon instanceof ItemKatana || weapon instanceof ItemScythe) && lock != null && lock.isLockedOn());
        cir.cancel();
    }

}
