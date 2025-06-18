package com.pippip5789.mfrex.mixin;

import dynamicswordskills.entity.DSSPlayerInfo;
import dynamicswordskills.skills.*;
import dynamicswordskills.util.PlayerUtils;
import minefantasy.mfr.item.ItemDagger;
import minefantasy.mfr.item.ItemKatana;
import minefantasy.mfr.item.ItemSword;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MortalDraw.class)
public abstract class MixinDSSMortalDraw extends SkillActive {

    public MixinDSSMortalDraw(String translationKey) {
        super(translationKey);
    }

    private MixinDSSMortalDraw(Dash skill) {
        super(skill);
    }

    @Inject(method = "getSwordSlot", at = @At("HEAD"), remap = false, cancellable = true)
    private static void getSwordSlot(EntityPlayer player, CallbackInfoReturnable<Integer> cir) {
        int plvl = DSSPlayerInfo.get(player).getTrueSkillLevel(Skills.mortalDraw);
        boolean needsDummy = DSSPlayerInfo.get(player).getTrueSkillLevel(Skills.swordBasic) < 1;

        for(int i = 0; i < 9; ++i) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && (plvl > 0) && (stack.getItem() instanceof ItemKatana || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemDagger) || PlayerUtils.isProvider(stack, Skills.mortalDraw) && (!needsDummy || PlayerUtils.isProvider(stack, Skills.swordBasic))) {
                cir.setReturnValue(i);
                cir.cancel();
            }
        }

        cir.setReturnValue(-1);
        cir.cancel();
    }

}
