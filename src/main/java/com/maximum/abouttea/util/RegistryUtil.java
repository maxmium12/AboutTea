package com.maximum.abouttea.util;

import com.maximum.abouttea.init.ModGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.maximum.abouttea.AboutTea.MODID;

public class RegistryUtil{
    private static final DeferredRegister<Block> BLOCKS=new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS=new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static <T extends Block> RegistryObject<T> registryBlockWithItem(String name, Supplier<T> block){
        RegistryObject<T> object=registryBlock(name,block);
        registryItem(name,()->new BlockItem(object.get(),new Item.Properties().group(ModGroup.GROUP)));
        return object;
        //TODO return registryBlock(name,block,()-> BLOCKS.register(name,block));
    }
    public static <T extends Block> RegistryObject<T> registryBlock(String name,Supplier<T> block){
        return BLOCKS.register(name,block);
    }
    public static <T extends Item> RegistryObject<T> registryItem(String name,Supplier<T> item){
        return ITEMS.register(name,item);
    }
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static class Wrapper <PRIMARY extends IForgeRegistryEntry<? super PRIMARY>, SECONDARY extends IForgeRegistryEntry<? super SECONDARY>>{

    }
}
