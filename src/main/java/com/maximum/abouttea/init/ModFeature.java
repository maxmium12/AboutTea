package com.maximum.abouttea.init;

import com.maximum.abouttea.util.RegistryUtil;
import com.maximum.abouttea.world.TeaTreeGen;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;

//TODO
public class ModFeature {
    public static final RegistryObject<TeaTreeGen> TEA_TREE_GEN = RegistryUtil.registryFeature("tea_tree", () -> new TeaTreeGen(TreeFeatureConfig::deserializeFoliage));
    //public static final Feature<TeaTreeGenConfig> teatreegen=FEATURES.register("teatree",()->{return });
}
