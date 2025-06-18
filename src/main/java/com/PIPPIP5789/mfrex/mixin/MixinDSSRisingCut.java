package com.pippip5789.mfrex.mixin;

import dynamicswordskills.entity.DSSPlayerInfo;
import dynamicswordskills.skills.Dash;
import dynamicswordskills.skills.Parry;
import dynamicswordskills.skills.RisingCut;
import dynamicswordskills.skills.SkillActive;
import dynamicswordskills.util.PlayerUtils;
import minefantasy.mfr.item.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RisingCut.class)
public abstract class MixinDSSRisingCut extends SkillActive {

    public MixinDSSRisingCut(String translationKey) {
        super(translationKey);
    }

    private MixinDSSRisingCut(Dash skill) {
        super(skill);
    }

    @Inject(method = "canUse", at = @At("HEAD"), remap = false, cancellable = true)
    public void canUse(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        Item item = player.getHeldItemMainhand().getItem();
        cir.setReturnValue(super.canUse(player) && !this.isActive() && !player.isHandActive() && (item instanceof ItemSword || item instanceof ItemGreatsword || item instanceof ItemBattleaxe));
        cir.cancel();
    }

}
