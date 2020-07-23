package com.maximum.abouttea.api.recipes;

import com.maximum.abouttea.AboutTea;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IMixerRecipe extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID=new ResourceLocation(AboutTea.MODID,"mixer_recipe");

    boolean matches(ItemStack output);

    RecipeWrapper getInputs();
    //忽略下面的方法
    @Nonnull
    @Override
    default ItemStack getCraftingResult(@Nonnull IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    default boolean matches(@Nonnull IInventory inv, @Nonnull World world) {
        return false;
    }

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }
}
