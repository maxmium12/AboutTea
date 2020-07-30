package com.maximum.abouttea.item;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.init.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class ItemTea extends Item implements IItemTea {
    private final int color;
    private final int tier;
    private final EffectInstance[] effect;
    public ItemTea(int color, int tier, EffectInstance... effect) {
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
    public EffectInstance[] getEffects(){
        return effect;
    }
}
