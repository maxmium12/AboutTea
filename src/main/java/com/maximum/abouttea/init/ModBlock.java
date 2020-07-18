package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.block.*;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.util.RegistryUtil;
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
    public static RegistryObject<BlockTeaSet> blockTeaSet= RegistryUtil.registryBlockWithItem("teaset",BlockTeaSet::new);
    public static RegistryObject<BlockTeaTreeLeave> blockTeaTreeLeave=RegistryUtil.registryBlockWithItem("teatreeleave",()->new BlockTeaTreeLeave(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f),"greentea"));
    public static RegistryObject<BlockTeaWood> blockTeaWood=RegistryUtil.registryBlockWithItem("teawood",BlockTeaWood::new);
    public static RegistryObject<BlockTeaStoneOre> blockTeaStoneOre=RegistryUtil.registryBlockWithItem("teastone_ore",BlockTeaStoneOre::new);
    public static RegistryObject<BlockTeaGenerator> blockTeaGenerator=RegistryUtil.registryBlockWithItem("tea_generator",BlockTeaGenerator::new);
    public static RegistryObject<BlockTeaBookConverter> blockTeaBookConverter=RegistryUtil.registryBlockWithItem("tea_book_converter",BlockTeaBookConverter::new);
    public static RegistryObject<BlockTeaBlock> blockTeaBlock=RegistryUtil.registryBlockWithItem("tea_block",()->new BlockTeaBlock("green_tea"));
    public static RegistryObject<BlockTest> blockTest=RegistryUtil.registryBlockWithItem("test",BlockTest::new);
}
