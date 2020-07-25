package com.maximum.abouttea.init;

import com.maximum.abouttea.api.IEffect;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.HashMap;
import java.util.Map;

public class ModTea {
    private static final Map<String, ItemTea> teas=new HashMap<>();
    private static final Map<ItemTea, IEffect> effects=new HashMap<>();
    public ModTea(){
        registry("green_tea",0x30cbba,1,(player)->{},1);
        registry("golden_tea",0xffd700,1,player -> {player.addPotionEffect(new EffectInstance(Effects.HASTE,20));},1);
    }
    public static void registry(String name,int color,int tier,IEffect effect,int time){
        ItemTea tea=new ItemTea(color,tier);
        RegistryUtil.registryItem(name,()->tea);
        teas.put(name, tea);
    }
    public static Map<String, ItemTea> getTeas(){
        return teas;
    }
    public static ItemTea getTea(String name){
        return teas.get(name);
    }
}
