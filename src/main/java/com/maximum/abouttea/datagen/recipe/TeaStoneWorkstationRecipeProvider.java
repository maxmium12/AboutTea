package com.maximum.abouttea.datagen.recipe;

import com.google.gson.JsonObject;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TeaStoneWorkstationRecipeProvider extends RecipeProvider {
    public TeaStoneWorkstationRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        new TeaStoneWorkstationRecipeBuilder(ModItems.itemTeaHelmet.get(),1,500).patternLine("TTT").patternLine("T T").key('T',ModItems.itemTeaStone.get()).addCriterion("has_item",hasItem(ModBlock.blockTeaStoneWorkStation.get())).build(consumer);
    }
}
