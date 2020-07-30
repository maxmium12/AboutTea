package com.maximum.abouttea.world;

import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class TeaTreeGenConfig extends BaseTreeFeatureConfig {
    public TeaTreeGenConfig(BlockStateProvider trunkProviderIn, BlockStateProvider leavesProviderIn, List<TreeDecorator> decoratorsIn, int baseHeightIn) {
        super(trunkProviderIn, leavesProviderIn, decoratorsIn, baseHeightIn);
    }
    @Override
    protected TeaTreeGenConfig setSapling(IPlantable sapling) {
        super.setSapling(sapling);
        return this;
    }
}
