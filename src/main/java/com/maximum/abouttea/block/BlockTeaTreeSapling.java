package com.maximum.abouttea.block;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockTeaTreeSapling extends SaplingBlock {
    public BlockTeaTreeSapling() {
        super(new TeaTree(), Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
    }
}
