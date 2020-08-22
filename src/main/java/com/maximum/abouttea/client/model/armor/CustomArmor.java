package com.maximum.abouttea.client.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nonnull;

public abstract class CustomArmor extends BipedModel<LivingEntity> {
    public CustomArmor(float modelSize) {
        super(modelSize);
    }

    public abstract void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight, boolean hasEffect);
}
