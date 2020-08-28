package com.maximum.abouttea.impl;

import com.google.gson.JsonObject;
import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.init.ModRecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DryerRecipes implements IDryerRecipe {
    private final Ingredient input;
    private final int ticks;
    private final ItemStack output;
	private final ResourceLocation id;

    public DryerRecipes(ResourceLocation id, int ticks, Ingredient input, ItemStack output) {
        this.ticks = ticks;
        this.input = input;
        this.output = output;
		this.id = id;
    }

    @Override
    public boolean matches(ItemStack stack) {
        return input.test(stack);
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getTicks() {
        return ticks;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeType.DRYER_SERIALIZER;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DryerRecipes> {

        @Override
        public DryerRecipes read(ResourceLocation recipeId, JsonObject json) {
            Ingredient input = Ingredient.deserialize(json.get("input"));
            ItemStack output = CraftingHelper.getItemStack(json.get("output").getAsJsonObject(), true);
            int ticks = JSONUtils.getInt(json, "time");
            return new DryerRecipes(recipeId, ticks, input, output);
        }

        @Nullable
        @Override
        public DryerRecipes read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient input = Ingredient.read(buffer);
            ItemStack output = buffer.readItemStack();
            int ticks = buffer.readVarInt();
            return new DryerRecipes(recipeId,ticks, input, output);
        }

        @Override
        public void write(PacketBuffer buffer, DryerRecipes recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.output);
            buffer.writeVarInt(recipe.getTicks());
        }
    }
}
