package com.maximum.abouttea.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.world.server.ServerWorld;

public class EffectLightning extends InstantEffect {
    private int tick;

    public EffectLightning() {
        super(EffectType.NEUTRAL, 0x0000cd);
    }

    public void performEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isRemote) {
            ServerWorld world = (ServerWorld) entity.getEntityWorld();
            LightningBoltEntity lightning = new LightningBoltEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), false);
            lightning.setCaster((ServerPlayerEntity) entity);
            for (int i = 0; i < amplifier; i++)
                world.addLightningBolt(lightning);
        }
    }
}
