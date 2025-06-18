package com.pippip5789.mfrex.mixin;

import dynamicswordskills.skills.Dash;
import dynamicswordskills.skills.SkillActive;
import dynamicswordskills.skills.SpinAttack;
import dynamicswordskills.util.PlayerUtils;
import minefantasy.mfr.item.ItemBattleaxe;
import minefantasy.mfr.item.ItemGreatsword;
import minefantasy.mfr.item.ItemWaraxe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpinAttack.class)
public abstract class MixinDSSSpinAttack extends SkillActive {

    @Shadow
    protected abstract boolean checkActiveHand(EntityPlayer player);

    public MixinDSSSpinAttack(String translationKey) {
        super(translationKey);
    }

    private MixinDSSSpinAttack(Dash skill) {
        super(skill);
    }

    @Inject(method = "canSpin", at = @At("HEAD"), remap = false, cancellable = true)
    protected void canSpin(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        Item item = player.getHeldItemMainhand().getItem();
        cir.setReturnValue(super.canUse(player) && this.checkActiveHand(player) && (item instanceof ItemWaraxe || item instanceof ItemBattleaxe || item instanceof ItemGreatsword));
        cir.cancel();
    }

}
