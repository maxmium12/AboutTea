package com.maximum.abouttea.init;

import com.maximum.abouttea.block.BlockTeaSet;
import com.maximum.abouttea.tile.TileBookConverter;
import com.maximum.abouttea.tile.TileTeaGenerator;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maximum.abouttea.AboutTea.MODID;

public class ModTiles {
    public static final RegistryObject<TileEntityType<TileTeaSet>> TEA_SET_TILE = RegistryUtil.registryTile("tea_set",()->TileEntityType.Builder.create(TileTeaSet::new,ModBlock.blockTeaSet.get()).build(null));
    //public static final RegistryObject<TileEntityType<>>
    public static final RegistryObject<TileEntityType<TileTeaGenerator>> TEA_GENERATOR_TILE = RegistryUtil.registryTile("tea_generator",()->TileEntityType.Builder.create(TileTeaGenerator::new,ModBlock.blockTeaGenerator.get()).build(null));
    public static final RegistryObject<TileEntityType<TileBookConverter>> TEA_BOOK_CONVERTER_TILE = RegistryUtil.registryTile("tea_book_converter",()->TileEntityType.Builder.create(TileBookConverter::new,ModBlock.blockTeaBookConverter.get()).build(null));
    public static final RegistryObject<TileEntityType<TileTeaDryer>> MANUAL_TEA_DRYER_TILE = RegistryUtil.registryTile("manual_tea_dryer",()->TileEntityType.Builder.create(TileTeaDryer::new,ModBlock.blockManualTeaDryer.get()).build(null));
}
