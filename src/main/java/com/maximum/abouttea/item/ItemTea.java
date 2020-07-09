package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemTea extends Item implements IItemTea {
    private int color;
    public ItemTea(int color) {
        super(new Properties().group(ItemGroup.MISC));
        this.color=color;
    }
    @Override
    public int getColor() {
        return color;
    }
}
