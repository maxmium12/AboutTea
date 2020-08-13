package com.maximum.abouttea.init;

import com.maximum.abouttea.util.UnlockTechTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTrigger {
    public static UnlockTechTrigger UnlockTechTrigger;
    @SubscribeEvent
    public static void onRegistry(FMLCommonSetupEvent event){
        CriteriaTriggers.register(UnlockTechTrigger = new UnlockTechTrigger());
    }
}
