package com.maximum.abouttea.compat.jei;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@JeiPlugin
public class JEIAboutTea implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(AboutTea.MODID,"main");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DryerCategory(registration.getJeiHelpers().getGuiHelper()));
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(Minecraft.getInstance().world.getRecipeManager().getRecipes(ModRecipeType.DRYER_RECIPE).values(),DryerCategory.UID);
    }
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlock.blockManualTeaDryer.get()),DryerCategory.UID);
    }
}
