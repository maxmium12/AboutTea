package com.maximum.abouttea.network;

import com.maximum.abouttea.AboutTea;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE= NetworkRegistry.newSimpleChannel(new ResourceLocation(AboutTea.MODID),()->"1.0",(s)->s.equals("1.0"),(s)->s.equals("1.0"));
    private int ID=0;
    public int nextID(){
        return ID++;
    }
    public  void registryMessage(){
        INSTANCE.registerMessage(nextID(),MsgCapabilitySync.class,MsgCapabilitySync::toByte,MsgCapabilitySync::new,MsgCapabilitySync::handle);
    }
}
