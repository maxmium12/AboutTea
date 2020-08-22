package com.maximum.abouttea.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTea;
import com.maximum.abouttea.item.ItemTea;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TeaVillagerProfession {
    public static VillagerProfession profession;
    public static PointOfInterestType point;

    @SubscribeEvent
    public static void onVillagerRegistry(RegistryEvent.Register<VillagerProfession> event) {
        profession = new VillagerProfession("tea_trader", point, ImmutableSet.of(), ImmutableSet.of(), null);
        event.getRegistry().register(profession.setRegistryName("tea_trader"));
    }

    @SubscribeEvent
    public static void onPointRegistry(RegistryEvent.Register<PointOfInterestType> event) {
        Set<BlockState> states = ImmutableSet.copyOf(ModBlock.blockManualTeaDryer.get().getStateContainer().getValidStates());
        point = new PointOfInterestType("tea", states, 1, 1);
        event.getRegistry().register(point.setRegistryName("tea_point"));
    }

    @SubscribeEvent
    public static void onVillagerTradeRegistry(VillagerTradesEvent event) {
        if (event.getType().equals(profession)) {
            event.getTrades().get(1).add(new TeastoneTrade());
        }
    }

    static class TeastoneTrade implements VillagerTrades.ITrade {
        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            MerchantOffer merchantOffer;
            if (rand.nextInt(5) <= 3) {
                Collection<ItemTea> teas = ModTea.getTeas().values();
                ItemTea tea = Lists.newArrayList(teas).get(rand.nextInt(teas.size()));
                int teacount = rand.nextInt(3);
                int count = tea.getTier() * teacount + rand.nextInt(3);
                return new MerchantOffer(new ItemStack(ModItems.itemTeaStone.get(), count), new ItemStack(tea, teacount), rand.nextInt(20), 1, 0.05f);
            }
            return new MerchantOffer(new ItemStack(ModItems.itemTechBook.get()), new ItemStack(ModItems.itemTeaStone.get(), 3), 2, 1, 0.5f);
        }
    }
}
