package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.world.TeaTreeGenConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
//TODO
public class ModFeature {
    private static final DeferredRegister<Feature<?>> FEATURES=new DeferredRegister<>(ForgeRegistries.FEATURES, AboutTea.MODID);
    //public static final Feature<TeaTreeGenConfig> teatreegen=FEATURES.register("teatree",()->{return });
}
