package com.maximum.abouttea.block.manual;


import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockTeaDryer extends Block {
    public BlockTeaDryer() {
        super(Properties.create(Material.WOOD).notSolid().hardnessAndResistance(2.0f));
    }
    private static final VoxelShape shape;

    static {
        VoxelShape base = Block.makeCuboidShape(0, 0, 0, 16, 1, 16);
        VoxelShape column1 = Block.makeCuboidShape(0, 1, 0, 15, 8, 1);
        VoxelShape column2 = Block.makeCuboidShape(15, 1, 0, 16, 8, 15);
        VoxelShape column3 = Block.makeCuboidShape(0, 1, 15, 15, 8, 16);
        VoxelShape column4 = Block.makeCuboidShape(0, 1, 1, 1, 8, 16);
        shape = VoxelShapes.or(base, column1, column2, column3, column4);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileTeaSet();
    }
}
