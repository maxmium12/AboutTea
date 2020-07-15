package com.maximum.abouttea.init;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.item.ItemTea;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModTea {
    private static Map<String, ItemTea> teas=new HashMap<>();
    public ModTea(){
        registry("greentea",ModItems.itemGreenTea.get());
    }
    public static void registry(String name,ItemTea tea){
        teas.put(name, tea);
    }
    public static Map<String, ItemTea> getTeas(){
        return teas;
    }
}
