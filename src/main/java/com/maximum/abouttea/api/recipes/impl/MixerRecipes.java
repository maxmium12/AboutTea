package com.maximum.abouttea.api.recipes.impl;

import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class MixerRecipes {
    private static final LinkedList<MixerRecipes.Recipe> recipes=new LinkedList<>();
    public static MixerRecipes.Recipe findRecipe(ItemStack output){
        for(MixerRecipes.Recipe recipe:recipes){
            if(ItemStack.areItemStacksEqual(output,recipe.output)){
                return recipe;
            }
        }
        return null;
    }
    public static void registry(MixerRecipes.Recipe recipe){
        recipes.add(recipe);
    }
    public static void removeRecipe(ItemStack output){
        Recipe recipe=findRecipe(output);
        if(recipe!=null){
            recipes.remove(recipe);
        }
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
