package com.maximum.abouttea.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroup {
    public static final ItemGroup GROUP=new AboutteaGroup();
    public static class AboutteaGroup extends ItemGroup {
        public AboutteaGroup() {
            super("about_tea");
        }
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.itemTeaStone.get());
        }
    }
}
