package com.maximum.abouttea.datagen;

import com.maximum.abouttea.AboutTea;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.maximum.abouttea.AboutTea.prefix;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, AboutTea.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
       for(Item item: ForgeRegistries.ITEMS){
           ResourceLocation registryName=item.getRegistryName();
           if(registryName.getNamespace().equals(AboutTea.MODID)){
               if(item instanceof BlockItem){
                   getBuilder(item.getRegistryName().getPath())
                           .parent(getExistingFile(prefix("block/"+registryName.getPath())));
               }
           }
       }
    }

    @Override
    public String getName() {
        return AboutTea.MODID+"Item Model Provider";
    }
}
