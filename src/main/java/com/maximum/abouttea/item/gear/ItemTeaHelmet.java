package com.maximum.abouttea.item.gear;

import com.maximum.abouttea.init.ModGroup;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.item.ItemTea;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemTeaHelmet extends ArmorItem {
    public ItemTeaHelmet() {
        super(new TeaMaterial(), EquipmentSlotType.HEAD, new Properties().maxStackSize(1).group(ModGroup.GROUP));

    }
    public static ItemTea getTeaInArmor(ItemStack stack){
        if(stack.getChildTag("tea")!=null){
            ItemStack stack1=ItemStack.read(stack.getChildTag("tea"));
            if(stack1.getItem() instanceof ItemTea){
                return (ItemTea) stack1.getItem();
            }
        }
        return ModTea.getTea("green_tea");
    }
    public static void setTeaInArmor(ItemStack stack){
        stack.getOrCreateTag().put("tea",stack.serializeNBT());
    }
}
