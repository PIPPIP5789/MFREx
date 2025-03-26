package com.pippip5789.mfrex.mixin;

import com.pippip5789.mfrex.core.content.masonry.GuiMasonryBench;
import com.pippip5789.mfrex.core.content.masonry.TileEntityMasonryBench;
import minefantasy.mfr.network.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetworkHandler.class)
public class MixinMFRNetworkHandler {

    @Unique
    private static final int GUI_MASONRY_BENCH = 16;

    @Inject(method = "getClientGuiElement", at = @At("HEAD"), remap = false, cancellable = true)
    public void getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z, CallbackInfoReturnable<Object> cir) {
        final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null && ID == GUI_MASONRY_BENCH) {
            System.out.println("WAKADY");
            cir.setReturnValue(new GuiMasonryBench(((TileEntityMasonryBench)tileEntity).createContainer(player), (TileEntityMasonryBench)tileEntity));
            
        }
    }

    @Inject(method = "getServerGuiElement", at = @At("HEAD"), remap = false, cancellable = true)
    public void getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z, CallbackInfoReturnable<Object> cir) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity != null) {
            if (ID == GUI_MASONRY_BENCH) {
                cir.setReturnValue(((TileEntityMasonryBench)tileEntity).createContainer(player));
                
            }
        }
    }

}
