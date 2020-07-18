package com.maximum.abouttea.item;

import com.maximum.abouttea.init.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTeaBook extends Item {
    public ItemTeaBook() {
        super(new Properties().group(ModGroup.GROUP).maxStackSize(1));
    }
    public static boolean isUnlockTech(ItemStack stack){
       return stack.hasTag()&&stack.getTag().getBoolean("unlock");
    }
    public static void setUnlockTech(ItemStack stack,boolean unlock){
        stack.getOrCreateTag().putBoolean("unlock",unlock);
    }
}
