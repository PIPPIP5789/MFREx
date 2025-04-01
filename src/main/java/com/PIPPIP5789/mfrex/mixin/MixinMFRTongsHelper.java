package com.pippip5789.mfrex.mixin;

import com.codetaylor.mc.pyrotech.modules.tech.bloomery.ModuleTechBloomery;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.block.BlockBloom;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.tile.TileBloom;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.tile.TileBloomery;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.util.BloomHelper;
import minefantasy.mfr.api.heating.IHotItem;
import minefantasy.mfr.api.heating.TongsHelper;
import minefantasy.mfr.item.ItemHeated;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TongsHelper.class)
public abstract class MixinMFRTongsHelper {

    /*if (tile instanceof TileBloomery) {
        bloomStack = ((TileBloomery)tile).getOutputStackHandler().extractItem(0, 1, false);
        tongsFull = BloomHelper.createItemTongsFull(heldItem, bloomStack);
        heldItem.shrink(1);
        ItemHandlerHelper.giveItemToPlayer(player, tongsFull, player.inventory.currentItem);
        return true;
    } else {
        if (tile instanceof TileBloom) {
            bloomStack = BloomHelper.toItemStack((TileBloom)tile, new ItemStack(ModuleTechBloomery.Blocks.BLOOM));
            tongsFull = BloomHelper.createItemTongsFull(heldItem, bloomStack);
            if (!world.isRemote) {
                world.setBlockToAir(tile.getPos());
            }

            heldItem.shrink(1);
            ItemHandlerHelper.giveItemToPlayer(player, tongsFull, player.inventory.currentItem);
        }*/


    @Inject(method = "trySetHeldItem", at = @At("HEAD"), remap = false, cancellable = true)
    private static void trySetHeldItem(ItemStack tongs, ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        System.out.println("Waka ->  " + tongs + " : " + item);
        if (!item.isEmpty() && TongsHelper.isHotItem(item)) {
            NBTTagCompound nbt = TongsHelper.getNBT(tongs);
            nbt.setBoolean("Held", true);
            NBTTagCompound save = new NBTTagCompound();
            item.writeToNBT(save);
            nbt.setTag("Saved", save);

            cir.setReturnValue(true);
        }
        else {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "isHotItem", at = @At("HEAD"), remap = false, cancellable = true)
    private static void isHotItem(ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        if(item != null) {
            if (Loader.isModLoaded("pyrotech"))
                cir.setReturnValue((item.getItem() instanceof IHotItem ? ((IHotItem) item.getItem()).isHot(item) : false) || (BlockBloom.getBlockFromItem(item.getItem()) instanceof BlockBloom));
            else
                cir.setReturnValue(item.getItem() instanceof IHotItem ? ((IHotItem) item.getItem()).isHot(item) : false);
        }
        
    }

}
