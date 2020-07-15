package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.block.*;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.maximum.abouttea.AboutTea.MODID;

@Mod.EventBusSubscriber
public class ModBlock {
    private static final DeferredRegister<Block> BLOCKS=new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> BLOCK_ITEMS=new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static RegistryObject<BlockTeaSet> blockTeaSet=registryBlock("teaset",BlockTeaSet::new);
    public static RegistryObject<BlockTeaTreeLeave> blockTeaTreeLeave=registryBlock("teatreeleave",()->new BlockTeaTreeLeave(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f),"greentea"));
    public static RegistryObject<BlockTeaWood> blockTeaWood=registryBlock("teawood",BlockTeaWood::new);
    public static RegistryObject<BlockTeaStoneOre> blockTeaStoneOre=registryBlock("teastone_ore",BlockTeaStoneOre::new);
    public static RegistryObject<BlockTeaGenerator> blockTeaGenerator=registryBlock("tea_generator",BlockTeaGenerator::new);
    public static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block){

        RegistryObject<T> registryObject=BLOCKS.register(name,block);
        BLOCK_ITEMS.register(name,()->new BlockItem(registryObject.get(),new Item.Properties().group(ModGroup.GROUP)));
        return registryObject;
    }
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
