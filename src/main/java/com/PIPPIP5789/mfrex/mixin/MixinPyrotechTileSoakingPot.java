package com.PIPPIP5789.mfrex.mixin;

import com.codetaylor.mc.athenaeum.network.tile.ITileDataService;
import com.codetaylor.mc.athenaeum.network.tile.data.TileDataFloat;
import com.codetaylor.mc.athenaeum.network.tile.spi.TileEntityDataBase;
import com.codetaylor.mc.athenaeum.util.ParticleHelper;
import com.codetaylor.mc.athenaeum.util.SoundHelper;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.library.spi.block.IBlockIgnitableWithIgniterItem;
import com.codetaylor.mc.pyrotech.library.util.Util;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCoreConfig;
import com.codetaylor.mc.pyrotech.modules.ignition.item.ItemIgniterBase;
import com.codetaylor.mc.pyrotech.modules.tech.basic.block.BlockSoakingPot;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.SoakingPotRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileCampfire;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileSoakingPot;
import com.codetaylor.mc.pyrotech.modules.tech.refractory.ModuleTechRefractory;
import com.codetaylor.mc.pyrotech.modules.tech.refractory.util.RefractoryIgnitionHelper;
import minefantasy.mfr.block.BlockFirepit;
import minefantasy.mfr.block.BlockForge;
import minefantasy.mfr.tile.TileEntityFirepit;
import minefantasy.mfr.tile.TileEntityForge;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileSoakingPot.class)
public abstract class MixinPyrotechTileSoakingPot extends TileEntityDataBase {

    @Shadow
    private boolean firstLightCheck;

    @Shadow
    private SoakingPotRecipe currentRecipe;

    @Shadow
    private TileSoakingPot.InputStackHandler inputStackHandler;

    @Shadow
    private TileSoakingPot.InputFluidTank inputFluidTank;

    @Shadow
    private TileDataFloat recipeProgress;

    @Shadow
    private TileSoakingPot.OutputStackHandler outputStackHandler;

    @Shadow
    protected abstract void updateRecipe();

    protected MixinPyrotechTileSoakingPot(ITileDataService tileDataService) {
        super(tileDataService);
    }

    @Inject(method = "update", at = @At("HEAD"), remap = false, cancellable = true)
    public void update(CallbackInfo ci) {
        if (!this.firstLightCheck) {
            this.firstLightCheck = true;
            this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos);
        }

        if (this.currentRecipe != null && this.currentRecipe.isCampfireRequired()) {
            TileEntity tileEntity = this.world.getTileEntity(this.pos.down());
            if (!(tileEntity instanceof TileCampfire || tileEntity instanceof TileEntityFirepit)) {
                return;
            }

            if (tileEntity instanceof TileEntityFirepit && ((TileEntityFirepit) tileEntity).getHeat() < 100) {
                return;
            }

            if (tileEntity instanceof TileCampfire && !((TileCampfire)tileEntity).workerIsActive()) {
                return;
            }
        }

        if (this.world.isRemote) {
            if (ModuleCoreConfig.CLIENT.SHOW_RECIPE_PROGRESSION_PARTICLES && this.currentRecipe != null && !this.inputStackHandler.getStackInSlot(0).isEmpty() && this.world.getTotalWorldTime() % 40L == 0L) {
                double y = (double)this.pos.getY() + 0.75;
                IBlockState blockState = this.world.getBlockState(this.pos);
                if ((Boolean)blockState.getBlock().getActualState(blockState, this.world, this.pos).getValue(BlockSoakingPot.PROPERTY_CAMPFIRE)) {
                    y -= 0.25;
                }

                ParticleHelper.spawnProgressParticlesClient(1, (double)this.pos.getX() + 0.5, y, (double)this.pos.getZ() + 0.5, 0.25, 0.25, 0.25);
            }

        } else {
            if (this.currentRecipe != null) {
                int timeTicks = Math.max(1, this.currentRecipe.getTimeTicks());
                float increment = 1.0F / (float)timeTicks;
                ItemStack itemStack = this.inputStackHandler.getStackInSlot(0);
                int maxDrain = this.currentRecipe.getInputFluid().amount * itemStack.getCount();
                FluidStack drain = this.inputFluidTank.drain(maxDrain, false);
                if (drain == null || drain.amount != maxDrain) {
                    return;
                }

                this.recipeProgress.add(increment);
                if ((double)this.recipeProgress.get() >= 0.9999) {
                    SoakingPotRecipe currentRecipe = this.currentRecipe;
                    ItemStack inputItem = this.inputStackHandler.extractItem(0, this.inputStackHandler.getSlotLimit(0), false);
                    this.inputFluidTank.drain(currentRecipe.getInputFluid().amount * inputItem.getCount(), true);
                    ItemStack output = currentRecipe.getOutput();
                    output.setCount(inputItem.getCount() * output.getCount());
                    this.outputStackHandler.insertItem(0, output, false);
                    this.recipeProgress.set(0.0F);
                    this.updateRecipe();
                }
            } else {
                this.recipeProgress.set(0.0F);
            }

        }
    }


}
