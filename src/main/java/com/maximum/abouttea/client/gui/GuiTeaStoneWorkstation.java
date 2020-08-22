package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerTeaStoneWorkStation;
import com.maximum.abouttea.tile.machine.TileTeaStoneWorkstation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.maximum.abouttea.AboutTea.prefix;

public class GuiTeaStoneWorkstation extends GuiEnergyBar<ContainerTeaStoneWorkStation> {
    private static final ResourceLocation WORKSTATION = prefix("textures/gui/tea_stone_workstation1.png");
    private final ContainerTeaStoneWorkStation container;
    private final int textureWidth = 256;
    private final int textureHeight = 256;

    public GuiTeaStoneWorkstation(ContainerTeaStoneWorkStation screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, TileTeaStoneWorkstation.getMaxEnergy());
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
        Minecraft.getInstance().getRenderManager().textureManager.bindTexture(WORKSTATION);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
        // drawBackBar(container.getData().get(1), i + 154, j + 6);
    }

    private int getProgressWidth() {
        int maxTicks = container.getData().get(0);
        if (maxTicks > 0) return (int) Math.floor(container.getData().get(0) * 21 / maxTicks);
        return 0;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawFrontBar(154, 10, mouseX, mouseY, container.getData().getEnergy());
    }
}
