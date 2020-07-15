package com.maximum.abouttea.item;

import com.maximum.abouttea.init.ModGroup;
import net.minecraft.item.Item;

public class ItemTeaBook extends Item {
    public ItemTeaBook() {
        super(new Properties().group(ModGroup.GROUP).maxStackSize(1));
    }

}
