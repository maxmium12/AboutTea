package com.maximum.abouttea.world;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
//TODO
public class TeaTreeGen extends AbstractTreeFeature<TeaTreeGenConfig> {
    private List<BlockPos> leaves = Lists.newArrayList();
    private int minheight=6;
    public TeaTreeGen(Function<Dynamic<?>, ? extends TeaTreeGenConfig> p_i225797_1_) {
        super(p_i225797_1_);
    }

    @Override
    public boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TeaTreeGenConfig configIn) {
        int treeheight=minheight;
        if(rand.nextInt(3)==2){
            treeheight+=2;
            if(rand.nextInt(3)==2){
                treeheight+=2;
            }
        }
        return false;
    }
}
