package com.pippip5789.mfrex.mixin;

import com.codetaylor.mc.athenaeum.interaction.spi.IBlockInteractable;
import com.codetaylor.mc.athenaeum.interaction.spi.IInteraction;
import com.codetaylor.mc.pyrotech.modules.ignition.item.ItemIgniterBase;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.block.BlockBloom;
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

@Mixin(BlockBloom.class)
public abstract class MixinPyrotechBlockBloom implements IBlockInteractable {

    @Inject(method = "onBlockActivated", at = @At("HEAD"), remap = false, cancellable = true)
    public void onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        if(player.getHeldItemMainhand().getItem() instanceof ItemTongs) {
            ((ItemTongs) player.getHeldItemMainhand().getItem()).onItemRightClick(world, player, hand);
            cir.setReturnValue(false);
            
        }

        cir.setReturnValue(this.interact(IInteraction.EnumType.MouseClick, world, pos, state, player, hand, facing, hitX, hitY, hitZ));
    }

}
