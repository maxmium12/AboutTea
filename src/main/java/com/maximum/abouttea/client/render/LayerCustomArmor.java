package com.maximum.abouttea.client.render;

import com.maximum.abouttea.client.model.armor.CustomArmor;
import com.maximum.abouttea.item.gear.ISpecialModel;
import com.maximum.abouttea.item.gear.ItemTeaHelmet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LayerCustomArmor<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends BipedArmorLayer<T,M,A> {

    public LayerCustomArmor(IEntityRenderer<T, M> p_i50936_1_, A p_i50936_2_, A p_i50936_3_) {
        super(p_i50936_1_, p_i50936_2_, p_i50936_3_);
    }
    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer renderer, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        matrix.push();
        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        for(EquipmentSlotType slot:EquipmentSlotType.values()){
            ItemStack stack = entity.getItemStackFromSlot(slot);
            Item item = stack.getItem();
            if(item instanceof ISpecialModel && item instanceof ArmorItem){
                ArmorItem armor=(ArmorItem) item;
                if(armor.getEquipmentSlot()==slot){
                    int color=ItemTeaHelmet.getTeaInArmor(stack).getColor();
                    float red = (float)(color >> 16 & 255) / 255.0F;
                    float blue = (float)(color >> 8 & 255) / 255.0F;
                    float green = (float)(color & 255) / 255.0F;
                    CustomArmor model=((ISpecialModel)item).getModel();
                    RenderSystem.color4f(red,green,blue,0.4f);
                    model.setRotationAngles(entity,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
                    model.render(matrix,renderer,packedLightIn, OverlayTexture.NO_OVERLAY,stack.hasEffect());
                }
            }
        }
        RenderSystem.color4f(1,1,1,1);
        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();
        matrix.pop();
    }

}
