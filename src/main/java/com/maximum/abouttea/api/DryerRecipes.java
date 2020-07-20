package com.maximum.abouttea.api;

import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTea;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class DryerRecipes {
    static {
        registry(new Recipe(3600,new ItemStack(ModTea.getTea("green_tea")),new ItemStack(ModItems.itemDryGreenTea.get())));
    }
    private static final LinkedList<Recipe> recipes=new LinkedList<>();
    public static Recipe findRecipe(ItemStack input){
        for(Recipe recipe:recipes){
            if(ItemStack.areItemStacksEqual(input,recipe.input)){
                return recipe;
            }
        }
        return null;
    }
    public static void registry(Recipe recipe){
        recipes.add(recipe);
    }
    public static class Recipe{
        public int ticks;
        public ItemStack input;
        public ItemStack output;
        public Recipe(int ticks,ItemStack input,ItemStack output){
            this.ticks=ticks;
            this.input=input;
            this.output=output;
        }
    }
}
