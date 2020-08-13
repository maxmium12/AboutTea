package com.maximum.abouttea.util;

import com.google.gson.*;
import com.maximum.abouttea.block.BlockTeaBookConverter;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import static com.maximum.abouttea.AboutTea.prefix;

public class UnlockTechTrigger extends AbstractCriterionTrigger<UnlockTechTrigger.Instance> {
    private static final ResourceLocation ID = prefix("unlock_tech");
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        return new Instance();
    }
    public void trigger(ServerPlayerEntity entity, BlockState block){
        func_227070_a_(entity.getAdvancements(), (in) -> in.test(block));
    }

    public static class Instance extends CriterionInstance{
        public Instance() {
            super(ID);
        }
        public boolean test(BlockState state) {
            return  (state.getBlock() instanceof BlockTeaBookConverter && state.get(BlockTeaBookConverter.CONVERT));
        }
    }
}
