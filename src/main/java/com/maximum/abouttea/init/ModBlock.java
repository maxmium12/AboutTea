package com.maximum.abouttea.init;

import com.maximum.abouttea.block.BlockTeaSet;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maximum.abouttea.AboutTea.MODID;

@Mod.EventBusSubscriber
public class ModBlock {
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    public static BlockTeaSet blockTeaSet;
    public static final RegistryObject<TileEntityType<TileTeaSet>> TEA_SET_TILE = TILES.register("teaset",()->TileEntityType.Builder.create(TileTeaSet::new,blockTeaSet).build(null));

    public static void onBlockRegistry(RegistryEvent.Register<Block> event){
        event.getRegistry().register(blockTeaSet=new BlockTeaSet());
    }
    public static void registryTile(){

    }
}
