package com.maximum.abouttea.block;


import com.maximum.abouttea.init.ModTiles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

public class BlockTeaGenerator extends Block {
    public BlockTeaGenerator() {
        super(Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).harvestLevel(2).hardnessAndResistance(2f));
    }
    @Override
    public boolean hasTileEntity(@Nonnull BlockState state) {
        return true;
    }
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world)
    {
        return ModTiles.TEA_GENERATOR_TILE.get().create();
    }
}
