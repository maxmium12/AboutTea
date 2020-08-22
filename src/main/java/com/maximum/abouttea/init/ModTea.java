package com.maximum.abouttea.init;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.maximum.abouttea.effect.EffectInstanceWrapper;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.potion.Effects;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModTea {
    private static final Map<String, ItemTea> teas = new HashMap<>();

    public ModTea() {
        registry("green_tea", 0x30cbba, 1);
        registry("golden_tea", 0xffd700, 1, new EffectInstanceWrapper(Effects.HASTE.getRegistryName(), 800, 2));
        registry("explosive_tea", 0xdc143c, 1, new EffectInstanceWrapper(ModEffect.EFFECT_EXPLOSIVE.getRegistryName(), 1, 1));
        registry("lightning_tea", 0x0000dc, 1, new EffectInstanceWrapper(ModEffect.EFFECT_LIGHTNING.getRegistryName(), 1, 1));
        registry("explosive_tea_2", 0xdc143c, 2, new EffectInstanceWrapper(ModEffect.EFFECT_EXPLOSIVE.getRegistryName(), 1, 2));
        registry("lightning_tea_2", 0x0000dc, 2, new EffectInstanceWrapper(ModEffect.EFFECT_LIGHTNING.getRegistryName(), 3, 1));
        registry("explosive_tea_3", 0xdc143c, 3, new EffectInstanceWrapper(ModEffect.EFFECT_EXPLOSIVE.getRegistryName(), 1, 3));
        registry("lightning_tea_3", 0x0000dc, 3, new EffectInstanceWrapper(ModEffect.EFFECT_LIGHTNING.getRegistryName(), 5, 1));

        File teadir = new File(".", "teas");
        if (!teadir.exists()) {
            teadir.mkdir();
        }
        File json = null;
        if (teadir.listFiles() != null) {
            for (File file : teadir.listFiles()) {
                if (file.getName().equals("teas.json")) {
                    json = file;
                    break;
                }
            }
        }
        try {
            if (json != null) {
                JsonReader reader = new JsonReader(new FileReader(json));
                JsonParser parser = new JsonParser();
                JsonObject teaitems = parser.parse(reader).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : teaitems.entrySet()) {
                    String name = entry.getKey();
                    JsonObject details = entry.getValue().getAsJsonObject();
                    int tier = details.get("tier").getAsInt();
                    int color = details.get("color").getAsInt();
                    List<EffectInstanceWrapper> effectInstances = new ArrayList<>();
                    JsonObject effects = JSONUtils.getJsonObject(details, "effects");
                    for (Map.Entry<String, JsonElement> entry1 : effects.entrySet()) {
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

    public static void registry(String name, int color, int tier, EffectInstanceWrapper... effect) {
        ItemTea tea = new ItemTea(color, tier, effect);
        RegistryUtil.registryItem(name, () -> tea);
        teas.put(name, tea);
    }

    public static Map<String, ItemTea> getTeas() {
        return teas;
    }

    public static ItemTea getTea(String name) {
        return teas.get(name);
    }
}
