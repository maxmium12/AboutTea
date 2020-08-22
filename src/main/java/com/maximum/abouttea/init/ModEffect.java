package com.maximum.abouttea.init;

import com.maximum.abouttea.effect.EffectExplosive;
import com.maximum.abouttea.effect.EffectLightning;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maximum.abouttea.AboutTea.prefix;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffect {
    public static EffectExplosive EFFECT_EXPLOSIVE = (EffectExplosive) new EffectExplosive().setRegistryName(prefix("explosive"));
    public static EffectLightning EFFECT_LIGHTNING = (EffectLightning) new EffectLightning().setRegistryName(prefix("lightning"));

    @SubscribeEvent
    public static void registerEffect(RegistryEvent.Register<Effect> event) {
        event.getRegistry().register(EFFECT_EXPLOSIVE);
        event.getRegistry().register(EFFECT_LIGHTNING);
    }
}
