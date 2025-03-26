package com.PIPPIP5789.mfrex.mixin;

import com.codetaylor.mc.athenaeum.util.StackHelper;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.PyrotechAPI;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.ModuleTechBloomery;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.tile.TileBloom;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.tile.TileBloomery;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.util.BloomHelper;
import minefantasy.mfr.api.heating.Heatable;
import minefantasy.mfr.api.heating.TongsHelper;
import minefantasy.mfr.item.ItemHeated;
import minefantasy.mfr.item.ItemTongs;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.throwables.ClassMetadataNotFoundException;

import java.util.Set;

@Mixin(ItemTongs.class)
public abstract class MixinMFRItemTongs extends ItemTool {

    protected MixinMFRItemTongs(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
    }

    //@Shadow
    //public abstract RayTraceResult rayTrace(World world, EntityPlayer player, boolean useLiquids);

    @Inject(method = "onItemRightClick", at = @At("HEAD"), remap = false, cancellable = true)
    public void onItemRightClick(World world, EntityPlayer player, EnumHand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        ItemStack item = player.getHeldItem(hand);
        RayTraceResult rayTraceResult = this.rayTrace(world, player, true);
        if (rayTraceResult == null) {
            cir.setReturnValue(ActionResult.newResult(EnumActionResult.PASS, item));
            cir.cancel();
        }
        else {
            if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos pos = rayTraceResult.getBlockPos();
                if (!world.canMineBlockBody(player, pos)) {
                    cir.setReturnValue(ActionResult.newResult(EnumActionResult.PASS, item));
                    cir.cancel();
                }

                if (!player.canPlayerEdit(pos, rayTraceResult.sideHit, item)) {
                    cir.setReturnValue(ActionResult.newResult(EnumActionResult.PASS, item));
                    cir.cancel();
                }

                if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
                    if (world.getTileEntity(rayTraceResult.getBlockPos()) instanceof TileBloomery) {
                        TongsHelper.trySetHeldItem(player.getHeldItemMainhand(), ItemHeated.createHotItem(((TileBloomery) world.getTileEntity(rayTraceResult.getBlockPos())).getOutputStackHandler().extractItem(0, 1, false), 1500));
                    }
                }

                if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
                    if(world.getTileEntity(rayTraceResult.getBlockPos()) instanceof TileBloom) {
                        ItemStack bloomStack = StackHelper.writeTileEntityToItemStack(world.getTileEntity(rayTraceResult.getBlockPos()), new ItemStack(ModuleTechBloomery.Blocks.BLOOM));
                        if (!world.isRemote)
                            world.setBlockToAir(rayTraceResult.getBlockPos());
                        TongsHelper.trySetHeldItem(player.getHeldItemMainhand(), bloomStack);
                    }
                }

                float water = TongsHelper.getWaterSource(world, pos);
                if (!TongsHelper.getHeldItem(item).isEmpty() && water >= 0.0F) {
                    ItemStack drop = TongsHelper.getHeldItem(item).copy();
                    ItemStack cooled = drop;
                    if (TongsHelper.isCoolableItem(drop)) {
                        cooled = Heatable.getQuenchedItem(drop, water);
                        cooled.setCount(drop.getCount());
                        player.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1.0F, 1.0F);
                        player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 2.0F, 0.5F);

                        for(int a = 0; a < 5; ++a) {
                            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)((float)pos.getX() + 0.5F), (double)(pos.getY() + 1), (double)((float)pos.getZ() + 0.5F), 0.0, 0.06499999761581421, 0.0, new int[0]);
                        }
                    }

                    if (!cooled.isEmpty() && !world.isRemote) {
                        if (world.isAirBlock(pos.add(0, 1, 0))) {
                            EntityItem entity = new EntityItem(world, (double)pos.getX() + 0.5, (double)(pos.getY() + 1), (double)pos.getZ() + 0.5, cooled);
                            entity.setPickupDelay(20);
                            entity.motionX = entity.motionY = entity.motionZ = 0.0;
                            world.spawnEntity(entity);
                        } else {
                            player.entityDropItem(cooled, 0.0F);
                        }
                    }
                    System.out.println("8");

                    cir.setReturnValue(ActionResult.newResult(EnumActionResult.PASS, TongsHelper.clearHeldItem(item, player)));
                    cir.cancel();
                }
            }

            cir.setReturnValue(ActionResult.newResult(EnumActionResult.FAIL, item));
            cir.cancel();
        }
    }

}
