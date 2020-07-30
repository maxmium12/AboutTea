package com.maximum.abouttea.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.maximum.abouttea.api.recipes.impl.MixerRecipes;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTea;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.maximum.abouttea.AboutTea.prefix;

public class MixerRecipeProvider extends RecipeProvider {
    public MixerRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(new FinishRecipe(prefix("dry_green_tea"), 300, new ItemStack(ModItems.itemDryGreenTea.get()), Ingredient.fromItems(ModTea.getTea("green_tea"))));
    }
    private static class FinishRecipe implements IFinishedRecipe{
        private final ResourceLocation id;
        private final int time;
        private final ItemStack output;
        private final List<Ingredient> inputs;
        public FinishRecipe(ResourceLocation id, int time, ItemStack output, Ingredient... inputs){
            this.id=id;
            this.time=time;
            this.output=output;
            this.inputs= Arrays.asList(inputs);
        }
        @Override
        public void serialize(JsonObject json) {
            JsonArray in=new JsonArray();
            for(Ingredient input:inputs){
                in.add(input.serialize());
            }
            json.add("input",in);
            Dynamic<INBT> nbt=new Dynamic<>(NBTDynamicOps.INSTANCE,output.serializeNBT());
            json.add("output", nbt.convert(JsonOps.INSTANCE).getValue().getAsJsonObject());
            json.add("time",new JsonPrimitive(time));
        }

        @Override
        public ResourceLocation getID() {
            return id;
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return ModRecipeType.MIXER_SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject getAdvancementJson() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementID() {
            return null;
        }
    }
}
