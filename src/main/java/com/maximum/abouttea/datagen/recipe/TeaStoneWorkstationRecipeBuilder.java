package com.maximum.abouttea.datagen.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.maximum.abouttea.init.ModRecipeType;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TeaStoneWorkstationRecipeBuilder extends ShapedRecipeBuilder {
    private final int ticks;

    public TeaStoneWorkstationRecipeBuilder(IItemProvider resultIn, int countIn, int ticks) {
        super(resultIn, countIn);
        this.ticks = ticks;
    }

    @Override
    public void build(Consumer<IFinishedRecipe> p_200464_1_) {
        this.build(p_200464_1_, ForgeRegistries.ITEMS.getKey(result));
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        super.validate(id);
        super.advancementBuilder.withParentId(
                new ResourceLocation("recipes/root"))
                .withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id))
                .withRewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(id))
                .withRequirementsStrategy(IRequirementsStrategy.OR);
        assert super.result != null;
        assert super.pattern != null;
        assert super.key != null;
        assert super.advancementBuilder != null;
        assert id != null;
        assert consumerIn != null;
        assert super.result.getGroup() != null;
        ResourceLocation advancementID = new ResourceLocation(id.getNamespace(), "recipes/" + super.result.getGroup().getPath() + "/" + id.getPath());
        consumerIn.accept(new TeaStoneWorkstationRecipeBuilder.FinishRecipe(
                id, super.result, super.count, super.group == null ? "" : super.group,
                super.pattern, super.key, super.advancementBuilder,
                advancementID, ticks));
    }

    public class FinishRecipe extends Result {
        private final int ticks;

        public FinishRecipe(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<String> patternIn, Map<Character, Ingredient> keyIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn, int ticks) {
            super(idIn, resultIn, countIn, groupIn, patternIn, keyIn, advancementBuilderIn, advancementIdIn);
            this.ticks = ticks;
        }

        @Override
        public void serialize(JsonObject json) {
            super.serialize(json);
            json.add("ticks", new JsonPrimitive(ticks));
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return ModRecipeType.TEA_STONE_SERIALIZER;
        }
    }
}
