package com.PIPPIP5789.mfrex.mixin;

import com.codetaylor.mc.athenaeum.util.SoundHelper;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.library.spi.block.IBlockIgnitableWithIgniterItem;
import com.codetaylor.mc.pyrotech.library.util.Util;
import com.codetaylor.mc.pyrotech.modules.ignition.item.ItemIgniterBase;
import com.codetaylor.mc.pyrotech.modules.tech.refractory.ModuleTechRefractory;
import com.codetaylor.mc.pyrotech.modules.tech.refractory.util.RefractoryIgnitionHelper;
import ibxm.Player;
import minefantasy.mfr.api.crafting.IHeatSource;
import minefantasy.mfr.block.BlockFirepit;
import minefantasy.mfr.block.BlockForge;
import minefantasy.mfr.init.MineFantasyBlocks;
import minefantasy.mfr.tile.TileEntityFirepit;
import minefantasy.mfr.tile.TileEntityForge;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemIgniterBase.class)
public abstract class MixinPyrotechItemIgniterBase extends Item {

    public abstract void damageItem(ItemStack stack, EntityLivingBase player);
    public abstract int getCooldownTicks();

    @Inject(method = "onItemUseFinish", at = @At("HEAD"), remap = false, cancellable = true)
    private void onItemUseFinish(ItemStack stack, World world, EntityLivingBase player, CallbackInfoReturnable<ItemStack> cir) {
        RayTraceResult rayTraceResult = this.rayTrace(world, (EntityPlayer)player, false);
        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos pos = rayTraceResult.getBlockPos();
            EnumFacing facing = rayTraceResult.sideHit;
            BlockPos offset = pos.offset(facing);
            IBlockState blockState = world.getBlockState(pos);
            Block block = blockState.getBlock();

            if (world.getTileEntity(pos) instanceof TileEntityFirepit) {
                if (!world.isRemote) {
                    SoundHelper.playSoundServer(world, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS);
                    world.spawnParticle(EnumParticleTypes.FLAME, (double) pos.getX() + 0.5, (double)pos.getY() - 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
                    ((TileEntityFirepit) world.getTileEntity(pos)).setLit(true);
                }

                this.damageItem(stack, player);
            }
            else if (world.getTileEntity(pos) instanceof TileEntityForge) {
                if (!world.isRemote) {
                    SoundHelper.playSoundServer(world, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS);
                    world.spawnParticle(EnumParticleTypes.FLAME, (double) pos.getX() + 0.5, (double)pos.getY() - 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
                    ((TileEntityForge) world.getTileEntity(pos)).fireUpForge();
                }

                this.damageItem(stack, player);
            }
            else if (block instanceof IBlockIgnitableWithIgniterItem) {
                if (!world.isRemote) {
                    ((IBlockIgnitableWithIgniterItem)block).igniteWithIgniterItem(world, pos, blockState, facing);
                    SoundHelper.playSoundServer(world, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS);
                }

                this.damageItem(stack, player);
            }
            else if (Util.canSetFire(world, offset)) {
                if (!world.isRemote) {
                    world.setBlockState(offset, Blocks.FIRE.getDefaultState(), 3);
                    SoundHelper.playSoundServer(world, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS);
                }

                this.damageItem(stack, player);
            }
            else if (!world.isRemote) {
                if (ModPyrotech.INSTANCE.isModuleEnabled(ModuleTechRefractory.class)) {
                    RefractoryIgnitionHelper.igniteBlocks(world, pos);
                }

                SoundHelper.playSoundServer(world, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS);
                this.damageItem(stack, player);
            }

            ((EntityPlayer)player).getCooldownTracker().setCooldown(this, this.getCooldownTicks());
            cir.setReturnValue(stack);
            cir.cancel();
        }
        else {
            player.stopActiveHand();
            cir.setReturnValue(stack);
            cir.cancel();
        }
    }

}
