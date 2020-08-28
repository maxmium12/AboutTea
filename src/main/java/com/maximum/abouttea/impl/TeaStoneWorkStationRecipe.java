package com.maximum.abouttea.impl;

import com.google.gson.JsonObject;
import com.maximum.abouttea.api.recipes.ITeaStoneCraftingTableRecipe;
import com.maximum.abouttea.init.ModRecipeType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Map;

import static com.maximum.abouttea.AboutTea.prefix;

public class TeaStoneWorkStationRecipe implements ITeaStoneCraftingTableRecipe {
    private final Ingredient[][] inputs;
    private final ItemStack output;
    private final int ticks;
	private final ResourceLocation id;

    public TeaStoneWorkStationRecipe(ResourceLocation id,Ingredient[][] inputs, ItemStack output, int ticks) {
        this.inputs = inputs;
        this.output = output;
        this.ticks = ticks;
		this.id = id;
    }

    public static Ingredient[][] getArrayFromList(NonNullList<Ingredient> inputs, int width, int height) {
        Ingredient[][] inputArray = new Ingredient[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                inputArray[i][j] = inputs.get(i * 3 + j);
            }
        }
        return inputArray;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                if (!inputs[i][j].test(inv.getStackInSlot(i * 3 + j))) return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return getRecipeOutput().copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width <= 3 && height <= 3;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return prefix("tea_stone_workstation_recipe");
    }

    public Ingredient[][] getInputs() {
        return inputs;
    }

    public int getTicks() {
        return ticks;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeType.TEA_STONE_SERIALIZER;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TeaStoneWorkStationRecipe> {

        @Override
        public TeaStoneWorkStationRecipe read(ResourceLocation recipeId, JsonObject json) {
            Map<String, Ingredient> key = ShapedRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
            String[] patterns = ShapedRecipe.shrink(ShapedRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
            int width = patterns[0].length();
            int height = patterns.length;
            NonNullList<Ingredient> inputs = ShapedRecipe.deserializeIngredients(patterns, key, width, height);
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            int ticks = JSONUtils.getInt(json, "ticks");
            Ingredient[][] inputArray = getArrayFromList(inputs, width, height);
            return new TeaStoneWorkStationRecipe(recipeId,inputArray, output, ticks);
        }

        @Nullable
        @Override
        public TeaStoneWorkStationRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int width = buffer.readVarInt();
            int height = buffer.readVarInt();
            int length = buffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(length, Ingredient.EMPTY);
            for (int i = 0; i < length; i++) {
                inputs.set(i, Ingredient.read(buffer));
            }
            Ingredient[][] inputArray = getArrayFromList(inputs, width, height);
            ItemStack output = buffer.readItemStack();
            int ticks = buffer.readVarInt();
            return new TeaStoneWorkStationRecipe(recipeId,inputArray, output, ticks);
        }

        @Override
        public void write(PacketBuffer buffer, TeaStoneWorkStationRecipe recipe) {
            int width = recipe.getInputs()[0].length;
            int height = recipe.getInputs().length;
            buffer.writeVarInt(width);
            buffer.writeVarInt(height);
            buffer.writeVarInt(width * height);
            for (Ingredient[] inputs1 : recipe.inputs) {
                for (Ingredient input : inputs1) {
                    input.write(buffer);
                }
            }
            buffer.writeItemStack(recipe.getRecipeOutput());
            buffer.writeVarInt(recipe.getTicks());
        }
    }
}
