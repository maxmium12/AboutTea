package com.maximum.abouttea.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveOutputRecipe;
import com.google.common.collect.Lists;
import com.maximum.abouttea.impl.MixerRecipes;
import com.maximum.abouttea.init.ModRecipeType;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.maxseth.abouttea.mixerrecipe")
public class MixerRecipeTweak implements IRecipeManager {
    @ZenCodeType.Method
    public void addMixRecipe(IIngredient[] input, IItemStack output, int time) {
        List<IIngredient> in = Lists.newArrayList(input);
        in.forEach(IIngredient::asVanillaIngredient);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, new MixerRecipes(output.getInternal(), time, in.toArray(new Ingredient[0])), "mixer_recipe"));
    }

    @ZenCodeType.Method
    public void removeMixRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new ActionRemoveOutputRecipe(this, output));
    }

    @Override
    public IRecipeType getRecipeType() {
        return ModRecipeType.DRYER_RECIPE;
    }
}
