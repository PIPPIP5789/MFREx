package com.pippip5789.mfrex.mixin;

import com.codetaylor.mc.pyrotech.modules.ignition.item.ItemIgniterBase;
import minefantasy.mfr.api.tool.ILighter;
import minefantasy.mfr.block.BlockFirepit;
import minefantasy.mfr.block.BlockTileEntity;
import minefantasy.mfr.tile.TileEntityFirepit;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockFirepit.class)
public abstract class MixinMFRBlockFirepit extends BlockTileEntity<TileEntityFirepit> {

    @Shadow
    private Random rand;

    public MixinMFRBlockFirepit(Material material, MapColor mapColor) {
        super(material, mapColor);
    }
/*
    @Inject(method = "onBlockActivated", at = @At("HEAD"), remap = false, cancellable = true)
    public void onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        TileEntityFirepit firepit = (TileEntityFirepit)world.getTileEntity(pos);
        if (firepit != null) {
            ItemStack held = player.getHeldItemMainhand();
            boolean burning = firepit.isBurning();
            if (!held.isEmpty()) {
                if (firepit.addFuel(held) && !player.capabilities.isCreativeMode) {
                    if (!world.isRemote) {
                        if (held.getCount() == 1) {
                            if (!held.getItem().getContainerItem(held).isEmpty()) {
                                player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, held.getItem().getContainerItem(held));
                            } else {
                                player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                            }
                        } else {
                            held.shrink(1);
                            if (!held.getItem().getContainerItem(held).isEmpty() && !player.inventory.addItemStackToInventory(held.getItem().getContainerItem(held))) {
                                player.entityDropItem(held.getItem().getContainerItem(held), 0.0F);
                            }
                        }
                    }

                    cir.setReturnValue(true);
                    
                }

                if (burning) {
                    if (firepit.tryCook(player, held) && !player.capabilities.isCreativeMode) {
                        ItemStack contain = held.getItem().getContainerItem(held);
                        held.shrink(1);
                        if (held.getCount() <= 0) {
                            if (!contain.isEmpty()) {
                                player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, contain);
                            } else {
                                player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                            }
                        } else if (!contain.isEmpty()) {
                            firepit.dropItem(player, contain);
                        }
                    }

                    cir.setReturnValue(true);
                    
                }

                if (firepit.fuel > 0) {
                    if (held.getItem() instanceof ILighter) {
                        world.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
                        world.spawnParticle(EnumParticleTypes.FLAME, (double)pos.getX() + 0.5, (double)pos.getY() - 0.5, (double)pos.getZ() + 0.5, 0.0, 0.10000000149011612, 0.0, new int[0]);
                        ILighter lighter = (ILighter)held.getItem();
                        if (lighter.canLight()) {
                            if (this.rand.nextDouble() < lighter.getChance() && !world.isRemote) {
                                firepit.setLit(true);
                                held.damageItem(1, player);
                            }

                            cir.setReturnValue(true);
                            
                        }
                    }

                    if (held.getItem() instanceof ItemIgniterBase) {
                        world.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
                        world.spawnParticle(EnumParticleTypes.FLAME, (double)pos.getX() + 0.5, (double)pos.getY() - 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
                        if (!world.isRemote) {
                            ItemIgniterBase lighter = (ItemIgniterBase) held.getItem();
                            if(lighter.)
                            firepit.setLit(true);
                            held.damageItem(1, player);
                        }

                        cir.setReturnValue(true);
                        
                    }

                    if (held.getItem() instanceof ItemFlintAndSteel) {
                        world.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
                        world.spawnParticle(EnumParticleTypes.FLAME, (double)pos.getX() + 0.5, (double)pos.getY() - 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0, new int[0]);
                        if (!world.isRemote) {
                            firepit.setLit(true);
                            held.damageItem(1, player);
                        }

                        cir.setReturnValue(true);
                        
                    }
                }
            }
        }

        cir.setReturnValue(super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ));
        
    }*/

}
