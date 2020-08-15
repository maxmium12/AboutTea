package com.maximum.abouttea.block.machine;

import com.maximum.abouttea.init.ModTiles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockMachineMixer extends Block {
    public BlockMachineMixer() {
        super(Properties.create(Material.IRON));
    }
    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTiles.MACHINE_MIXER.get().create();
    }
}
