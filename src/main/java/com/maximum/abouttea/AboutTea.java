package com.maximum.abouttea;

import com.maximum.abouttea.init.*;
import com.maximum.abouttea.util.RegistryUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("abouttea")
public class AboutTea {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "abouttea";
    public static AboutTea instance;

    public AboutTea() {
        instance = this;
        new ModBlock();
        new ModTiles();
        new ModFeature();
        new ModItems();
        new ModTea();
        new ModContainer();
        new ModEffect();
        RegistryUtil.register();
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name);
    }
}
