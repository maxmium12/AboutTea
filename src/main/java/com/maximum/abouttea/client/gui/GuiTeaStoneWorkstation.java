package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerTeaStoneWorkStaion;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class GuiTeaStoneWorkstation extends ContainerScreen<ContainerTeaStoneWorkStaion> {
    private ContainerTeaStoneWorkStaion container;
    public GuiTeaStoneWorkstation(ContainerTeaStoneWorkStaion screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.container = screenContainer;
    }
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
