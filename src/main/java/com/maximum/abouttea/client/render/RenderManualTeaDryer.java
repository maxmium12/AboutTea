package com.maximum.abouttea.client.render;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.items.CapabilityItemHandler;

public class RenderManualTeaDryer extends TileEntityRenderer<TileTeaDryer> {

    public RenderManualTeaDryer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaDryer te, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(te.getPos().getX(),te.getPos().getY(),te.getPos().getZ());
        matrixStackIn.rotate(new Quaternion(0,90,0,true));
        for(int i = 0;i < te.getInv().getSlots();i++){
            Minecraft.getInstance().getItemRenderer().renderItem(te.getInv().getStackInSlot(i), ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.translate(i/4,0,0);
            AboutTea.LOGGER.info(te.getInv().getStackInSlot(i).getItem().getRegistryName().getPath()+ i);
        }
        matrixStackIn.pop();
    }
}
