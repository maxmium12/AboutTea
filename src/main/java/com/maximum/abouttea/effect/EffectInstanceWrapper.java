package com.maximum.abouttea.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInstanceWrapper {
    private final ResourceLocation effect;
    private final int duration;
    private final int amplifier;

    public EffectInstanceWrapper(ResourceLocation effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public EffectInstance getEffectInstance() {
        Effect effect = ForgeRegistries.POTIONS.getValue(this.effect);
        if (effect == null) throw new IllegalArgumentException("Minecraft don't have this effect");
        ;
        return new EffectInstance(effect, duration, amplifier);
    }
}
