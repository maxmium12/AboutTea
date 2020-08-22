package com.maximum.abouttea.datagen;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AboutTea.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlock.blockTeaTreeLeave.get(), models().getExistingFile(new ResourceLocation("block/birch_leaves")));
        simpleBlock(ModBlock.blockTeaStone.get());
        simpleBlock(ModBlock.blockTeaStoneOre.get());
        axisBlock(ModBlock.blockTeaWood.get());
    }
}
