package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.util.RegistryUtil;
import com.maximum.abouttea.world.TeaTreeGen;
import com.maximum.abouttea.world.TeaTreeGenConfig;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
//TODO
public class ModFeature {
    public static final RegistryObject<TeaTreeGen> TEA_TREE_GEN=RegistryUtil.registryFeature("tea_tree",()-> new TeaTreeGen(TreeFeatureConfig::deserializeFoliage));
    //public static final Feature<TeaTreeGenConfig> teatreegen=FEATURES.register("teatree",()->{return });
}
