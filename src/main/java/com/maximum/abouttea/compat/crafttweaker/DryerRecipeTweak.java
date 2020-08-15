package com.maximum.abouttea.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveOutputRecipe;
import com.maximum.abouttea.impl.DryerRecipes;
import com.maximum.abouttea.init.ModRecipeType;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.maxseth.abouttea.dryrecipe")
public class DryerRecipeTweak implements IRecipeManager {
    @ZenCodeType.Method
    public  void addDryRecipe(IItemStack input,IItemStack output,int time){
        CraftTweakerAPI.apply(new ActionAddRecipe(this,new DryerRecipes(time,Ingredient.fromStacks(input.getInternal()),output.getInternal()),"dryer_recipe"));
    }
    @ZenCodeType.Method
    public  void removeDryRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveOutputRecipe(this,output));
    }

    @Override
    public IRecipeType getRecipeType() {
        return ModRecipeType.DRYER_RECIPE;
    }
}
