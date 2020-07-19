package com.maximum.abouttea.util;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModGroup;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.gen.feature.Feature;
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
    private static final DeferredRegister<Feature<?>> FEATURES=new DeferredRegister<>(ForgeRegistries.FEATURES, AboutTea.MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS=new DeferredRegister<>(ForgeRegistries.CONTAINERS, AboutTea.MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
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
    public static <T extends Feature<?>> RegistryObject<T> registryFeature(String name,Supplier<T> feature){
        return FEATURES.register(name,feature);
    }
    public static <T extends ContainerType<?>> RegistryObject<T> registryContainer(String name,Supplier<T> container){
        return CONTAINERS.register(name,container);
    }
    public static <T extends TileEntityType<?>> RegistryObject<T> registryTile(String name,Supplier<T> tile){
        return TILES.register(name,tile);
    }
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static class Wrapper <PRIMARY extends IForgeRegistryEntry<? super PRIMARY>, SECONDARY extends IForgeRegistryEntry<? super SECONDARY>>{

    }
}
