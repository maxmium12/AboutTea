package com.maximum.abouttea.item.gear;

import com.maximum.abouttea.client.model.armor.CustomArmor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public interface ISpecialModel {
    @Nonnull
    @OnlyIn(Dist.CLIENT)
    CustomArmor getModel();
}
