package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.effect.EffectInstanceWrapper;
import com.maximum.abouttea.init.ModGroup;
import com.maximum.abouttea.util.RenderUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemTeaCup extends Item {
    public ItemTeaCup() {
        super(new Properties().group(ItemGroup.MISC).maxStackSize(1).group(ModGroup.GROUP));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack cup = playerIn.getHeldItem(handIn);
        ItemStack teaStack = getTea(cup);
        RayTraceResult result = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if(result.getType() == RayTraceResult.Type.BLOCK){
            BlockRayTraceResult ret = (BlockRayTraceResult) result;
            if(worldIn.getFluidState(ret.getPos()).isTagged(FluidTags.WATER)) {
                setWater(cup, true);
                return ActionResult.resultSuccess(cup);
            }
        }
        if(teaStack.getItem() instanceof ItemTea && hasWater(cup)) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(cup);
        }
        return ActionResult.resultPass(cup);
    }
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack teaStack = getTea(stack);
        if(teaStack.getItem() instanceof ItemTea) {
            ItemTea tea = (ItemTea) teaStack.getItem();
            for(EffectInstance effect: tea.getEffects()){
                entityLiving.addPotionEffect(effect);
            }
        }
        setEmpty(stack);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("abouttea.teacup.tea", getTea(stack).getItem().getName().getString()));
        if(hasWater(stack)){
            tooltip.add(new TranslationTextComponent("abouttea.teacup.water"));
        }
    }
    public static void setTea(ItemStack cup,ItemTea tea){
        ItemStack teastack=new ItemStack(tea);
        CompoundNBT nbt=cup.getOrCreateChildTag("tea");
        nbt.put("tea",teastack.serializeNBT());
    }
    public static void setEmpty(ItemStack cup){
        CompoundNBT nbt=cup.getOrCreateChildTag("tea");
        nbt.put("tea",ItemStack.EMPTY.serializeNBT());
    }
    public static ItemStack getTea(ItemStack cup){
        CompoundNBT nbt=cup.getOrCreateChildTag("tea");
        return ItemStack.read(nbt.getCompound("tea"));
    }
    public static boolean hasWater(ItemStack cup){
        CompoundNBT nbt = cup.getOrCreateTag();
       return nbt.getBoolean("water");
    }
    public static ItemStack setWater(ItemStack cup, boolean has){
        CompoundNBT nbt = cup.getOrCreateTag();
        nbt.putBoolean("water", has);
        return cup;
    }
}
