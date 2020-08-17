package com.maximum.abouttea.client.gui;

import com.maximum.abouttea.gui.ContainerMachineDryer;
import com.maximum.abouttea.tile.machine.TileMachineDryer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.maximum.abouttea.AboutTea.prefix;

public class GuiMachineDryer extends GuiEnergyBar<ContainerMachineDryer>{
    private static final ResourceLocation DRYER = prefix("textures/gui/machine_dryer.png");
    private final ContainerMachineDryer container;
    private static final int textureWidth = 256;
    private static final int textureHeight = 256;
    public GuiMachineDryer(ContainerMachineDryer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, TileMachineDryer.getMaxEnergy());
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
        Minecraft.getInstance().getRenderManager().textureManager.bindTexture(DRYER);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(i, j, 0, 0, xSize, ySize, textureWidth, textureHeight);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawFrontBar(154, 10, mouseX, mouseY, container.getData().get(4));
    }
}
