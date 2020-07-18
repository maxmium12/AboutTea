package com.maximum.abouttea.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {
    @CapabilityInject(IAboutTeaCap.class)
    public static Capability<IAboutTeaCap> ABOUTTEACAP;
    public CapabilityHandler(){
        CapabilityManager.INSTANCE.register(IAboutTeaCap.class,new AboutTeaCap.Storage(),AboutTeaCap.Impl::new);
    }
}
