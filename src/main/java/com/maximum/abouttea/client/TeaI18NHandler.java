package com.maximum.abouttea.client;

import com.google.common.collect.Lists;
import com.maximum.abouttea.AboutTea;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.util.text.LanguageMap;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.io.input.ReaderInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TeaI18NHandler implements IResourceManagerReloadListener {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        AboutTea.LOGGER.info("重载茶叶I18N");
        loadLang(resourceManager);
    }
    public static void loadLang(IResourceManager resourceManager){
        List<String> list = Lists.newArrayList("zh_cn");
        Minecraft mc = Minecraft.getInstance();
        try {
            File lang = new File(mc.gameDir,"teas/zh_cn.json");
            if(!lang.exists()){
                lang.createNewFile();
            }
            String currentLang = mc.getLanguageManager().getCurrentLanguage().getCode();
            if (!"en_us".equals(currentLang)) {
                list.add(currentLang);
            }
            LanguageManager.CURRENT_LOCALE.func_195811_a(resourceManager, list);
            LanguageManager.CURRENT_LOCALE.loadLocaleData(
                    new ReaderInputStream(new FileReader(lang), StandardCharsets.UTF_8));
            LanguageMap.replaceWith(LanguageManager.CURRENT_LOCALE.properties);
        }catch (Exception e){
            //什么也不做
        }
    }
}
