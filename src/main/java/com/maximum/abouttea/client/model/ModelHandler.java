package com.maximum.abouttea.client.model;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModTea;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelHandler {
    private static final ResourceLocation CUP=new ResourceLocation(AboutTea.MODID,"models/item/cup.obj");
    @SubscribeEvent
    public static void onRegistryModel(ModelRegistryEvent event){
        AboutTea.LOGGER.info("load tea model");
        ModelLoader.addSpecialModel(new ResourceLocation(AboutTea.MODID,"item/tea"));
    }
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event){
        AboutTea.LOGGER.info("replace models");
        ResourceLocation tealocation=new ResourceLocation(AboutTea.MODID,"item/tea");
        IBakedModel teamodel=event.getModelRegistry().get(tealocation);
        ModTea.getTeas().forEach(((s, itemTea) -> {
            event.getModelRegistry().put(new ModelResourceLocation(itemTea.getRegistryName(),"inventory"),teamodel);
        }));
    }
}
