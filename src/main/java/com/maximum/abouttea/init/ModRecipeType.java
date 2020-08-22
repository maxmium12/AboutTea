package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.impl.DryerRecipes;
import com.maximum.abouttea.impl.MixerRecipes;
import com.maximum.abouttea.impl.TeaCupRecipes;
import com.maximum.abouttea.impl.TeaStoneWorkStationRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maximum.abouttea.AboutTea.prefix;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipeType {
    public static IRecipeType<DryerRecipes> DRYER_RECIPE = new RecipeType<>();
    public static IRecipeSerializer<DryerRecipes> DRYER_SERIALIZER = new DryerRecipes.Serializer();
    public static IRecipeType<MixerRecipes> MIXER_RECIPE = new RecipeType<>();
    public static IRecipeSerializer<MixerRecipes> MIXER_SERIALIZER = new MixerRecipes.Serializer();
    public static IRecipeType<TeaStoneWorkStationRecipe> TEA_STONE_WORKSTATION_RECIPE = new RecipeType<>();
    public static IRecipeSerializer<TeaStoneWorkStationRecipe> TEA_STONE_SERIALIZER = new TeaStoneWorkStationRecipe.Serializer();
    public static SpecialRecipeSerializer<TeaCupRecipes> TEA_CUP_SERIALIZER = new SpecialRecipeSerializer<>(TeaCupRecipes::new);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        AboutTea.LOGGER.info("register recipe types");
        ResourceLocation id = prefix("dryer");
        Registry.register(Registry.RECIPE_TYPE, id, DRYER_RECIPE);
        event.getRegistry().register(DRYER_SERIALIZER.setRegistryName(id));
        id = prefix("mixer");
        Registry.register(Registry.RECIPE_TYPE, id, MIXER_RECIPE);
        event.getRegistry().register(MIXER_SERIALIZER.setRegistryName(id));
        id = prefix("teastone_workstation");
        Registry.register(Registry.RECIPE_TYPE, id, TEA_STONE_WORKSTATION_RECIPE);
        event.getRegistry().register(TEA_STONE_SERIALIZER.setRegistryName(id));
        id = prefix("tea_cup");
        event.getRegistry().register(TEA_CUP_SERIALIZER.setRegistryName(id));
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}
