package com.pippip5789.mfrex.mixin;

import com.codetaylor.mc.pyrotech.modules.ignition.item.ItemIgniterBase;
import com.codetaylor.mc.pyrotech.modules.tech.machine.block.spi.BlockCombustionWorkerStoneBase;
import minefantasy.mfr.item.ItemLighter;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockCombustionWorkerStoneBase.class)
public abstract class MixinPyrotechBlockCombustionWorkerStoneBase {

    @Shadow
    public abstract boolean isTop(IBlockState state);

    @Shadow
    public abstract void igniteWithIgniterItem(World world, BlockPos pos, IBlockState blockState, EnumFacing facing);

    @Inject(method = "onBlockActivated", at = @At("HEAD"), remap = false, cancellable = true)
    private void onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        if (!this.isTop(state)) {
            ItemStack heldItem = player.getHeldItemMainhand();
            if (heldItem.getItem() instanceof ItemLighter) {
                int use = ItemLighter.tryUse(heldItem, player);
                if(use > 0) {
                    igniteWithIgniterItem(world, pos, state, facing);
                    cir.setReturnValue(false);
                }
            }
        }
    }

}
