package com.maximum.abouttea.client.model.armor;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelHelmet extends Model {
    private final ModelRenderer bone;

    public ModelHelmet(float size) {
        super(RenderType::getEntitySolid);
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureOffset(0, 0).addBox(-6.0F, -12.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-6.0F, -14.0F, -5.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-6.0F, -14.0F, -6.0F, 12.0F, 8.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(5.0F, -14.0F, -5.0F, 1.0F, 8.0F, 11.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-6.0F, -14.0F, 5.0F, 11.0F, 8.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-5.0F, -14.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}