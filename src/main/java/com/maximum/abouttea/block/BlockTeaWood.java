package com.maximum.abouttea.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockTeaWood extends Block {
    public BlockTeaWood() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(0.3f));
    }
}
