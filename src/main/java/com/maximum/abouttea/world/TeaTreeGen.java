package com.maximum.abouttea.world;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractSmallTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class TeaTreeGen extends AbstractSmallTreeFeature<TreeFeatureConfig> {


    public TeaTreeGen(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i225796_1_) {
        super(p_i225796_1_);
    }

    //复制自金合欢树，作了修改
    @Override
    protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn) {
        int i = configIn.baseHeight + rand.nextInt(configIn.heightRandA + 1) + rand.nextInt(configIn.heightRandB + 1);
        int j = configIn.trunkHeight >= 0 ? configIn.trunkHeight + rand.nextInt(configIn.trunkHeightRandom + 1) : i - (configIn.foliageHeight + rand.nextInt(configIn.foliageHeightRandom + 1));
        int k = configIn.foliagePlacer.func_225573_a_(rand, j, i, configIn);
        Optional<BlockPos> optional = this.func_227212_a_(generationReader, i, j, k, positionIn, configIn);
        if (!optional.isPresent()) {
            return false;
        } else {
            BlockPos blockpos = optional.get();
            this.setDirtAt(generationReader, blockpos.down(), blockpos);
            Direction direction = Direction.Plane.HORIZONTAL.random(rand);
            int l = i - rand.nextInt(4) - 1;
            int i1 = 3 - rand.nextInt(3);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            int j1 = blockpos.getX();
            int k1 = blockpos.getZ();
            int l1 = 0;

            for (int i2 = 0; i2 < i; ++i2) {
                int j2 = blockpos.getY() + i2;
                if (i2 >= l && i1 > 0) {
                    j1 += direction.getXOffset();
                    k1 += direction.getZOffset();
                    --i1;
                }

                if (this.setLog(generationReader, rand, blockpos$mutable.setPos(j1, j2, k1), p_225557_4_, boundingBoxIn, configIn)) {
                    l1 = j2;
                }
            }

            BlockPos blockpos1 = new BlockPos(j1, l1, k1);
            configIn.foliagePlacer.func_225571_a_(generationReader, rand, configIn, i, j, k + 1, blockpos1, p_225557_5_);
            return true;
        }
    }
}
