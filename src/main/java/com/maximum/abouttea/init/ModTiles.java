package com.maximum.abouttea.init;

import com.maximum.abouttea.block.BlockTeaSet;
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
    public static void register(){
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
