package com.maximum.abouttea.init;


import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.item.*;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final RegistryObject<ItemTeaCup> itemTeaCup= RegistryUtil.registryItem("teacup", ItemTeaCup::new);
    public static final RegistryObject<ItemTeaStone> itemTeaStone=RegistryUtil.registryItem("teastone",ItemTeaStone::new);
    public static final RegistryObject<ItemTeaBook> itemTeaBook=RegistryUtil.registryItem("teabook",ItemTeaBook::new);
    public static final RegistryObject<ItemTechBook> itemTechBook=RegistryUtil.registryItem("tech_book",ItemTechBook::new);
}
