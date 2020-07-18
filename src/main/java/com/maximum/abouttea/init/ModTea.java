package com.maximum.abouttea.init;

import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModTea {
    private static Map<String, ItemTea> teas=new HashMap<>();
    public ModTea(){
        registry("green_tea",0x30cbba,1);
    }
    public static void registry(String name,int color,int tier){
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
