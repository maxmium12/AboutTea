package com.maximum.abouttea.datagen.loottable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LootTableProvider extends net.minecraft.data.LootTableProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Map<Block, Function<Block, LootTable.Builder>> functionTable = new HashMap<>();

    public LootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        functionTable.put(ModBlock.blockTeaWood.get(), this::genNormal);
        functionTable.put(ModBlock.blockTeaStoneOre.get(), this::genTeaStoneOre);
    }

    @Override
    public void act(DirectoryCache cache) {
        Map<ResourceLocation, LootTable.Builder> tables = new HashMap<>();

        for (Block b : ForgeRegistries.BLOCKS) {
            ResourceLocation id = ForgeRegistries.BLOCKS.getKey(b);
            if (!AboutTea.MODID.equals(id.getNamespace())) {
                continue;
            }
            Function<Block, LootTable.Builder> func = functionTable.getOrDefault(b, this::genNormal);
            tables.put(id, func.apply(b));
        }

        for (Map.Entry<ResourceLocation, LootTable.Builder> e : tables.entrySet()) {
            Path path = getPath(this.dataGenerator.getOutputFolder(), e.getKey());
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(e.getValue().setParameterSet(LootParameterSets.BLOCK).build()), path);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private LootTable.Builder genNormal(Block b) {
        LootEntry.Builder<?> entry = ItemLootEntry.builder(b);
        LootPool.Builder pool = LootPool.builder().name("main").rolls(ConstantRange.of(1)).addEntry(entry).acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(pool);
    }

    private LootTable.Builder genTeaStoneOre(Block b) {
        LootEntry.Builder<?> entry = ItemLootEntry.builder(b);
        LootPool.Builder pool = LootPool.builder().name("main").rolls(ConstantRange.of(1)).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)).addEntry(entry).acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(pool);
    }
}
