package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.effect.EffectInstanceWrapper;
import com.maximum.abouttea.init.ModGroup;
import com.maximum.abouttea.util.RenderUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemTea extends Item implements IItemTea {
    private final int color;
    private final int tier;
    private final EffectInstanceWrapper[] effect;
    public ItemTea(int color, int tier, EffectInstanceWrapper... effect) {
        super(new Properties().group(ModGroup.GROUP));
        this.color=color;
        this.tier=tier;
        this.effect=effect;
    }
    @Override
    public int getColor() {
        return color;
    }
    public int getTier(){
        return tier;
    }
    public EffectInstance[] getEffects() {
        List<EffectInstance> effectInstances=new ArrayList<>();
        for(EffectInstanceWrapper wrapper:effect){
            effectInstances.add(wrapper.getEffectInstance());
        }
        return effectInstances.toArray(new EffectInstance[0]);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        List<EffectInstance> list = new ArrayList<>();
        for(EffectInstanceWrapper wrapper: effect){
            list.add(wrapper.getEffectInstance());
        }
        RenderUtil.addPotionToolTips(list, tooltip, 1.0f);
    }
}
