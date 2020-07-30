package com.maximum.abouttea.item.gear;

import com.maximum.abouttea.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TeaMaterial implements IArmorMaterial {
    private final int[] armorDamageDeduction=new int[]{3,6,8,3};
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * 33;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return armorDamageDeduction[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(ModItems.itemTeaStone.get());
    }

    @Override
    public String getName() {
        return "tea_stone";
    }

    @Override
    public float getToughness() {
        return 2;
    }
}
