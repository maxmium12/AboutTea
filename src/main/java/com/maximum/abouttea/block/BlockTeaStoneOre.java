package com.maximum.abouttea.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockTeaStoneOre extends Block {
    public BlockTeaStoneOre() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(0.5f).harvestLevel(3).harvestTool(ToolType.PICKAXE));
    }
}
