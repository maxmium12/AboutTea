package com.maximum.abouttea.init;


import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.item.ItemTeaCup;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS=new DeferredRegister<>(ForgeRegistries.ITEMS, AboutTea.MODID);
    public static final RegistryObject<ItemTeaCup> itemTeaCup=ITEMS.register("teacup", ItemTeaCup::new);
    public static final RegistryObject<ItemTea> itemGreenTea=ITEMS.register("greentea",()->new ItemTea(0x31cbb));
    public static void register(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
