package com.maximum.abouttea.client;

import com.maximum.abouttea.client.render.RenderTeaSet;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileTeaSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber
public class ClientProxy {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        ClientRegistry.bindTileEntityRenderer(ModTiles.TEA_SET_TILE.get(),(tileEntityRendererDispatcher -> new RenderTeaSet(tileEntityRendererDispatcher)));
    }
}
