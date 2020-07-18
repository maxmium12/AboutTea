package com.maximum.abouttea.event;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.capabilities.AboutTeaCap;
import com.maximum.abouttea.capabilities.CapabilityHandler;
import com.maximum.abouttea.capabilities.IAboutTeaCap;
import com.maximum.abouttea.client.render.RenderTeaSet;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.network.MsgCapabilitySync;
import com.maximum.abouttea.network.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

@Mod.EventBusSubscriber(modid = AboutTea.MODID)
public class EventHandler {
    @SubscribeEvent
    public static void onOreGen(FMLCommonSetupEvent event){
        for(Biome biome: ForgeRegistries.BIOMES){
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlock.blockTeaStoneOre.get().getDefaultState(),2))
                    .withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(3,16,3))));
        }
    }
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event){
        ClientRegistry.bindTileEntityRenderer(ModTiles.TEA_SET_TILE.get(), RenderTeaSet::new);
    }
    @SubscribeEvent
    public static void onItemColorInit(ColorHandlerEvent.Item event){
        event.getItemColors().register(((p_getColor_1_, p_getColor_2_) -> {
                if(p_getColor_1_.getItem() instanceof ItemTea){
                    return ((ItemTea) p_getColor_1_.getItem()).getColor();
                }return 0;
            }));
    }
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<PlayerEntity> event){
        event.addCapability(new ResourceLocation(AboutTea.MODID, "aboutteacap"),new AboutTeaCap.ProviderPlayer());
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        Capability<IAboutTeaCap> capability=CapabilityHandler.ABOUTTEACAP;
        Capability.IStorage<IAboutTeaCap> storage=CapabilityHandler.ABOUTTEACAP.getStorage();
        event.getOriginal().getCapability(capability).ifPresent(iAboutTeaCap -> {
            INBT nbt = storage.writeNBT(capability,iAboutTeaCap,null);
            storage.readNBT(capability,iAboutTeaCap,null,nbt);
        });
    }
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event){
        if(!event.getWorld().isRemote&&event.getEntity() instanceof PlayerEntity){
            ServerPlayerEntity player= (ServerPlayerEntity) event.getEntity();
            player.getCapability(CapabilityHandler.ABOUTTEACAP).ifPresent(iAboutTeaCap -> {
                MsgCapabilitySync msg=new MsgCapabilitySync(new CompoundNBT());
                Capability.IStorage<IAboutTeaCap> storage=CapabilityHandler.ABOUTTEACAP.getStorage();
                Capability<IAboutTeaCap> capability=CapabilityHandler.ABOUTTEACAP;
                msg.cap.put("aboutteacap",storage.writeNBT(capability,iAboutTeaCap,null));
                NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(()->player),msg);
            });
        }
    }
}
