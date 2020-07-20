package com.maximum.abouttea.item;

import com.maximum.abouttea.init.ModGroup;
import net.minecraft.item.Item;

public class ItemTeaMaterial extends Item {
    public ItemTeaMaterial(Properties properties) {
        super(properties.group(ModGroup.GROUP));
    }
}
