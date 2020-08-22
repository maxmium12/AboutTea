package com.maximum.abouttea.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

import static com.maximum.abouttea.AboutTea.prefix;

public abstract class GuiEnergyBar<T extends Container> extends ContainerScreen<T> {
    private static final ResourceLocation ENERGY_BAR = prefix("textures/gui/energy_bar.png");
    private static final ResourceLocation ENERGY_BAR_FULL = prefix("textures/gui/energy_bar_full.png");
    private final int maxEnergy;

    public GuiEnergyBar(T screenContainer, PlayerInventory inv, ITextComponent titleIn, int maxEnergy) {
        super(screenContainer, inv, titleIn);
        this.maxEnergy = maxEnergy;
    }

    public void drawBackBar(int energy, int x, int y) {
        double percentEmpty = (maxEnergy - energy) / maxEnergy;
        Minecraft.getInstance().getTextureManager().bindTexture(ENERGY_BAR_FULL);
        blit(x, y, 0, 0, 4, 50); //渲染能量条背景
        Minecraft.getInstance().getTextureManager().bindTexture(ENERGY_BAR);
        //blit(x, y, 0, 0, 4, (int) (69 * percentEmpty)); //渲染能量条空的部分
    }

    public void drawFrontBar(int x, int y, int mouseX, int mouseY, int energy) {
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        int trueX = mouseX - i, trueY = mouseY - j;
        if (isPointInRegion(x, y, 4, 69, mouseX, mouseY)) {
            renderTooltip(Arrays.asList(String.format("%d/ %d FE", energy, maxEnergy)), trueX, trueY);
        }
    }

    @Override
    protected abstract void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY);
}
