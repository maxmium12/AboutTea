package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.gui.ContainerMixer;
import com.maximum.abouttea.gui.ContainerTeaSet;
import com.maximum.abouttea.gui.ContainerTeaStoneWorkStaion;
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
    public static final RegistryObject<ContainerType<ContainerTeaSet>> containerteaset = RegistryUtil.registryContainer("tea_set_container",
            () -> IForgeContainerType.create((windowid, inv, buffer) -> new ContainerTeaSet(windowid, inv, buffer.readBlockPos(), Minecraft.getInstance().world)));
    public static final RegistryObject<ContainerType<ContainerMixer>> MIXER_CONTAINER = RegistryUtil.registryContainer("mixer_container",
            () -> IForgeContainerType.create((ContainerMixer::new)));
    public static final RegistryObject<ContainerType<ContainerTeaStoneWorkStaion>> TEA_STONE_WORKSTATION = RegistryUtil.registryContainer("tea_stone_workstation",
            () -> IForgeContainerType.create(ContainerTeaStoneWorkStaion::new));
}