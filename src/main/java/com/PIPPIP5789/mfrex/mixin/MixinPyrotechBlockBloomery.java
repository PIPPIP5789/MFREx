package com.PIPPIP5789.mfrex.mixin;

import com.codetaylor.mc.athenaeum.interaction.spi.IInteraction;
import com.codetaylor.mc.pyrotech.modules.ignition.item.ItemIgniterBase;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.block.BlockBloomery;
import minefantasy.mfr.item.ItemTongs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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

@Mixin(BlockBloomery.class)
public abstract class MixinPyrotechBlockBloomery {

    @Shadow
    public abstract boolean isTop(IBlockState state);

    @Shadow
    public abstract boolean interact(IInteraction.EnumType type, World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ);

    @Inject(method = "onBlockActivated", at = @At("HEAD"), remap = false, cancellable = true)
    public void onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        if (this.isTop(state)) {
            ItemStack heldItem = player.getHeldItemMainhand();
            if (heldItem.getItem() instanceof ItemIgniterBase) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
        System.out.println("Wala");
        if(player.getHeldItemMainhand().getItem() instanceof ItemTongs) {
            ((ItemTongs) player.getHeldItemMainhand().getItem()).onItemRightClick(world, player, hand);
            cir.setReturnValue(false);
            cir.cancel();
        }

        cir.setReturnValue(this.interact(com.codetaylor.mc.athenaeum.interaction.spi.IInteraction.EnumType.MouseClick, world, pos, state, player, hand, facing, hitX, hitY, hitZ));
        cir.cancel();
    }

}
