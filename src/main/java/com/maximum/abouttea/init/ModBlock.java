package com.maximum.abouttea.init;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.block.BlockTeaSet;
import com.maximum.abouttea.block.BlockTeaStoneOre;
import com.maximum.abouttea.block.BlockTeaTreeLeave;
import com.maximum.abouttea.block.BlockTeaWood;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maximum.abouttea.AboutTea.MODID;

@Mod.EventBusSubscriber
public class ModBlock {
    private static final DeferredRegister<Block> BLOCKS=new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    public static RegistryObject<BlockTeaSet> blockTeaSet=BLOCKS.register("teaset",BlockTeaSet::new);
    public static RegistryObject<BlockTeaTreeLeave> blockTeaTreeLeave=BLOCKS.register("teatreeleave",()->new BlockTeaTreeLeave(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f),ModItems.itemGreenTea.get()));
    public static RegistryObject<BlockTeaWood> blockTeaWood=BLOCKS.register("teawood",BlockTeaWood::new);
    public static RegistryObject<BlockTeaStoneOre> blockTeaStoneOre=BLOCKS.register("teastoneore",BlockTeaStoneOre::new);
    public static void register(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
