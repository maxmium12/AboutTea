package com.maximum.abouttea.block;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockTeaWood extends LogBlock {
    public BlockTeaWood() {
        super(MaterialColor.WOOD,Properties.create(Material.WOOD).hardnessAndResistance(0.3f));
    }
}
