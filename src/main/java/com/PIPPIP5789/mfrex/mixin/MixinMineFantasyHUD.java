package com.pippip5789.mfrex.mixin;

import com.pippip5789.mfrex.core.content.masonry.MasonryBenchRecipeBase;
import com.pippip5789.mfrex.core.content.masonry.TileEntityMasonryBench;
import minefantasy.mfr.api.crafting.IBasicMetre;
import minefantasy.mfr.api.crafting.IQualityBalance;
import minefantasy.mfr.client.render.MineFantasyHUD;
import minefantasy.mfr.config.ConfigStamina;
import minefantasy.mfr.container.ContainerCarpenter;
import minefantasy.mfr.entity.EntityCogwork;
import minefantasy.mfr.tile.TileEntityAnvil;
import minefantasy.mfr.tile.TileEntityCarpenter;
import minefantasy.mfr.tile.TileEntityKitchenBench;
import minefantasy.mfr.tile.TileEntityTanningRack;
import minefantasy.mfr.util.GuiHelper;
import minefantasy.mfr.util.PowerArmour;
import minefantasy.mfr.util.ToolHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static betterwithmods.client.ClientEventHandler.mc;

@Mixin(MineFantasyHUD.class)
abstract public class MixinMineFantasyHUD extends Gui {

    @Final
    @Shadow
    private static Minecraft mc;

    @Shadow
    public abstract BlockPos getClickedBlock();

    @Shadow
    public abstract void bindTexture(String image);

    @Inject(method = "renderGameOverlay", at = @At("TAIL"), remap = false, cancellable = true)
    public void renderGameOverlay(CallbackInfo ci) {
        if (mc.player != null) {
            EntityPlayer player = mc.player;
            World world = player.world;
            BlockPos coords = getClickedBlock();

            TileEntity tile = world.getTileEntity(coords);
            if (tile != null) {
                if (tile instanceof TileEntityMasonryBench) {
                    this.renderCraftMetre(player, (TileEntityMasonryBench) tile);
                }
            }
        }
    }

    private void renderCraftMetre(EntityPlayer player, TileEntityMasonryBench tile) {
        if (player.openContainer instanceof ContainerCarpenter) {
            return;
        }

        boolean knowsCraft = tile.doesPlayerKnowCraft(player);
        GlStateManager.pushMatrix();
        ScaledResolution scaledresolution = new ScaledResolution(mc);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();

        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width / 2 - 86;
        int yPos = height - 69;

        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos + 6, yPos + 12, 90, 20, tile.getProgressBar(160), 3);

        String s = knowsCraft ? tile.getResultName() : "????";
        mc.fontRenderer.drawString(s, xPos + 86 - (mc.fontRenderer.getStringWidth(s) / 2), yPos + 3, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        if (knowsCraft && !tile.getResultName().equalsIgnoreCase("")
                && tile.getRecipe() instanceof MasonryBenchRecipeBase) {
            MasonryBenchRecipeBase carpenterRecipe = (MasonryBenchRecipeBase) tile.getRecipe();
            boolean available = ToolHelper.isToolSufficient(player.getHeldItem(EnumHand.MAIN_HAND), carpenterRecipe.getToolType(), tile.getToolTierNeeded());
            GuiHelper.renderToolIcon(this, carpenterRecipe.getToolType().getName(),
                    tile.getToolTierNeeded(), xPos - 20, yPos, available, true);
        }

        GlStateManager.popMatrix();
    }

}
