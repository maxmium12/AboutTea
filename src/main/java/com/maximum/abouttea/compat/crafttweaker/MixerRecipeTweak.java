package com.maximum.abouttea.compat.crafttweaker;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.maximum.abouttea.api.recipes.impl.MixerRecipes;
import net.minecraft.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

public class MixerRecipeTweak {
    @ZenCodeType.Method
    public static void addMixRecipe(IItemStack[] inputs, IItemStack output, int time){
        ItemStack[] stacks=new ItemStack[4];
        for(int i=0;i<stacks.length;i++){
            stacks[i]=inputs[i].getInternal();
        }
        MixerRecipes.registry(new MixerRecipes.Recipe(time,stacks,output.getInternal()));
    }
    @ZenCodeType.Method
    public static void removeMixRecipe(IItemStack output){
        MixerRecipes.removeRecipe(output.getInternal());
    }
}
