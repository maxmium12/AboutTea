package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerTeaGenerator;
import com.maximum.abouttea.tile.TileTeaGenerator;
import com.maximum.abouttea.util.EnergyArray;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.maximum.abouttea.AboutTea.prefix;

public class GuiTeaGenerator extends GuiEnergyBar<ContainerTeaGenerator> {
    private final ResourceLocation GENERATOR = prefix("textures/gui/generator.png");
    private final int textureWidth = 256;
    private final int textureHeight = 256;
    private ContainerTeaGenerator container;

    public GuiTeaGenerator(ContainerTeaGenerator screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, TileTeaGenerator.getMaxEnergy());
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
        Minecraft.getInstance().getRenderManager().textureManager.bindTexture(GENERATOR);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
        int height = getBarHeight();
        blit(i + 154, j + 69 + height - 61, 190, height - 61, 4, -height);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawFrontBar(154, 10, mouseX, mouseY, ((EnergyArray) container.getData()).getEnergy());
    }

    private int getBarHeight() {
        IIntArray data = container.getData();
        return data.get(1) * 61 / TileTeaGenerator.getMaxEnergy();
    }
}
