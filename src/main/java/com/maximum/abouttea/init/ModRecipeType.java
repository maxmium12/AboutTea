package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.api.recipes.impl.DryerRecipes;
import com.maximum.abouttea.api.recipes.impl.MixerRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maximum.abouttea.AboutTea.prefix;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipeType {
    public static IRecipeType<DryerRecipes> DRYER_RECIPE=new RecipeType<>();
    public static IRecipeSerializer<DryerRecipes> DRYER_SERIALIZER=new DryerRecipes.Serializer();
    public static IRecipeType<MixerRecipes> MIXER_RECIPE=new RecipeType<>();
    public static IRecipeSerializer<MixerRecipes> MIXER_SERIALIZER=new MixerRecipes.Serializer();
    @SubscribeEvent
    public static void register(RegistryEvent.Register<IRecipeSerializer<?>> event){
        AboutTea.LOGGER.info("register recipe types");
        ResourceLocation id=prefix("dryer");
        Registry.register(Registry.RECIPE_TYPE,id,DRYER_RECIPE);
        event.getRegistry().register(DRYER_SERIALIZER.setRegistryName(id));
        id=prefix("mixer");
        Registry.register(Registry.RECIPE_TYPE,id,MIXER_RECIPE);
        event.getRegistry().register(MIXER_SERIALIZER.setRegistryName(id));
    }
    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T>{
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}
