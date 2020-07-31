package com.maximum.abouttea.datagen.recipe;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTea;
import net.minecraft.data.*;
import net.minecraft.item.Items;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Nonnull
    @Override
    public String getName() {
        return super.getName() + ": " + AboutTea.MODID;
    }
    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer){
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.itemTeaBook.get(),1).addIngredient(ModTea.getTea("green_tea")).addIngredient(Items.BOOK).addCriterion("has_tea",this.hasItem(ModTea.getTea("green_tea"))).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlock.blockManualTeaDryer.get()).patternLine("WWW").patternLine("S S").key('W',Items.WHEAT).key('S',Items.STICK).addCriterion("has_tea_book",hasItem(ModItems.itemTeaBook.get())).build(consumer);
    }
}
