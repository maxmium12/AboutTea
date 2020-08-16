package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerMixer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.maximum.abouttea.AboutTea.prefix;

public class GuiMixer extends ContainerScreen<ContainerMixer> {
    private final ResourceLocation MIXER=prefix("textures/gui/mixer1.png");
    private final int textureWidth=256;
    private final int textureHeight=256;
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
        int isburn=container.getData().get(1);
        this.blit(i + 80, j + 67 + 14 - isburn*14, 176, 12 - isburn*14, 21, isburn*14);
        int height = getProgressHeight();
        blit(i + 17, j + 7 + 33 - height, 198, 33 - height, 12, height);
    }
    private int getProgressHeight(){
        int maxTicks = container.getData().get(2);
        if(maxTicks > 0) return (int) Math.floor(container.getData().get(0) * 33/ container.getData().get(2));
        return 0;
    }
}
