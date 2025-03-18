package com.PIPPIP5789.mfrex.mixin;

import betterwithmods.common.blocks.mechanical.BlockBellows;
import betterwithmods.common.blocks.mechanical.tile.TileEntityCauldron;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileSoakingPot;
import minefantasy.mfr.api.crafting.IHeatUser;
import minefantasy.mfr.tile.TileEntityBase;
import minefantasy.mfr.tile.TileEntityFirepit;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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
            cir.setReturnValue((tile instanceof TileEntityCauldron) || (tile instanceof TileSoakingPot) || (tile instanceof IHeatUser ? ((IHeatUser)tile).canAccept(this) : false));
            cir.cancel();
        }
    }

}
