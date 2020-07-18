package com.maximum.abouttea.block;

import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockTeaSet extends Block {
    public BlockTeaSet() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(1f));
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
