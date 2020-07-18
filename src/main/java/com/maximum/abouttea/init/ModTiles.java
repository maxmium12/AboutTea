package com.maximum.abouttea.init;

import com.maximum.abouttea.block.BlockTeaSet;
import com.maximum.abouttea.tile.TileBookConverter;
import com.maximum.abouttea.tile.TileTeaGenerator;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maximum.abouttea.AboutTea.MODID;

public class ModTiles {
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    public static final RegistryObject<TileEntityType<TileTeaSet>> TEA_SET_TILE = TILES.register("teaset",()->TileEntityType.Builder.create(TileTeaSet::new,ModBlock.blockTeaSet.get()).build(null));
    //public static final RegistryObject<TileEntityType<>>
    public static final RegistryObject<TileEntityType<TileTeaGenerator>> TEA_GENERATOR_TILE = TILES.register("tea_generator",()->TileEntityType.Builder.create(TileTeaGenerator::new,ModBlock.blockTeaGenerator.get()).build(null));
    public static final RegistryObject<TileEntityType<TileBookConverter>> TEA_BOOK_CONVERTER_TILE = TILES.register("tea_book_converter",()->TileEntityType.Builder.create(TileBookConverter::new,ModBlock.blockTeaBookConverter.get()).build(null));
    public static void register(){
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
