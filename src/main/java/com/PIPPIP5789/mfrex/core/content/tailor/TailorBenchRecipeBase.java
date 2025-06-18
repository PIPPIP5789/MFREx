package com.pippip5789.mfrex.core.content.tailor;

import minefantasy.mfr.constants.Skill;
import minefantasy.mfr.constants.Tool;
import minefantasy.mfr.recipe.IRecipeMFR;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

public abstract class TailorBenchRecipeBase extends IForgeRegistryEntry.Impl<TailorBenchRecipeBase> implements IRecipeMFR {
    public static final int MAX_WIDTH = 4;
    public static final int MAX_HEIGHT = 4;
    protected ItemStack output;
    protected NonNullList<Ingredient> inputs;
    protected final int toolTier;
    protected final int tailorBenchTier;
    protected final int craftTime;
    protected final Tool toolType;
    protected final SoundEvent soundOfCraft;
    protected final String research;
    protected final Skill skillUsed;
    protected Integer skillXp;
    protected float vanillaXp;

    public TailorBenchRecipeBase(ItemStack output, NonNullList<Ingredient> inputs, int toolTier, int tailorBenchTier, int craftTime, String toolType, SoundEvent soundOfCraft, String research, Skill skillUsed, int skillXp, float vanillaXp) {
        this.output = output;
        this.inputs = inputs;
        this.toolTier = toolTier;
        this.tailorBenchTier = tailorBenchTier;
        this.craftTime = craftTime;
        this.toolType = Tool.fromName(toolType);
        this.soundOfCraft = soundOfCraft;
        this.research = research;
        this.skillUsed = skillUsed;
        this.skillXp = skillXp;
        this.vanillaXp = vanillaXp;
    }

    abstract boolean matches(TailorBenchCraftMatrix var1, @Nonnull World var2);

    public int getRecipeSize() {
        return 0;
    }

    public String getName() {
        return CraftingManagerTailorBench.getRecipeName(this);
    }

    public ItemStack getCraftingResult() {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    public int getCraftTime() {
        return this.craftTime;
    }

    public int getToolTier() {
        return this.toolTier;
    }

    public int getTailorBenchTier() {
        return this.tailorBenchTier;
    }

    public Tool getToolType() {
        return this.toolType;
    }

    public SoundEvent getSound() {
        return this.soundOfCraft;
    }

    public ItemStack getTailorBenchRecipeOutput() {
        return this.output;
    }

    public String getRequiredResearch() {
        return this.research;
    }

    public Skill getSkill() {
        return this.skillUsed;
    }

    public int getSkillXp() {
        return this.skillXp;
    }

    public float getVanillaXp() {
        return this.vanillaXp;
    }

    public int getWidth() {
        return 4;
    }

    public int getHeight() {
        return 4;
    }

    public boolean shouldSlotGiveSkillXp() {
        return false;
    }
}
