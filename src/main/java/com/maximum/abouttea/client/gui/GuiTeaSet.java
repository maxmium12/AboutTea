package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerTeaSet;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class GuiTeaSet extends ContainerScreen<ContainerTeaSet> {
    public GuiTeaSet(ContainerTeaSet screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
