package com.maximum.abouttea.block;

import com.maximum.abouttea.init.ModFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

import static com.maximum.abouttea.world.FeatureConfig.TEA_TREE_CONFIG;

public class TeaTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return ModFeature.TEA_TREE_GEN.get().withConfiguration(TEA_TREE_CONFIG);
    }
}
