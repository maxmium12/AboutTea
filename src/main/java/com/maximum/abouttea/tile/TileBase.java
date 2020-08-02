package com.maximum.abouttea.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class TileBase extends TileEntity {
    protected ItemStackHandler inv = new ItemStackHandler(getInvSize());
    private final LazyOptional<IItemHandler> autoinv = LazyOptional.of(()->inv);
    public TileBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public abstract int getInvSize();
    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        inv.deserializeNBT(compound);
    }
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.merge(inv.serializeNBT());
        return super.write(compound);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, autoinv);
    }
    public ItemStackHandler getInv(){
        return inv;
    }
}
