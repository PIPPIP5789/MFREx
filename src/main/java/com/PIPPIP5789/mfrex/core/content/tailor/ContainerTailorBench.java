package com.pippip5789.mfrex.core.content.tailor;

import minefantasy.mfr.container.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class ContainerTailorBench extends ContainerBase {

    private final TileEntityTailorBench tile;
    private final boolean isGuiContainer;

    public ContainerTailorBench(TileEntityTailorBench tile) {
        this.isGuiContainer = false;
        this.tile = tile;
        int width = 4;
        int height = 4;

        int y;
        int slot;
        for(y = 0; y < width; ++y) {
            for(slot = 0; slot < height; ++slot) {
                slot = slot * width + y;
                this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), slot, 44 + y * 18, 54 + slot * 18));
            }
        }

        this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), tile.getOutputSlotNum(), 174, 80));

    }

    public ContainerTailorBench(EntityPlayer player, TileEntityTailorBench tile) {
        super(player.inventory, tile);
        this.isGuiContainer = true;
        this.tile = tile;
        int width = 4;
        int height = 4;
        this.addTileSlots(width, height, 44, 54);
        this.addSlotToContainer(new SlotItemHandler(tile.getInventory(), tile.getOutputSlotNum(), 174, 80));

        this.addPlayerSlots(player.inventory, 8, 216);
    }

    public void detectAndSendChanges() {
        for(int i = 0; i < this.inventorySlots.size(); ++i) {
            ItemStack itemstack = ((Slot)this.inventorySlots.get(i)).getStack();
            ItemStack itemstack1 = (ItemStack)this.inventoryItemStacks.get(i);
            if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
                if (this.isGuiContainer) {
                    this.tile.onInventoryChanged();
                }

                itemstack1 = itemstack.isEmpty() ? ItemStack.EMPTY : itemstack.copy();
                this.inventoryItemStacks.set(i, itemstack1);
                Iterator var4 = this.listeners.iterator();

                while(var4.hasNext()) {
                    IContainerListener listener = (IContainerListener)var4.next();
                    listener.sendSlotContents(this, i, itemstack1);
                }
            }
        }

        this.tile.sendUpdates();
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUsableByPlayer(player);
    }

    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer user, int currentSlot) {
        int slotCount = this.tile.getInventory().getSlots();
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(currentSlot);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (currentSlot < slotCount) {
                if (!this.mergeItemStack(itemstack1, slotCount, this.inventorySlots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, slotCount - 5, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() <= 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
