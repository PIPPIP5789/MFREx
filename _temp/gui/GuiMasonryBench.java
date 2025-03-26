package _temp.gui;

import com.pippip5789.mfrex.MFREx;
import _temp.init.MFRExBlocks;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMasonryBench extends MFRExGuiContainerBase {

    public GuiMasonryBench(Container inventorySlotsIn, InventoryPlayer playerInventory) {
        super(inventorySlotsIn, playerInventory, new ResourceLocation(MFREx.MODID, "textures/gui/gui_masonry_bench.png"), MFRExBlocks.masonryBench);
    }

}
