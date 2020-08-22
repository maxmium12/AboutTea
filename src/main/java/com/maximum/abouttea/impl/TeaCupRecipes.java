package com.maximum.abouttea.impl;

import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.item.ItemTeaCup;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static com.maximum.abouttea.AboutTea.prefix;

public class TeaCupRecipes extends SpecialRecipe {
    public static final ResourceLocation ID = prefix("tea_cup");
    private static final Ingredient INGREDIENT_TEA = Ingredient.fromItems(ModTea.getTeas().values().toArray(new IItemProvider[0]));
    private static final Ingredient INGREDIENT_CUP = Ingredient.fromItems(ModItems.itemTeaCup.get());

    public TeaCupRecipes(ResourceLocation idIn) {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        boolean cup = false;
        boolean tea = false;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (INGREDIENT_CUP.test(stack)) {
                if (cup) {
                    return false;
                }
                cup = true;
            }
            if (INGREDIENT_TEA.test(stack)) {
                if (tea) {
                    return false;
                }
                tea = true;
            }
        }
        return cup && tea;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        ItemStack cup = new ItemStack(ModItems.itemTeaCup.get());
        ItemTea tea = ModTea.getTea("green_tea");
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (INGREDIENT_TEA.test(stack)) {
                ItemTeaCup.setTea(cup, (ItemTea) stack.getItem());
            }
        }
        return cup;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeType.TEA_CUP_SERIALIZER;
    }
}
