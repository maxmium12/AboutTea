package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.init.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemTea extends Item implements IItemTea {
    private int color;
    private int tier;
    public ItemTea(int color,int tier) {
        super(new Properties().group(ModGroup.GROUP));
        this.color=color;
        this.tier=tier;
    }
    @Override
    public int getColor() {
        return color;
    }
    public int getTier(){
        return tier;
    }
}
