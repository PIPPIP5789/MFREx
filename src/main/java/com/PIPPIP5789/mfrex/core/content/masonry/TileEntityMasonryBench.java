package com.pippip5789.mfrex.core.content.masonry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import minefantasy.mfr.constants.Skill;
import minefantasy.mfr.constants.Tool;
import minefantasy.mfr.container.ContainerBase;
import minefantasy.mfr.mechanics.knowledge.ResearchLogic;
import minefantasy.mfr.recipe.IRecipeMFR;
import minefantasy.mfr.tile.TileEntityBase;
import minefantasy.mfr.util.ToolHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMasonryBench extends TileEntityBase implements IMasonryBench {

    private int tier;
    public static final int WIDTH = 4;
    public static final int HEIGHT = 4;
    public float progressMax;
    public float progress;
    private ContainerMasonryBench syncMasonryBench;
    private MasonryBenchCraftMatrix craftMatrix;
    private String lastPlayerHit;
    public final ItemStackHandler inventory;

    public TileEntityMasonryBench() {
        this.lastPlayerHit = "";
        this.inventory = this.createInventory();
        this.setContainer(new ContainerMasonryBench(this));
    }

    protected ItemStackHandler createInventory() {
        return new ItemStackHandler(21);
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ContainerBase createContainer(EntityPlayer player) {
        return new ContainerMasonryBench(player, this);
    }

    protected int getGuiId() {
        return 16;
    }

    public boolean isItemValidForSlot(int slot, ItemStack item) {
        return true;
    }

    public void onInventoryChanged() {
        this.updateCraftingData();
        IBlockState iblockstate = this.world.getBlockState(this.getPos());
        this.world.notifyBlockUpdate(this.getPos(), iblockstate, iblockstate, 3);
        this.markDirty();
    }

    public boolean tryCraft(EntityPlayer user) {
        if (user == null) {
            return false;
        } else if (this.getRecipe() != null && this.getRecipe() instanceof MasonryBenchRecipeBase) {
            MasonryBenchRecipeBase MasonryBenchRecipe = (MasonryBenchRecipeBase)this.getRecipe();
            ItemStack held = user.getHeldItemMainhand();
            Tool tool = ToolHelper.getToolTypeFromStack(held);
            int toolTier = ToolHelper.getCrafterTier(held);
            float efficiency = ToolHelper.getCrafterEfficiency(held);
            if (tool == Tool.OTHER) {
                this.updateCraftingData();
                return false;
            } else if (this.world.isRemote) {
                return true;
            } else {
                if (!held.isEmpty()) {
                    held.damageItem(1, user);
                }

                if (this.doesPlayerKnowCraft(user) && this.canCraft(MasonryBenchRecipe) && tool == MasonryBenchRecipe.getToolType() && this.tier >= MasonryBenchRecipe.getMasonryBenchTier() && toolTier >= MasonryBenchRecipe.getToolTier()) {
                    this.world.playSound((EntityPlayer)null, this.pos, this.getUseSound(MasonryBenchRecipe), SoundCategory.AMBIENT, 1.0F, 1.0F);
                    if (user.swingProgress > 0.0F && (double)user.swingProgress <= 1.0) {
                        efficiency *= 0.5F - user.swingProgress;
                    }

                    this.progress += Math.max(0.2F, efficiency);
                    if (this.progress >= this.progressMax) {
                        this.craftItem(user, MasonryBenchRecipe);
                    }
                }

                this.lastPlayerHit = user.getName();
                this.updateCraftingData();
                return true;
            }
        } else {
            return false;
        }
    }

    private SoundEvent getUseSound(MasonryBenchRecipeBase MasonryBenchRecipe) {
        if (MasonryBenchRecipe.getSound().toString().equalsIgnoreCase("engineering")) {
            if (this.world.rand.nextInt(5) == 0) {
                return SoundEvents.UI_BUTTON_CLICK;
            } else {
                return this.world.rand.nextInt(20) == 0 ? SoundEvents.BLOCK_WOODEN_DOOR_OPEN : SoundEvents.BLOCK_WOOD_STEP;
            }
        } else {
            return MasonryBenchRecipe.getSound();
        }
    }

    private void craftItem(EntityPlayer user, MasonryBenchRecipeBase MasonryBenchRecipe) {
        if (this.canCraft(MasonryBenchRecipe)) {
            this.addXP(user, MasonryBenchRecipe);
            ItemStack result = MasonryBenchRecipe.getCraftingResult().copy();
            int output = this.getOutputSlotNum();
            if (this.getInventory().getStackInSlot(output).isEmpty()) {
                if (result.getMaxStackSize() == 1 && !this.lastPlayerHit.isEmpty()) {
                    this.getNBT(result).setString("mfr_crafted_by_name", this.lastPlayerHit);
                }

                this.getInventory().setStackInSlot(output, result);
            } else if (this.getInventory().getStackInSlot(output).getItem() == result.getItem()) {
                this.getInventory().getStackInSlot(output).grow(result.getCount());
            }

            this.consumeResources(user);
        }

        this.onInventoryChanged();
        this.progress = 0.0F;
    }

    public int getOutputSlotNum() {
        return this.getInventory().getSlots() - 5;
    }

    private NBTTagCompound getNBT(ItemStack item) {
        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
        }

        return item.getTagCompound();
    }

    public void consumeResources(EntityPlayer player) {
        for(int slot = 0; slot < this.getOutputSlotNum(); ++slot) {
            ItemStack item = this.getInventory().getStackInSlot(slot);
            if (!item.isEmpty() && !item.getItem().getContainerItem(item).isEmpty()) {
                if (item.getCount() == 1) {
                    this.getInventory().setStackInSlot(slot, item.getItem().getContainerItem(item));
                } else {
                    ItemStack drop = this.processSurplus(item.getItem().getContainerItem(item));
                    if (!drop.isEmpty() && player != null) {
                        EntityItem entityItem = player.dropItem(drop, false);
                        if (entityItem != null) {
                            entityItem.setPosition(player.posX, player.posY, player.posZ);
                            entityItem.setNoPickupDelay();
                        }
                    }

                    this.getInventory().extractItem(slot, 1, false);
                }
            } else {
                this.getInventory().extractItem(slot, 1, false);
            }
        }

        this.onInventoryChanged();
    }

    private ItemStack processSurplus(ItemStack item) {
        for(int a = 0; a < 4; ++a) {
            if (item.isEmpty()) {
                return ItemStack.EMPTY;
            }

            int slot = this.getInventory().getSlots() - 4 + a;
            ItemStack stackInSlot = this.getInventory().getStackInSlot(slot);
            if (stackInSlot.isEmpty()) {
                this.getInventory().setStackInSlot(slot, item);
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isItemEqual(item) && stackInSlot.getCount() < stackInSlot.getMaxStackSize()) {
                if (stackInSlot.getCount() + item.getCount() <= stackInSlot.getMaxStackSize()) {
                    stackInSlot.grow(item.getCount());
                    return ItemStack.EMPTY;
                }

                int room_left = stackInSlot.getMaxStackSize() - stackInSlot.getCount();
                stackInSlot.grow(room_left);
                item.shrink(room_left);
            }
        }

        return item;
    }

    private boolean canFitResult(ItemStack result) {
        ItemStack resSlot = this.getInventory().getStackInSlot(this.getOutputSlotNum());
        if (!resSlot.isEmpty() && !result.isEmpty()) {
            if (!resSlot.isItemEqual(result)) {
                return false;
            }

            if (resSlot.getCount() + result.getCount() > resSlot.getMaxStackSize()) {
                return false;
            }
        }

        return true;
    }

    public MasonryBenchRecipeBase getResult() {
        if (this.syncMasonryBench != null && this.craftMatrix != null) {
            for(int a = 0; a < this.getOutputSlotNum(); ++a) {
                this.craftMatrix.setInventorySlotContents(a, this.getInventory().getStackInSlot(a));
            }

            return CraftingManagerMasonryBench.findMatchingRecipe(this, this.craftMatrix, this.world);
        } else {
            return null;
        }
    }

    public String getResultName() {
        if (!(this.getRecipe() instanceof MasonryBenchRecipeBase)) {
            return I18n.format("gui.no_project_set", new Object[0]);
        } else {
            MasonryBenchRecipeBase MasonryBenchRecipe = (MasonryBenchRecipeBase)this.getRecipe();
            return MasonryBenchRecipe.getCraftingResult().getDisplayName();
        }
    }

    public void updateCraftingData() {
        if (!this.world.isRemote && (this.getRecipe() instanceof MasonryBenchRecipeBase || this.getRecipe() == null)) {
            MasonryBenchRecipeBase oldRecipe = (MasonryBenchRecipeBase)this.getRecipe();
            MasonryBenchRecipeBase newRecipe = this.getResult();
            this.setRecipe(newRecipe);
            if (!this.canCraft(newRecipe) && this.progress > 0.0F) {
                this.progress = 0.0F;
            }

            if (newRecipe != null && oldRecipe != null && !newRecipe.equals(oldRecipe)) {
                this.progress = 0.0F;
            }

            if (this.progress > this.progressMax) {
                this.progress = this.progressMax - 1.0F;
            }
        }

    }

    public boolean canCraft(MasonryBenchRecipeBase MasonryBenchRecipe) {
        if (MasonryBenchRecipe == null) {
            return false;
        }
        else {
            return this.progressMax > 0.0F ? this.canFitResult(MasonryBenchRecipe.getCraftingResult()) : false;
        }
    }

    public void setProgressMax(int i) {
        this.progressMax = (float)i;
    }

    public void setContainer(ContainerMasonryBench container) {
        this.syncMasonryBench = container;
        this.craftMatrix = new MasonryBenchCraftMatrix(this, this.syncMasonryBench, 4, 4);
    }

    public int getProgressBar(int i) {
        return (int)Math.ceil((double)((float)i / this.progressMax * this.progress));
    }

    public boolean doesPlayerKnowCraft(EntityPlayer user) {
        IRecipeMFR recipe = this.getRecipe();
        return recipe instanceof MasonryBenchRecipeBase && !recipe.getRequiredResearch().equals("none") ? ResearchLogic.getResearchCheck(user, ResearchLogic.getResearch(recipe.getRequiredResearch())) : true;
    }

    private void addXP(EntityPlayer smith, MasonryBenchRecipeBase MasonryBenchRecipe) {
        if (!this.world.isRemote && MasonryBenchRecipe.getSkill() != Skill.NONE) {
            float baseXP = this.progressMax / 10.0F;
            MasonryBenchRecipe.giveSkillXp(smith, (float)((int)baseXP + 1));
            MasonryBenchRecipe.giveVanillaXp(smith, baseXP, 1);
        }

    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.tier = nbt.getInteger("tier");
        this.inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
        this.progress = nbt.getFloat("progress");
        this.progressMax = nbt.getFloat("progress_max");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("tier", this.tier);
        nbt.setTag("inventory", this.inventory.serializeNBT());
        nbt.setFloat("progress", this.progress);
        nbt.setFloat("progress_max", this.progressMax);
        if (this.getRecipe() != null) {
            nbt.setString("recipe", this.getRecipe().getName());
        }

        return nbt;
    }

    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.inventory) : super.getCapability(capability, facing);
    }
}
