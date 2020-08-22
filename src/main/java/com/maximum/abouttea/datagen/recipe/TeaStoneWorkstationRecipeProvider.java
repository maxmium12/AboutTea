package com.maximum.abouttea.datagen.recipe;

import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;

import java.util.function.Consumer;

public class TeaStoneWorkstationRecipeProvider extends RecipeProvider {
    public TeaStoneWorkstationRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        new TeaStoneWorkstationRecipeBuilder(ModItems.itemTeaHelmet.get(), 1, 500).patternLine("TTT").patternLine("T T").key('T', ModItems.itemTeaStone.get()).addCriterion("has_item", hasItem(ModBlock.blockTeaStoneWorkStation.get())).build(consumer);
    }
}
