package com.maximum.abouttea.client.model;

import com.maximum.abouttea.AboutTea;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModelHandler {
    private static final ResourceLocation CUP=new ResourceLocation(AboutTea.MODID,"models/item/cup.obj");
    @SubscribeEvent
    public static void onModelBake(ModelRegistryEvent event){
        OBJModel model=OBJLoader.INSTANCE.loadModel(new OBJModel.ModelSettings(CUP,true,false,false,true,null));
    }
}
