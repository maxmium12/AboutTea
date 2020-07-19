package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.gui.ContainerTeaSet;
import com.maximum.abouttea.util.RegistryUtil;
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
    public static final RegistryObject<ContainerType<ContainerTeaSet>> containerteaset= RegistryUtil.registryContainer("teaset_container",
            ()->IForgeContainerType.create((int windowid,PlayerInventory inv,PacketBuffer buffer)->new ContainerTeaSet(windowid,inv,buffer.readBlockPos(), Minecraft.getInstance().world)));
}
