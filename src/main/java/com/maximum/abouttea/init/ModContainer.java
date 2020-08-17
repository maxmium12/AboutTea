package com.maximum.abouttea.init;

import com.maximum.abouttea.gui.*;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainer {
    public static final RegistryObject<ContainerType<ContainerTeaSet>> containerteaset = RegistryUtil.registryContainer("tea_set_container",
            () -> IForgeContainerType.create((windowid, inv, buffer) -> new ContainerTeaSet(windowid, inv, buffer.readBlockPos(), inv.player.world)));
    public static final RegistryObject<ContainerType<ContainerMixer>> MIXER_CONTAINER = RegistryUtil.registryContainer("mixer_container",
            () -> IForgeContainerType.create((ContainerMixer::new)));
    public static final RegistryObject<ContainerType<ContainerTeaStoneWorkStation>> TEA_STONE_WORKSTATION = RegistryUtil.registryContainer("tea_stone_workstation",
            () -> IForgeContainerType.create(ContainerTeaStoneWorkStation::new));
    public static final RegistryObject<ContainerType<ContainerMachineDryer>> MACHINE_DRYER = RegistryUtil.registryContainer("machine_dryer",
            () -> IForgeContainerType.create(ContainerMachineDryer::new));
    public static final RegistryObject<ContainerType<ContainerTeaGenerator>> TEA_GENERATOR = RegistryUtil.registryContainer("tea_generator",
            () -> IForgeContainerType.create(ContainerTeaGenerator::new));
}
