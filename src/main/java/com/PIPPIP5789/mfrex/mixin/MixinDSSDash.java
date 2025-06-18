package com.pippip5789.mfrex.mixin;

import dynamicswordskills.entity.DSSPlayerInfo;
import dynamicswordskills.network.PacketDispatcher;
import dynamicswordskills.network.server.DashImpactPacket;
import dynamicswordskills.ref.Config;
import dynamicswordskills.skills.Dash;
import dynamicswordskills.skills.SkillActive;
import dynamicswordskills.util.PlayerUtils;
import dynamicswordskills.util.TargetUtils;
import minefantasy.mfr.item.ItemDagger;
import minefantasy.mfr.item.ItemSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static betterwithmods.client.ClientEventHandler.mc;

@Mixin(Dash.class)
public abstract class MixinDSSDash extends SkillActive {

    public MixinDSSDash(String translationKey) {
        super(translationKey);
    }

    private MixinDSSDash(Dash skill) {
        super(skill);
    }

    @Shadow
    private boolean isActive;

    @Shadow
    private int activeTime;

    @Shadow
    private Vec3d trajectory;

    @Shadow
    private Vec3d initialPosition;

    @Shadow
    private int impactTime;

    @Shadow
    private Entity target;

    @Shadow
    protected abstract int getMaxActiveTime();

    @Shadow
    protected abstract int getBlockCooldown();

    @Shadow
    protected abstract double getRange();

    @Shadow
    protected abstract void setNotDashing(EntityPlayer player);

    @Shadow protected abstract void onDeactivated(World world, EntityPlayer player);

    @Inject(method = "onUpdate", at = @At("HEAD"), remap = false, cancellable = true)
    public void onUpdate(EntityPlayer player, CallbackInfo ci) {
        if (this.impactTime > 0) {
            --this.impactTime;
            if (this.impactTime == 0) {
                this.target = null;
            }
        }
        if(!player.isHandActive()) {
            if (!player.getEntityWorld().isRemote) {
                //this.deactivate(player);
            }
        }
        if (this.isActive) {
            player.setSprinting(true);
            if (player.getEntityWorld().isRemote) {
                if (this.trajectory != null) {
                    double bonus = 1.0 + 0.1 * (double)this.level;
                    double speed = bonus * player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                    if (player.isInWater() || player.isInLava()) {
                        speed *= 0.15;
                    }

                    if (player.onGround) {
                        this.trajectory = player.getLookVec();
                    }

                    player.addVelocity(this.trajectory.x * speed, -0.02, this.trajectory.z * speed);
                }

                RayTraceResult result = TargetUtils.checkForImpact(player.getEntityWorld(), player, player, 0.5, false);
                if (result == null && !player.collidedHorizontally) {
                    if (this.initialPosition != null && !(player.getDistance(this.initialPosition.x, this.initialPosition.y, this.initialPosition.z) > this.getRange())) {
                        if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
                            this.deactivate(player);
                        }
                    } else {
                        player.addVelocity(-player.motionX * 0.5, -0.02, -player.motionZ * 0.5);
                        this.deactivate(player);
                    }
                } else {
                    PacketDispatcher.sendToServer(new DashImpactPacket(player, result));
                    player.resetCooldown();
                    DSSPlayerInfo.get(player).setUseItemCooldown(this.getBlockCooldown());
                    KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode(), false);
                    this.impactTime = 5;
                    if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY) {
                        this.target = result.entityHit;
                    }

                    double d = player.onGround ? 2.0 : 0.5;
                    double dy = player.onGround ? 0.3 : -0.15;
                    player.setVelocity(-player.motionX * d, dy, -player.motionZ * d);
                    this.setNotDashing(player);
                }
            }
        }

        if (this.isActive) {
            ++this.activeTime;
            if (this.activeTime > this.getMaxActiveTime() && !player.getEntityWorld().isRemote) {
                this.deactivate(player);
            }
        }

        ci.cancel();
    }

    @Inject(method = "onActivated", at = @At("HEAD"), remap = false, cancellable = true)
    protected void onActivated(World world, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if(canUse(player)) {
            this.isActive = true;
            this.activeTime = 0;
            player.setSprinting(true);
            this.trajectory = player.getLookVec();
            this.initialPosition = new Vec3d(player.posX, player.posY, player.posZ);
        }
        cir.setReturnValue(this.isActive());
        cir.cancel();
    }

    public boolean canUse(EntityPlayer player) {
        boolean flag = PlayerUtils.isBlocking(player);
        EnumHand[] var3 = EnumHand.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            EnumHand hand = var3[var5];
            if (canItemDash(player, hand)) {
                flag = true;
                break;
            }
        }

        return flag && super.canUse(player) && !this.isActive();
    }

    @Inject(method = "canItemDash", at = @At("HEAD"), remap = false, cancellable = true)
    private void canItemDash(EntityPlayer player, EnumHand hand, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(canItemDash(player, hand));
        cir.cancel();
    }

    @Unique
    private boolean canItemDash(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        return stack.getItem() instanceof ItemShield || stack.getItem() instanceof ItemDagger || stack.getItem() instanceof ItemSword;
    }

    @Inject(method = "isKeyListener", at = @At("HEAD"), remap = false, cancellable = true)
    @SideOnly(Side.CLIENT)
    public void isKeyListener(Minecraft mc, KeyBinding key, boolean isLockedOn, CallbackInfoReturnable<Boolean> cir) {
        if (Config.requiresLockOn() && !isLockedOn) {
            cir.setReturnValue(false);
            cir.cancel();
        } else {
            cir.setReturnValue(key == mc.gameSettings.keyBindUseItem);
            cir.cancel();
        }
    }

}
