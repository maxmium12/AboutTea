package com.maximum.abouttea;

import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTiles;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("abouttea")
public class AboutTea {
    private static final Logger LOGGER = LogManager.getLogger();
    public AboutTea instance;
    public static final String MODID="abouttea";
    public AboutTea(){
        instance=this;
        ModItems.register();
        ModBlock.register();
        ModContainer.register();
        ModTiles.register();
    }
}
