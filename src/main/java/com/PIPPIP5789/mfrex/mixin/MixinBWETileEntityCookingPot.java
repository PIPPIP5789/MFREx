package com.PIPPIP5789.mfrex.mixin;

import betterwithmods.common.blocks.mechanical.tile.TileEntityCookingPot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityCookingPot.class)
public abstract class MixinBWETileEntityCookingPot {

    @Shadow
    public int heat;

//    @Shadow
//    public abstract void igniteWithIgniterItem(World world, BlockPos pos, IBlockState blockState, EnumFacing facing);

    @Inject(method = "findCookTime", at = @At("HEAD"), remap = false, cancellable = true)
    private void findCookTime(CallbackInfoReturnable<Integer> cir) {
        //System.out.println("Waka -> " + heat);
    }

}
