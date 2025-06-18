package com.pippip5789.mfrex.core.content.tailor;

import minefantasy.mfr.MineFantasyReforged;
import minefantasy.mfr.container.ContainerBase;
import minefantasy.mfr.util.GuiHelper;
import minefantasy.mfr.util.TextureHelperMFR;
import minefantasy.mfr.util.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTailorBench extends GuiContainer {

	private final TileEntityTailorBench tile;
	private  final TailorBenchRecipeBase tailorBenchRecipe;
	private final int regularXSize = 176;

	public GuiTailorBench(ContainerBase container, TileEntityTailorBench tile) {
		super(container);
		if (tile.getRecipe() instanceof TailorBenchRecipeBase) {
			this.tailorBenchRecipe = (TailorBenchRecipeBase) tile.getRecipe();
		}
		else {
			this.tailorBenchRecipe = null;
		}
		this.xSize = 195;
		this.ySize = 240;
		this.tile = tile;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		boolean knowsCraft = tile.doesPlayerKnowCraft(mc.player);
		String s = MineFantasyReforged.isDebug() ? "Tailor Bench Crafting" : knowsCraft ? I18n.format(tile.getResultName()) : "????";
		this.fontRenderer.drawString(s, 10, 8, 0);

		int xPoint = (this.width - this.xSize) / 2;
		int yPoint = (this.height - this.ySize) / 2;

		if (knowsCraft && !tile.getResultName().equalsIgnoreCase("") && tailorBenchRecipe != null) {
			if (tailorBenchRecipe.getToolType() != null) {
				if (x < xPoint && x > xPoint - 20 && y < yPoint + 20 && y > yPoint) {
					String s2 = tailorBenchRecipe.getToolType().getDisplayName() + ", "
							+ (tailorBenchRecipe.getToolTier() > -1
							? I18n.format("attribute.mfcrafttier.name") + " "
							+ tailorBenchRecipe.getToolTier()
							: I18n.format("attribute.nomfcrafttier.name"));
					this.fontRenderer.drawStringWithShadow(s2, -18, -12,
							isToolSufficient() ? 16777215 : GuiHelper.getColourForRGB(150, 0, 0));
				}
			}
			if (x < xPoint + regularXSize + 20 && x > xPoint + regularXSize && y < yPoint + 20 && y > yPoint) {
				String s2 = I18n.format("tooltype.tailor_bench") + ", "
						+ (tailorBenchRecipe.getTailorBenchTier() > -1
						? I18n.format("attribute.mfcrafttier.name") + " "
						+ tailorBenchRecipe.getTailorBenchTier()
						: I18n.format("attribute.nomfcrafttier.name"));
				this.fontRenderer.drawStringWithShadow(s2, regularXSize - fontRenderer.getStringWidth(s2) + 18,
						-12, 16777215);
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TextureHelperMFR.getResource("textures/gui/tailor_bench.png"));
		//this.mc.getTextureManager().bindTexture(TextureHelperMFR.getResource(getTextureFromBlock(tile.getBlockType())));
		int xPoint = (this.width - this.xSize) / 2;
		int yPoint = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(xPoint, yPoint, 0, 0, this.xSize, this.ySize);

		if (tile.progressMax > 0 && tile.progress > 0) {
			int progressWidth = (int) (160F / tile.progressMax * tile.progress);
			this.drawTexturedModalRect(xPoint + 8, yPoint + 21, 0, 240, progressWidth, 3);
		}
		if (tile.doesPlayerKnowCraft(mc.player) && tailorBenchRecipe != null
				&& !tile.getResultName().equalsIgnoreCase("")) {
			GuiHelper.renderToolIcon(this, "tailor_bench", tailorBenchRecipe.getTailorBenchTier(), xPoint + regularXSize, yPoint,
					true, true);

			if (tailorBenchRecipe.getToolType() != null) {
				GuiHelper.renderToolIcon(this, tailorBenchRecipe.getToolType().getName(),
						tailorBenchRecipe.getToolTier(), xPoint - 20, yPoint, isToolSufficient(), true);
			}
		}
	}

	private boolean isToolSufficient() {
		if (mc.player != null) {
			return ToolHelper.isToolSufficient(mc.player.getHeldItem(EnumHand.MAIN_HAND),
					tailorBenchRecipe.getToolType(), tailorBenchRecipe.getToolTier());
		}
		return false;
	}

	private void renderItem(ItemStack itemstack, int x, int y, int mouseX, int mouseY) {
		itemRender.renderItemAndEffectIntoGUI(itemstack, x, y);
		itemRender.renderItemOverlayIntoGUI(this.fontRenderer, itemstack, x, y, this.mc.getTextureManager().toString());
	}

	private void renderItemName(ItemStack itemstack, int x, int y, int mouseX, int mouseY) {
		if (this.isPointInRegion(x - guiLeft, y - guiTop, 16, 16, mouseX, mouseY)) {
			this.renderToolTip(itemstack, mouseX, mouseY);
		}
	}

	private String getTextureFromBlock(Block block) {
		/*if (block == MFRExBlockInit.TailorBench) {
			return "textures/gui/Tailor_bench_granite.png";
		}*/
		return null;
	}
}