package com.maximum.abouttea.api.recipes.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTea;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.LinkedList;

public class DryerRecipes implements IDryerRecipe {
    private final Ingredient input;
    private final int ticks;
    private final ItemStack output;
    public DryerRecipes(int ticks,Ingredient input,ItemStack output){
        this.ticks=ticks;
        this.input=input;
        this.output=output;
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
        return new ResourceLocation(AboutTea.MODID,"dryer_recipes");
    }

    public Ingredient getInput() {
        return input;
    }

    public int getTicks() {
        return ticks;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return new Serializer();
    }
    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DryerRecipes>{

        @Override
        public DryerRecipes read(ResourceLocation recipeId, JsonObject json) {
            Ingredient input=Ingredient.deserialize(json.get("input"));
            ItemStack output= CraftingHelper.getItemStack(json.get("output").getAsJsonObject(),true);
            int ticks=JSONUtils.getInt(json,"time");
            return new DryerRecipes(ticks,input,output);
        }

        @Nullable
        @Override
        public DryerRecipes read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient input=Ingredient.read(buffer);
            ItemStack output=buffer.readItemStack();
            int ticks=buffer.readVarInt();
            return new DryerRecipes(ticks,input,output);
        }

        @Override
        public void write(PacketBuffer buffer, DryerRecipes recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.output);
            buffer.writeVarInt(recipe.getTicks());
        }
    }
}
