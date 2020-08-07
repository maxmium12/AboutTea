package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.effect.EffectInstanceWrapper;
import com.maximum.abouttea.init.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

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
}
