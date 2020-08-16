package com.maximum.abouttea.event;

import com.google.common.collect.ImmutableList;
import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.capabilities.AboutTeaCap;
import com.maximum.abouttea.capabilities.CapabilityHandler;
import com.maximum.abouttea.capabilities.IAboutTeaCap;
import com.maximum.abouttea.client.render.RenderTeaSet;
import com.maximum.abouttea.init.*;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.item.ItemTechBook;
import com.maximum.abouttea.network.MsgCapabilitySync;
import com.maximum.abouttea.network.NetworkHandler;
import com.maximum.abouttea.world.FeatureConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

@Mod.EventBusSubscriber()
public class EventHandler {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void onOreGen(FMLCommonSetupEvent event) {
            for (Biome biome : ForgeRegistries.BIOMES) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlock.blockTeaStoneOre.get().getDefaultState(), 2))
                        .withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(3, 16, 3))));
            }
        }

        @SubscribeEvent
        public static void onTreeGen(FMLCommonSetupEvent event) {
            for (Biome biome : ForgeRegistries.BIOMES) {
                if (biome == Biomes.JUNGLE) {
                    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(ModFeature.TEA_TREE_GEN.get().withConfiguration(FeatureConfig.TEA_TREE_CONFIG).withChance(0.05f)),Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.field_230129_h_))));
                }
            }
        }
        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            new CapabilityHandler();
            new NetworkHandler().registryMessage();
        }
    }
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event){
        if(event.getName().equals(LootTables.CHESTS_ABANDONED_MINESHAFT)
                || event.getName().equals(LootTables.CHESTS_BURIED_TREASURE)
                || event.getName().equals(LootTables.CHESTS_DESERT_PYRAMID)
                || event.getName().equals(LootTables.CHESTS_END_CITY_TREASURE)
                || event.getName().equals(LootTables.CHESTS_IGLOO_CHEST)
                || event.getName().equals(LootTables.CHESTS_JUNGLE_TEMPLE)
                || event.getName().equals(LootTables.CHESTS_SHIPWRECK_TREASURE)
                || event.getName().equals(LootTables.CHESTS_SIMPLE_DUNGEON)){
            LootEntry.Builder<?> entry = ItemLootEntry.builder(ModItems.itemTechBook.get()).weight(20);
            LootPool pool = LootPool.builder().addEntry(entry).name("tech").rolls(ConstantRange.of(1)).acceptCondition(SurvivesExplosion.builder()).build();
            event.getTable().addPool(pool);
        }
    }

    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event){
       // AboutTea.LOGGER.info("attach capability");
        if(event.getObject() instanceof PlayerEntity)
            event.addCapability(new ResourceLocation(AboutTea.MODID, "aboutteacap"),new AboutTeaCap.ProviderPlayer());
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        LazyOptional<IAboutTeaCap> oldCap = event.getOriginal().getCapability(CapabilityHandler.ABOUTTEACAP);
        LazyOptional<IAboutTeaCap> newCap = event.getPlayer().getCapability(CapabilityHandler.ABOUTTEACAP);
        if(oldCap.isPresent() && newCap.isPresent()){
            newCap.ifPresent(n -> {
                oldCap.ifPresent(o -> {
                    n.deserializeNBT(o.serializeNBT());
                });
            });
        }
    }
    // @SubscribeEvent
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
