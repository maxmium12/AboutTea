package com.maximum.abouttea.datagen;

import com.maximum.abouttea.datagen.loottable.LootTableProvider;
import com.maximum.abouttea.datagen.recipe.DryerRecipeProvider;
import com.maximum.abouttea.datagen.recipe.MixerRecipeProvider;
import com.maximum.abouttea.datagen.recipe.ModRecipeProvider;
import com.maximum.abouttea.datagen.recipe.TeaStoneWorkstationRecipeProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {
    @SubscribeEvent
    public static void onGen(GatherDataEvent event) {
        if (event.includeServer()) {
            event.getGenerator().addProvider(new ModRecipeProvider(event.getGenerator()));
            event.getGenerator().addProvider(new MixerRecipeProvider(event.getGenerator()));
            event.getGenerator().addProvider(new DryerRecipeProvider(event.getGenerator()));
            event.getGenerator().addProvider(new TeaStoneWorkstationRecipeProvider(event.getGenerator()));
            event.getGenerator().addProvider(new BlockStateProvider(event.getGenerator(), event.getExistingFileHelper()));
            event.getGenerator().addProvider(new LootTableProvider(event.getGenerator()));
            event.getGenerator().addProvider(new ItemModelProvider(event.getGenerator(), event.getExistingFileHelper()));
        }
    }
}
