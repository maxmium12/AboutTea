package com.maximum.abouttea.init;

import com.maximum.abouttea.api.IItemTea;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModTea {
    private static Map<String, IItemTea> teas=new HashMap<>();
    public ModTea(){
        registry("greentea",ModItems.itemGreenTea.get());
    }
    public static void registry(String name,IItemTea tea){
        teas.put(name, tea);
    }
}
