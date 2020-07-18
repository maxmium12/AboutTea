package com.maximum.abouttea.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.item.ItemTea;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;

@Mod.EventBusSubscriber
public class TeaVillagerProfession {
    static VillagerProfession profession;
    public static void onVillagerRegistry(RegistryEvent.Register<VillagerProfession> event){
        profession=new VillagerProfession("teatrader",PointOfInterestType.HOME,ImmutableSet.of(),ImmutableSet.of(),null);
    }
    public static void onVillagerTradeRegistry(VillagerTradesEvent event){
        if(event.getType().equals(profession)){
            event.getTrades().get(1).add(new TeastoneTrade());
        }
    }
    static class TeastoneTrade implements VillagerTrades.ITrade {
        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            MerchantOffer merchantOffer;
            if(rand.nextInt(5)<=3){
            Collection<ItemTea> teas=ModTea.getTeas().values();
            ItemTea tea=Lists.newArrayList(teas).get(rand.nextInt(teas.size()));
            int teacount=rand.nextInt(3);
            int count=tea.getTier()*teacount+rand.nextInt(3);
            return new MerchantOffer(new ItemStack(ModItems.itemTeaStone.get(),count),new ItemStack(tea,teacount),rand.nextInt(20),1,0.05f);
            }
            return new MerchantOffer(new ItemStack(ModItems.itemTechBook.get()),new ItemStack(ModItems.itemTeaStone.get(),3),2,1,0.5f);
        }
    }
}
