package com.maximum.abouttea.util;

import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTDynamicOps;

public class SerializeUtil {
    public static JsonObject serializeStack(ItemStack stack){
        CompoundNBT nbt = stack.serializeNBT();
        byte c = nbt.getByte("Count");
        if (c != 1) {
            nbt.putByte("count", c);
        }
        nbt.remove("Count");
        renameNBT(nbt, "id", "item");
        renameNBT(nbt, "tag", "nbt");
        Dynamic<INBT> dyn = new Dynamic<>(NBTDynamicOps.INSTANCE, nbt);
        return dyn.convert(JsonOps.INSTANCE).getValue().getAsJsonObject();
    }
    public static void renameNBT(CompoundNBT nbt,String prev, String newname){
        INBT storage=nbt.get(prev);
        if(storage!=null){
            nbt.remove(prev);
            nbt.put(newname,storage);
        }
    }
}
