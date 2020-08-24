package com.maximum.abouttea.network;

import com.maximum.abouttea.capabilities.CapabilityHandler;
import com.maximum.abouttea.capabilities.IAboutTeaCap;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MsgCapabilitySync {
    public CompoundNBT cap;

    public MsgCapabilitySync(CompoundNBT cap) {
        this.cap = cap;
    }

    public MsgCapabilitySync(PacketBuffer buffer) {
        cap = buffer.readCompoundTag();
    }

    public void toByte(PacketBuffer buffer) {
        buffer.writeCompoundTag(cap);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            Minecraft.getInstance().player.getCapability(CapabilityHandler.ABOUTTEACAP).ifPresent(iAboutTeaCap -> {
                Capability.IStorage<IAboutTeaCap> storage = CapabilityHandler.ABOUTTEACAP.getStorage();
                storage.readNBT(CapabilityHandler.ABOUTTEACAP, iAboutTeaCap, null, cap);
            });
        }
        });
        ctx.get().setPacketHandled(true);
    }
}
