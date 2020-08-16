package com.maximum.abouttea.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAboutTeaCap extends INBTSerializable<CompoundNBT>{
    boolean isUnlock();
    void  setUnlock(boolean unlock);
}
