package com.PIPPIP5789.mfrex.mixin;

import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileTripHammer;
import minefantasy.mfr.constants.Tool;
import minefantasy.mfr.init.MineFantasySounds;
import minefantasy.mfr.recipe.AnvilRecipeBase;
import minefantasy.mfr.tile.TileEntityAnvil;
import minefantasy.mfr.tile.TileEntityBase;
import minefantasy.mfr.util.ToolHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*


        SET UP THE LIGHTERS TO WORK TOMORROW. NO SLACKING OR PROCRASTINATING. ALSO ASK ABOUT AN ALPHA


 */

@Mixin(TileEntityAnvil.class)
public abstract class MixinMFRTileEntityAnvil extends TileEntityBase {

    @Shadow
    private int requiredToolTier;

    @Shadow
    private float qualityBalance;

    @Shadow
    private float rightHit;

    @Shadow
    private float leftHit;

    @Shadow
    public float progress;

    @Shadow
    public float progressMax;

    @Shadow
    public abstract boolean doesPlayerKnowCraft(EntityPlayer player);

    @Shadow
    public abstract boolean canCraft();

    @Shadow
    public abstract void updateCraftingData();

    @Shadow
    public abstract void reassignHitValues();

    @Shadow
    protected abstract void ruinCraft(EntityPlayer player);

    @Shadow
    protected abstract void craftItem(EntityPlayer player, AnvilRecipeBase recipe);

    @Shadow
    protected abstract float getAbsoluteBalance();

    @Inject(method = "tryCraft", at = @At("HEAD"), remap = false, cancellable = true)
    public void tryCraft(EntityPlayer user, boolean rightClick, CallbackInfoReturnable<Boolean> cir) {
        /*AnvilRecipeBase anvilRecipe = null;
        if (this.getRecipe() instanceof AnvilRecipeBase) {
            anvilRecipe = (AnvilRecipeBase)this.getRecipe();
        }

        if (anvilRecipe == null) {
            cir.setReturnValue(false);
            cir.cancel();
        }

        if(user == null) {
            TileTripHammer trip = null;

            if (world.getTileEntity(pos.add(1, 1, 0)) instanceof TileTripHammer)
                trip = (TileTripHammer) world.getTileEntity(pos.add(1, 1, 0));
            if (world.getTileEntity(pos.add(-1, 1, 0)) instanceof TileTripHammer)
                trip = (TileTripHammer) world.getTileEntity(pos.add(-1, 1, 0));
            if (world.getTileEntity(pos.add(0, 1, 1)) instanceof TileTripHammer)
                trip = (TileTripHammer) world.getTileEntity(pos.add(1, 1, 0));
            if (world.getTileEntity(pos.add(0, 1, -1)) instanceof TileTripHammer)
                trip = (TileTripHammer) world.getTileEntity(pos.add(0, 1, -1));

            if (trip != null) {
                Tool tool = ToolHelper.getToolTypeFromStack(trip.getToolStackHandler().getStackInSlot(0));
                int toolTier = ToolHelper.getCrafterTier(trip.getToolStackHandler().getStackInSlot(0));
                if (tool != Tool.HAMMER && tool != Tool.HEAVY_HAMMER) {
                    this.updateCraftingData();
                    cir.setReturnValue(false);
                    cir.cancel();
                }

                if (this.doesPlayerKnowCraft(user) && this.canCraft() && tool == anvilRecipe.getToolType()) {
                    float mod = 1.0F;
                    if (toolTier < this.requiredToolTier) {
                        mod = 2.0F;
                        if (this.world.rand.nextInt(5) == 0) {
                            this.reassignHitValues();
                        }
                    }

                    if (rightClick) {
                        this.qualityBalance += this.rightHit * mod;
                    } else {
                        this.qualityBalance -= this.leftHit * mod;
                    }

                    if (this.qualityBalance >= 1.0F || this.qualityBalance <= -1.0F) {
                        this.ruinCraft(user);
                    }

                    this.world.playSound(user, this.pos.add(0.5, 0.5, 0.5), MineFantasySounds.ANVIL_SUCCEED, SoundCategory.NEUTRAL, 0.25F, rightClick ? 1.2F : 1.0F);
                    float efficiency = ToolHelper.getCrafterEfficiency(trip.getToolStackHandler().getStackInSlot(0)) * (rightClick ? 0.75F : 1.0F);

                    this.progress += Math.max(0.2F, efficiency);
                    if (this.progress >= this.progressMax) {
                        this.craftItem(user, anvilRecipe);
                    }
                } else {
                    this.world.playSound(user, this.pos.add(0.5, 0.5, 0.5), MineFantasySounds.ANVIL_FAIL, SoundCategory.NEUTRAL, 0.25F, 1.0F);
                }

                this.updateCraftingData();
                cir.setReturnValue(true);
                cir.cancel();
            }
        }*/
    }

    @Inject(method = "addXP", at = @At("HEAD"), remap = false, cancellable = true)
    private void addXP(EntityPlayer smith, AnvilRecipeBase anvilRecipe, CallbackInfo ci) {
        if (!this.world.isRemote) {
            float baseXP = this.progressMax / 10.0F;
            baseXP /= 1.0F + this.getAbsoluteBalance();
            if (smith != null) {
                anvilRecipe.giveSkillXp(smith, (float) ((int) baseXP + 1));
                anvilRecipe.giveVanillaXp(smith, baseXP, 1);
            }
        }
        ci.cancel();
    }

}
