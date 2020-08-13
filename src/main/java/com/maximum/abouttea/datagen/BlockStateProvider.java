package com.maximum.abouttea.datagen;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModBlock;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.maximum.abouttea.AboutTea.prefix;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AboutTea.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlock.blockTeaTreeLeave.get(),models().getExistingFile(new ResourceLocation("block/birch_leaves")));
        simpleBlock(ModBlock.blockTeaBlock.get());
        axisBlock(ModBlock.blockTeaWood.get());
    }
}
