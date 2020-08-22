package com.maximum.abouttea.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.world.Explosion;

public class EffectExplosive extends InstantEffect {
    public EffectExplosive() {
        super(EffectType.NEUTRAL, 0xff0000);
    }

    public void performEffect(LivingEntity entity, int amplifier) {
        entity.getEntityWorld().createExplosion(entity, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 4 * amplifier, Explosion.Mode.BREAK);
    }
}
