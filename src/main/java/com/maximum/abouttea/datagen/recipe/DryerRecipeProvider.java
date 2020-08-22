package com.maximum.abouttea.datagen.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.util.SerializeUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.maximum.abouttea.AboutTea.prefix;

public class DryerRecipeProvider extends RecipeProvider {
    public DryerRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(new FinishRecipe(prefix("dry_green_tea_recipe"), Ingredient.fromItems(ModTea.getTea("green_tea")), new ItemStack(ModItems.itemDryGreenTea.get()), 200));
    }

    private static class FinishRecipe implements IFinishedRecipe {
        private final Ingredient input;
        private final ItemStack output;
        private final int time;
        private final ResourceLocation id;

        public FinishRecipe(ResourceLocation id, Ingredient input, ItemStack output, int time) {
            this.input = input;
            this.output = output;
            this.time = time;
            this.id = id;
        }

        @Override
        public void serialize(JsonObject json) {
            json.add("input", input.serialize());
            json.add("output", SerializeUtil.serializeStack(output));
            json.add("time", new JsonPrimitive(time));
        }

        @Override
        public ResourceLocation getID() {
            return id;
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return ModRecipeType.DRYER_SERIALIZER;
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
