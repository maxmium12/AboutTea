package com.maximum.abouttea.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveOutputRecipe;
import com.maximum.abouttea.impl.TeaStoneWorkStationRecipe;
import com.maximum.abouttea.init.ModRecipeType;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

public class TeaStoneWorkstationTweak implements IRecipeManager {
    @ZenCodeType.Method
    public void addTeaStoneWorkstationRecipe(IIngredient[][] inputs, IItemStack output, int tick){
        Ingredient[][] ingredients = new Ingredient[inputs.length][inputs[0].length];
        for(int i = 0;i < inputs.length; i++){
            for(int j = 0;j < inputs[0].length; j++){
                ingredients[i][j] = inputs[i][j].asVanillaIngredient();
            }
        }
        CraftTweakerAPI.apply(new ActionAddRecipe(this,new TeaStoneWorkStationRecipe(ingredients, output.getInternal(), tick),"tea_stone_workstation_recipe"));
    }
    @ZenCodeType.Method
    public  void removeTeaStoneWorkstationRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveOutputRecipe(this,output));
    }
    @Override
    public IRecipeType getRecipeType() {
        return ModRecipeType.TEA_STONE_WORKSTATION_RECIPE;
    }
}
