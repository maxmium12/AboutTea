package com.maximum.abouttea.init;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.maximum.abouttea.api.IEffect;
import com.maximum.abouttea.effect.EffectInstanceWrapper;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ModTea {
    private static final Map<String, ItemTea> teas=new HashMap<>();
    public ModTea(){
        registry("green_tea",0x30cbba,1);
        registry("golden_tea",0xffd700,1, new EffectInstanceWrapper(Effects.HASTE.getRegistryName(),800,2));
        File teadir=new File(".","teas");
        if(!teadir.exists()){
            teadir.mkdir();
        }
        File json = null;
        if(teadir.listFiles() != null){
            for(File file:teadir.listFiles()){
                if(file.getName().equals("teas.json")){
                    json=file;
                    break;
                }
            }
        }
        try {
            if(json != null){
                JsonReader reader = new JsonReader(new FileReader(json));
                JsonParser parser = new JsonParser();
                JsonObject teaitems = parser.parse(reader).getAsJsonObject();
                for(Map.Entry<String, JsonElement> entry:teaitems.entrySet()){
                    String name = entry.getKey();
                    JsonObject details = entry.getValue().getAsJsonObject();
                    int tier = details.get("tier").getAsInt();
                    int color = details.get("color").getAsInt();
                    List<EffectInstanceWrapper> effectInstances = new ArrayList<>();
                    JsonObject effects = JSONUtils.getJsonObject(details,"effects");
                    for(Map.Entry<String, JsonElement> entry1:effects.entrySet()){
                        ResourceLocation effect = new ResourceLocation(entry1.getKey());
                        JsonObject effectDetail = entry1.getValue().getAsJsonObject();
                        int amplifier = effectDetail.get("amplifier").getAsInt();
                        int duration = effectDetail.get("duration").getAsInt();
                        effectInstances.add(new EffectInstanceWrapper(effect, duration, amplifier));
                    }
                    registry(name, color, tier, effectInstances.toArray(new EffectInstanceWrapper[0]));
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    public static void registry(String name, int color, int tier, EffectInstanceWrapper... effect){
        ItemTea tea=new ItemTea(color,tier, effect);
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
