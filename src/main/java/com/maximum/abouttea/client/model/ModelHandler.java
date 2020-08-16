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
        ModelLoader.addSpecialModel(new ResourceLocation(AboutTea.MODID,"item/tea1"));
        ModelLoader.addSpecialModel(new ResourceLocation(AboutTea.MODID,"item/tea2"));
        ModelLoader.addSpecialModel(new ResourceLocation(AboutTea.MODID,"item/tea3"));
    }
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event){
        AboutTea.LOGGER.info("replace models");
        ResourceLocation tealocation1=new ResourceLocation(AboutTea.MODID,"item/tea1");
        IBakedModel teamodel1=event.getModelRegistry().get(tealocation1);
        ResourceLocation tealocation2=new ResourceLocation(AboutTea.MODID,"item/tea2");
        IBakedModel teamodel2=event.getModelRegistry().get(tealocation1);
        ResourceLocation tealocation3=new ResourceLocation(AboutTea.MODID,"item/tea3");
        IBakedModel teamodel3=event.getModelRegistry().get(tealocation1);
        ModTea.getTeas().forEach(((s, itemTea) -> {
            switch (itemTea.getTier()){
                case 1:
                    event.getModelRegistry().put(new ModelResourceLocation(itemTea.getRegistryName(),"inventory"),teamodel1);
                    break;
                case 2:
                    event.getModelRegistry().put(new ModelResourceLocation(itemTea.getRegistryName(),"inventory"),teamodel2);
                    break;
                case 3:
                    event.getModelRegistry().put(new ModelResourceLocation(itemTea.getRegistryName(),"inventory"),teamodel3);
            }
        }));
    }
}
