package com.pippip5789.mfrex.mixin;

import betterwithmods.BWMod;
import betterwithmods.common.blocks.mechanical.BlockBellows;
import betterwithmods.common.blocks.mechanical.tile.TileEntityCauldron;
import betterwithmods.common.blocks.mechanical.tile.TileEntityCrucible;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileSoakingPot;
import minefantasy.mfr.api.crafting.IHeatUser;
import minefantasy.mfr.tile.TileEntityBase;
import minefantasy.mfr.tile.TileEntityFirepit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFirepit.class)
public class MixinMFRTileEntityFirepit extends TileEntity {

    @Inject(method = "hasBlockAbove", at = @At("HEAD"), remap = false, cancellable = true)
    private void hasBlockAbove(CallbackInfoReturnable<Boolean> cir) {
        if (this.world == null) {
        }
        else {
            TileEntity tile = this.world.getTileEntity(this.pos.add(0, 1, 0));
            boolean returnVal = false;

            if (Loader.isModLoaded(BWMod.MODID))
                if ((tile instanceof TileEntityCauldron) || (tile instanceof TileEntityCrucible)) returnVal = true;
            if (Loader.isModLoaded("pyrotech"))
                if ((tile instanceof TileSoakingPot)) returnVal = true;
            if ((tile instanceof IHeatUser && ((IHeatUser) tile).canAccept(this))) returnVal = true;

            cir.setReturnValue(returnVal);
        }
    }

}
