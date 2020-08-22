package com.maximum.abouttea.client.render;

import com.maximum.abouttea.tile.manual.TileTeaDryer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RenderManualTeaDryer extends TileEntityRenderer<TileTeaDryer> {

    public RenderManualTeaDryer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaDryer te, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0.2, 0.5, 0.2);
        matrixStackIn.rotate(new Quaternion(0, 0, 0, true));
        matrixStackIn.scale(0.25f, 0.25f, 0.25f);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                Minecraft.getInstance().getItemRenderer().renderItem(
                        te.getInv().getStackInSlot(j * 2 + i),
                        ItemCameraTransforms.TransformType.FIXED,
                        combinedLightIn,
                        OverlayTexture.NO_OVERLAY,
                        matrixStackIn, bufferIn);
                matrixStackIn.translate(2f * j, 0, 2f * i);
            }
        }
        matrixStackIn.pop();
    }
}
