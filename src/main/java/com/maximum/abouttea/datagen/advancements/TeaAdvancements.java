package com.maximum.abouttea.datagen.advancements;

import com.maximum.abouttea.init.ModTea;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class TeaAdvancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        Advancement advancement = Advancement.Builder.builder().withDisplay(ModTea.getTea("greentea"), new TranslationTextComponent("abouttea.advancements.title"), new TranslationTextComponent("abouttea.advancements.description"), new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"), FrameType.TASK, false, false, false).withCriterion("pickup_tea", InventoryChangeTrigger.Instance.forItems(ModTea.getTea("green_tea"))).register(advancementConsumer, "tea/root");
    }
}
