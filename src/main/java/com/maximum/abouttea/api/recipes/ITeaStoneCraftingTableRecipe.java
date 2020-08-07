package com.maximum.abouttea.api.recipes;

import com.maximum.abouttea.AboutTea;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface ITeaStoneCraftingTableRecipe extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID=new ResourceLocation(AboutTea.MODID,"teastone_workstation");
    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(TYPE_ID).get();
    }
}
