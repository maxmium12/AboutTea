package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerMixer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.maximum.abouttea.AboutTea.prefix;

public class GuiMixer extends ContainerScreen<ContainerMixer> {
    private final ResourceLocation MIXER=prefix("textures/gui/mixer.png");
    private final int textureWidth=176;
    private final int textureHeight=166;
    public GuiMixer(ContainerMixer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getInstance().getRenderManager().textureManager.bindTexture(MIXER);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
        blit(i+79,j+67,0,0,179,0,13,13,13,13);
    }
}
