package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerTeaSet;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class GuiTeaSet extends ContainerScreen<ContainerTeaSet> {

    public GuiTeaSet(ContainerTeaSet screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize=176;
        this.ySize=156;
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.renderBackground();
        RenderSystem.color4f(1,1,1,1);
        guiLeft=(this.width-this.xSize)/2;
        guiTop=(this.height-this.xSize)/2;
        blit(guiLeft,guiTop,0,0,xSize,ySize,176,156);
    }
}
