package com.maximum.abouttea.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.maximum.abouttea.AboutTea.MODID;

public class RegistryUtil{
    private static final DeferredRegister<Block> BLOCKS=new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> BLOCK_ITEMS=new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static <T extends Block,U extends Item> RegistryObject<T> registryBlock(String name, Supplier<T> block){
        //TODO return registryBlock(name,block,()-> BLOCKS.register(name,block));
    }
    public static <T extends Block,U>  RegistryObject<T> registryBlock(String name, Supplier<T> block, BiFunction<T,U,RegistryObject<Block>> objectWrapper){

    }
    public static class Wrapper <PRIMARY extends IForgeRegistryEntry<? super PRIMARY>, SECONDARY extends IForgeRegistryEntry<? super SECONDARY>>{

    }
}
