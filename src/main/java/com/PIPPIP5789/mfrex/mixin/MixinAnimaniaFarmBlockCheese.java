package com.pippip5789.mfrex.mixin;

import com.animania.addons.farm.common.block.BlockCheese;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rustic.client.EventHandlerClient;

@Mixin(BlockCheese.class)
public class MixinAnimaniaFarmBlockCheese {

    @Inject(method = "eatCheese", at = @At("HEAD"), remap = false, cancellable = true)
    public void eatCheese(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(false);
        ci.cancel();
    }

}
