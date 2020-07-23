package com.maximum.abouttea.client;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.client.render.RenderTeaSet;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.tile.TileTeaSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AboutTea.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        ClientRegistry.bindTileEntityRenderer(ModTiles.TEA_SET_TILE.get(),(RenderTeaSet::new));
        AboutTea.LOGGER.info("bind special render");
    }
    @SubscribeEvent
    public static void onItemColorInit(ColorHandlerEvent.Item event){
        ModTea.getTeas().forEach(((s, itemTea) -> {
            event.getItemColors().register(((p_getColor_1_, p_getColor_2_) -> itemTea.getColor()),itemTea);
        }));
    }
    @SubscribeEvent
    public static void onBlockColorInit(ColorHandlerEvent.Block event){
        event.getBlockColors().register(((p_getColor_1_, p_getColor_2_, p_getColor_3_, p_getColor_4_) -> 0x00aa70), ModBlock.blockTeaTreeLeave.get());
    }
}
