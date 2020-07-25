package com.maximum.abouttea.compat.jei;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.api.recipes.impl.DryerRecipes;
import com.maximum.abouttea.init.ModBlock;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DryerCategory implements IRecipeCategory<IDryerRecipe> {
    private final IDrawable background;
    private final ItemStack renderStack=new ItemStack(ModBlock.blockManualTeaDryer.get());
    private final IDrawable icon;
    public static final ResourceLocation UID=new ResourceLocation(AboutTea.MODID,"dryer");
    public DryerCategory(IGuiHelper helper){
        background=helper.createBlankDrawable(168,64);
        icon=helper.createDrawableIngredient(renderStack);
    }
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends IDryerRecipe> getRecipeClass() {
        return DryerRecipes.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("abouttea.jei.dryer");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    public void draw(IDryerRecipe recipe, double mouseX, double mouseY) {
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.pushMatrix();
        Minecraft.getInstance().fontRenderer.drawString(I18n.format("abouttea.jei.dryer.ticks"),18,16,0xffffff);
        RenderSystem.disableBlend();
        RenderSystem.disableAlphaTest();
    }
    @Override
    public void setIngredients(IDryerRecipe recipe, IIngredients iIngredients) {
        iIngredients.setInput(VanillaTypes.ITEM,recipe.getInput().getMatchingStacks()[0]);
        iIngredients.setOutput(VanillaTypes.ITEM,recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IDryerRecipe recipe, IIngredients iIngredients) {
        int index=0;

    }
}
