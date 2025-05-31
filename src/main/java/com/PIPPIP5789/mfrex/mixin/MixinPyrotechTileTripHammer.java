package com.pippip5789.mfrex.mixin;

import com.codetaylor.mc.athenaeum.network.tile.ITileDataService;
import com.codetaylor.mc.athenaeum.util.FacingHelper;
import com.codetaylor.mc.athenaeum.util.RandomHelper;
import com.codetaylor.mc.athenaeum.util.StackHelper;
import com.codetaylor.mc.pyrotech.library.util.Util;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.AnvilRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.spi.TileAnvilBase;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileTripHammer;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.spi.TileCogWorkerBase;
import minefantasy.mfr.tile.TileEntityAnvil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Mixin(TileTripHammer.class)
public abstract class MixinPyrotechTileTripHammer extends TileCogWorkerBase {

    @Shadow
    public abstract ItemStackHandler getToolStackHandler();

    @Shadow public abstract void onTileDataUpdate();

    public MixinPyrotechTileTripHammer(ITileDataService tileDataService) {
        super(tileDataService);
    }

    @Inject(method = "doWork", at = @At("HEAD"), remap = false, cancellable = true)
    public void doWork(ItemStack cog, CallbackInfoReturnable<Integer> cir) {
        IBlockState blockState = this.world.getBlockState(this.pos);
        EnumFacing tileFacing = this.getTileFacing(this.world, this.pos, blockState);
        BlockPos anvilPos = this.pos.offset(tileFacing);
        anvilPos = anvilPos.add(0, -1, 0);
        TileEntity tileEntity = this.world.getTileEntity(anvilPos);
        ItemStack toolItemStack = getToolStackHandler().getStackInSlot(0);

        if (!toolItemStack.isEmpty()) {
            if (tileEntity instanceof TileEntityAnvil) {
                if (((TileEntityAnvil) tileEntity).getRecipe() != null && toolItemStack.attemptDamageItem(1, RandomHelper.random(), (EntityPlayerMP) null)) {
                    this.world.playSound((EntityPlayer) null, (double) this.pos.getX(), (double) this.pos.getY(), (double) this.pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.75F, (float) (1.0 + Util.RANDOM.nextGaussian() * 0.4000000059604645));
                    toolItemStack.shrink(1);
                    getToolStackHandler().setStackInSlot(0, toolItemStack);
                }

                ((TileEntityAnvil) tileEntity).tryCraft(null, false);

                cir.setReturnValue(1);
            }
        }
    }

}
