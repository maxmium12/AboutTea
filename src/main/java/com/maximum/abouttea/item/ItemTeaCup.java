package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemTeaCup extends Item {
    public ItemTeaCup() {
        super(new Properties().group(ItemGroup.MISC));
    }
    public static void setTea(ItemStack cup,ItemTea tea){
        ItemStack teastack=new ItemStack(tea);
        CompoundNBT nbt=cup.getOrCreateChildTag("tea");
        nbt.put("tea",teastack.serializeNBT());
    }
    public static ItemStack getTea(ItemStack cup){
        CompoundNBT nbt=cup.getOrCreateChildTag("tea");
        return ItemStack.read(nbt.getCompound("tea"));
    }
}
