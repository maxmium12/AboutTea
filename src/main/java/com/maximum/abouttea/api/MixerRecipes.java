package com.maximum.abouttea.api;

import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class MixerRecipes {
    private static final LinkedList<DryerRecipes.Recipe> recipes=new LinkedList<>();
    public static DryerRecipes.Recipe findRecipe(ItemStack input){
        for(DryerRecipes.Recipe recipe:recipes){
            if(ItemStack.areItemStacksEqual(input,recipe.input)){
                return recipe;
            }
        }
        return null;
    }
    public static void registry(DryerRecipes.Recipe recipe){
        recipes.add(recipe);
    }
    public static class Recipe{
        public int ticks;
        public ItemStack[] inputs=new ItemStack[]{ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY};
        public ItemStack output;
        public Recipe(int ticks,ItemStack[] inputs,ItemStack output){
            this.ticks=ticks;
            if(inputs.length>4){
               throw new IllegalArgumentException("inputs length should smaller than 4!!!");
            }
            for(int i=0;i<inputs.length;i++){
                this.inputs[i]=inputs[i];
            }
            this.output=output;
        }
    }
}
