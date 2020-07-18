package com.maximum.abouttea.init;


import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS=new DeferredRegister<>(ForgeRegistries.ITEMS, AboutTea.MODID);
    public static final RegistryObject<ItemTeaCup> itemTeaCup=ITEMS.register("teacup", ItemTeaCup::new);
    public static final RegistryObject<ItemTeaStone> itemTeaStone=ITEMS.register("teastone",ItemTeaStone::new);
    public static final RegistryObject<ItemTeaBook> itemTeaBook=ITEMS.register("teabook",ItemTeaBook::new);
    public static final RegistryObject<ItemTechBook> itemTechBook=ITEMS.register("tech_book",ItemTechBook::new);
    public static void register(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
