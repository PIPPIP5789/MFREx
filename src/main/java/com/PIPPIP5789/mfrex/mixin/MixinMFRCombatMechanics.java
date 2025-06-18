package com.pippip5789.mfrex.mixin;

import minefantasy.mfr.config.ConfigStamina;
import minefantasy.mfr.item.ItemWeaponMFR;
import minefantasy.mfr.mechanics.CombatMechanics;
import minefantasy.mfr.mechanics.StaminaBar;
import minefantasy.mfr.mechanics.knowledge.ResearchLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import static minefantasy.mfr.util.TacticalManager.shouldStaminaBlock;

@Mixin(CombatMechanics.class)
public class MixinMFRCombatMechanics {

}
