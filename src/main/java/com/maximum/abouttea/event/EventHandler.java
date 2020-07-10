package com.maximum.abouttea.event;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModBlock;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AboutTea.MODID)
public class EventHandler {
    public static void onOreGen(FMLCommonSetupEvent event){
        for(Biome biome: ForgeRegistries.BIOMES){
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlock.blockTeaStoneOre.get().getDefaultState(),2))
                    .withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(3,16,3))));
        }
    }
}
