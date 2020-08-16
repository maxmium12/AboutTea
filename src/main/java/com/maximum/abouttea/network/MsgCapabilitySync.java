package com.maximum.abouttea.network;

import com.maximum.abouttea.capabilities.CapabilityHandler;
import com.maximum.abouttea.capabilities.IAboutTeaCap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class MsgCapabilitySync {
    public CompoundNBT cap;
    public MsgCapabilitySync(CompoundNBT cap){this.cap = cap;}
    public MsgCapabilitySync(PacketBuffer buffer){
        cap = buffer.readCompoundTag();
    }
    public void toByte(PacketBuffer buffer){
        buffer.writeCompoundTag(cap);
    }
    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            PlayerEntity player= Minecraft.getInstance().player;
            player.getCapability(CapabilityHandler.ABOUTTEACAP).ifPresent(iAboutTeaCap -> {
                Capability.IStorage<IAboutTeaCap> storage=CapabilityHandler.ABOUTTEACAP.getStorage();
                storage.readNBT(CapabilityHandler.ABOUTTEACAP,iAboutTeaCap,null,cap);
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
