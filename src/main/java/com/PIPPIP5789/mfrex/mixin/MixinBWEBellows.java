package com.pippip5789.mfrex.mixin;

import betterwithmods.common.blocks.mechanical.BlockBellows;
import com.oblivioussp.spartanweaponry.item.ItemLongbow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBellows.class)
public abstract class MixinBWEBellows {

    /*@Shadow public abstract EnumFacing getFacing(IBlockState state);

    @Inject(method = "stokeFlames", at = @At("HEAD"), remap = false)
    public void stokeFlames(World world, BlockPos pos, CallbackInfo ci) {
        EnumFacing dirFacing = this.getFacing(world.getBlockState(pos));
        BlockPos dirFacingPos = pos.offset(dirFacing, 1);

        try {
            minefantasy.mfr.tile.TileEntityForge tile = (minefantasy.mfr.tile.TileEntityForge) world.getTileEntity(dirFacingPos);
            if (tile != null) {
                tile.onUsedWithBellows(Math.min(tile.fuelTemperature * tile.getBellowsEffect(), (float) tile.getMaxTemp()));
            }
        }
        catch(ClassCastException e) {}
        try {
            minefantasy.mfr.tile.TileEntityForge wallTile = (minefantasy.mfr.tile.TileEntityForge) world.getTileEntity(dirFacingPos.offset(dirFacing, 1));
            if (wallTile != null) {
                wallTile.onUsedWithBellows(Math.min(wallTile.fuelTemperature * wallTile.getBellowsEffect(), (float) wallTile.getMaxTemp()));
            }
        }
        catch(ClassCastException e) {}
    }*/

}


//

