package com.maximum.abouttea.client.render;

import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.item.ItemTeaCup;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.util.RenderUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;

import java.util.concurrent.atomic.AtomicReference;

public class RenderTeaSet extends TileEntityRenderer<TileTeaSet> {
    private final ResourceLocation water=Fluids.WATER.getAttributes().getStillTexture();
    public RenderTeaSet(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaSet tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
    private void renderTeaCup(TileTeaSet tile){
        AtomicReference<ItemTea> tea = null;
        tile.getCupHandler().ifPresent(iItemHandler -> {
            tea.set((ItemTea) ItemTeaCup.getTea(iItemHandler.getStackInSlot(0)).getItem());
        });
        int color=tea.get().getColor();
        TextureAtlasSprite sprite= RenderUtil.getSpite(water);

    }
}
