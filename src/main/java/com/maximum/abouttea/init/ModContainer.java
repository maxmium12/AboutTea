package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.gui.ContainerTeaSet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainer {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS=new DeferredRegister<>(ForgeRegistries.CONTAINERS, AboutTea.MODID);
    public static final RegistryObject<ContainerType<ContainerTeaSet>> containerteaset=CONTAINERS.register("teaset_container",
            ()->IForgeContainerType.create((int windowid,PlayerInventory inv,PacketBuffer buffer)->new ContainerTeaSet(windowid,inv,buffer,buffer.readBlockPos(), Minecraft.getInstance().world)));
    public static void register(){
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
