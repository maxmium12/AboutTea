package com.maximum.abouttea.client;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.client.gui.GuiMixer;
import com.maximum.abouttea.client.render.LayerCustomArmor;
import com.maximum.abouttea.client.render.RenderManualTeaDryer;
import com.maximum.abouttea.client.render.RenderTeaSet;
import com.maximum.abouttea.gui.ContainerMixer;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.tile.TileTeaSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = AboutTea.MODID,bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        AboutTea.LOGGER.info("bind special render");
        ClientRegistry.bindTileEntityRenderer(ModTiles.TEA_SET_TILE.get(),RenderTeaSet::new);
        ClientRegistry.bindTileEntityRenderer(ModTiles.MANUAL_TEA_DRYER_TILE.get(), RenderManualTeaDryer::new);
        //添加RenderLayer
        AboutTea.LOGGER.info("Add render layer");
        RenderTypeLookup.setRenderLayer(ModBlock.blockTeaStone.get(), RenderType.getTranslucent());
        //添加Gui
        ScreenManager.registerFactory(ModContainer.MIXER_CONTAINER.get(), GuiMixer::new);
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
    @SubscribeEvent
    public static void loadComplete(FMLLoadCompleteEvent evt) {
        EntityRendererManager manager=Minecraft.getInstance().getRenderManager();
        for(PlayerRenderer renderer:manager.getSkinMap().values()){
            addCustomArmorLayer(renderer);
        }
        AboutTea.LOGGER.info("loaded custom layer");
    }
    //来自Mekanism
    private static <T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> void addCustomArmorLayer(LivingRenderer<T, M> renderer) {
        for (LayerRenderer<T, M> layerRenderer : new ArrayList<>(renderer.layerRenderers)) {
            //Only allow an exact match, so we don't add to modded entities that only have a modded extended armor layer
            if (layerRenderer.getClass() == BipedArmorLayer.class) {
                BipedArmorLayer<T, M, A> bipedArmorLayer = (BipedArmorLayer<T, M, A>) layerRenderer;
                renderer.addLayer(new LayerCustomArmor<>(renderer, bipedArmorLayer.modelLeggings, bipedArmorLayer.modelArmor));
                break;
            }
        }
    }
}
