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
    public static final RegistryObject<ItemTeaCup> itemTeaCup= RegistryUtil.registryItem("tea_cup", ItemTeaCup::new);
    public static final RegistryObject<ItemTeaStone> itemTeaStone=RegistryUtil.registryItem("tea_stone",ItemTeaStone::new);
    public static final RegistryObject<ItemTeaBook> itemTeaBook=RegistryUtil.registryItem("tea_book",ItemTeaBook::new);
    public static final RegistryObject<ItemTechBook> itemTechBook=RegistryUtil.registryItem("tech_book",ItemTechBook::new);
    public static final RegistryObject<ItemTeaMaterial> itemDryGreenTea=RegistryUtil.registryItem("dry_green_tea",()->new ItemTeaMaterial(new Item.Properties()));
}
