package com.maximum.abouttea.world;

import com.maximum.abouttea.init.ModBlock;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;

public class FeatureConfig {
    public static final TreeFeatureConfig TEA_TREE_CONFIG = new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlock.blockTeaWood.get().getDefaultState()), new SimpleBlockStateProvider(ModBlock.blockTeaTreeLeave.get().getDefaultState()), new AcaciaFoliagePlacer(2, 0)).baseHeight(7).heightRandA(2).heightRandB(2).trunkHeight(0).ignoreVines().build();
}
