package com.maximum.abouttea.block;

import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.item.ItemTea;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockTeaBlock extends Block {
    private final String name;
    public BlockTeaBlock(String name) {
        super(Properties.create(Material.LEAVES));
        this.name=name;
    }
    public ItemTea getTea(){
        return ModTea.getTeas().get(name);
    }
}
