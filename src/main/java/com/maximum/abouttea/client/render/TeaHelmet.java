package com.maximum.abouttea.client.render;

import com.maximum.abouttea.client.model.armor.CustomArmor;
import com.maximum.abouttea.client.model.armor.ModelHelmet;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;

import javax.annotation.Nonnull;

public class TeaHelmet extends CustomArmor {
    private static ModelHelmet HELMET=new ModelHelmet(0.0625f);
    public TeaHelmet(float modelSize) {
        super(modelSize);
    }

    @Override
    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int overlayLight, boolean hasEffect) {
        if(isChild){
            float s=1.5f/ childBodyScale;
            matrix.scale(s,s,s);
        }
        HELMET.render(matrix,renderer.getBuffer(RenderType.getTranslucent()),light,overlayLight,255,255,255,1);
    }
}
