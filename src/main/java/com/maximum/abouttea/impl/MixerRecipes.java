package com.maximum.abouttea.impl;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.maximum.abouttea.api.recipes.IMixerRecipe;
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
import java.util.ArrayList;
import java.util.List;

import static com.maximum.abouttea.AboutTea.prefix;

public class MixerRecipes implements IMixerRecipe {
    private final List<Ingredient> inputs;
    private final ItemStack output;
    int ticks;

    public MixerRecipes(ItemStack output, int ticks, Ingredient[] inputs) {
        if (inputs.length > 4) {
            throw new IllegalArgumentException("Input must lower than 4");
        }
        this.inputs = Lists.newArrayList(inputs);
        this.ticks = ticks;
        this.output = output;
    }

    @Override
    public boolean matches(ItemStack[] inputs) {
        int i = 0;
        if (inputs.length == 0) return false;
        for (ItemStack input : inputs) {
            for (Ingredient ingredient : this.inputs) {
                if (ingredient.test(input)) i++;
            }
        }
        return i == inputs.length;
    }

    @Override
    public List<Ingredient> getInputs() {
        return inputs;
    }

    @Override
    public int getTicks() {
        return ticks;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return prefix("mixer_recipe");
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeType.MIXER_SERIALIZER;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MixerRecipes> {

        @Override
        public MixerRecipes read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
            List<Ingredient> inputs = new ArrayList<>();
            for (JsonElement in : JSONUtils.getJsonArray(json, "inputs")) {
                inputs.add(Ingredient.deserialize(in));
            }
            int ticks = JSONUtils.getInt(json, "time");
            return new MixerRecipes(output, ticks, inputs.toArray(new Ingredient[0]));
        }

        @Nullable
        @Override
        public MixerRecipes read(ResourceLocation recipeId, PacketBuffer buffer) {
            ItemStack output = buffer.readItemStack();
            Ingredient[] inputs = new Ingredient[buffer.readVarInt()];
            for (int i = 0; i < inputs.length; i++) {
                inputs[i] = Ingredient.read(buffer);
            }
            int ticks = buffer.readVarInt();
            return new MixerRecipes(output, ticks, inputs);
        }

        @Override
        public void write(PacketBuffer buffer, MixerRecipes recipe) {
            buffer.writeItemStack(recipe.getRecipeOutput());
            buffer.writeVarInt(recipe.getInputs().size());
            for (Ingredient input : recipe.getInputs()) {
                input.write(buffer);
            }
            buffer.writeVarInt(recipe.getTicks());
        }
    }
}
