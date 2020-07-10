package com.maximum.abouttea.client.render;

import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.item.ItemTeaCup;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.util.RenderUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.concurrent.atomic.AtomicReference;

public class RenderTeaSet extends TileEntityRenderer<TileTeaSet> {
    private final ResourceLocation water=Fluids.WATER.getAttributes().getStillTexture();
    public RenderTeaSet(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaSet tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        BlockPos pos=tileEntityIn.getPos();
        matrixStackIn.translate(pos.getX(),pos.getY(),pos.getZ());
        //渲染一个杯子
        AtomicReference<ItemTea> tea=null;
        tileEntityIn.getCupHandler().ifPresent(iItemHandler -> {
            tea.set((ItemTea) ItemTeaCup.getTea(iItemHandler.getStackInSlot(0)).getItem());
        });
        int color= tea.get().getColor();
        if(tea.get()!= Items.AIR){
            //TODO 渲染杯子本身
            //渲染杯里的茶水
            TextureAtlasSprite sprite= RenderUtil.getSpite(water);
            IVertexBuilder builder=bufferIn.getBuffer(RenderType.getWaterMask());
            matrixStackIn.translate(0,0.2,0);
            float red = (float)(color >> 16 & 255) / 255.0F;
            float blue = (float)(color >> 8 & 255) / 255.0F;
            float green = (float)(color & 255) / 255.0F;
            builder.pos(matrixStackIn.getLast().getMatrix(),0.2f,0f,0.2f).color(red,blue,green,1f).tex(sprite.getMinU(),sprite.getMinV());
            builder.pos(matrixStackIn.getLast().getMatrix(),0.2f,0f,0.2f).color(red,blue,green,1f).tex(sprite.getMaxU(),sprite.getMinV());
            builder.pos(matrixStackIn.getLast().getMatrix(),0.2f,0f,0.2f).color(red,blue,green,1f).tex(sprite.getMinU(),sprite.getMaxV());
            builder.pos(matrixStackIn.getLast().getMatrix(),0.2f,0f,0.2f).color(red,blue,green,1f).tex(sprite.getMaxU(),sprite.getMaxV());
        }
    }
}
