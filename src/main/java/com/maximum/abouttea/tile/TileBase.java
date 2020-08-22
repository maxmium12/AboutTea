package com.maximum.abouttea.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TileBase extends TileEntity {
    protected ItemStackHandler inv = new ItemStackHandler(getInvSize());
    private final LazyOptional<IItemHandler> autoinv = LazyOptional.of(() -> inv);

    public TileBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public abstract int getInvSize();

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        readPacketNBT(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        writePacketNBT(compound);
        return super.write(compound);
    }

    public void writePacketNBT(CompoundNBT compound) {
        compound.merge(inv.serializeNBT());
    }

    public void readPacketNBT(CompoundNBT compound) {
        inv.deserializeNBT(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, autoinv);
        return super.getCapability(cap, side);
    }

    public ItemStackHandler getInv() {
        return inv;
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compound = new CompoundNBT();
        writePacketNBT(compound);
        return new SUpdateTileEntityPacket(pos, 999, compound);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        readPacketNBT(pkt.getNbtCompound());
    }

    public RecipeWrapper getRecipeWrapper() {
        return new RecipeWrapper(inv);
    }
}
