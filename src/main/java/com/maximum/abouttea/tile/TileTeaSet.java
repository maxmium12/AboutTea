package com.maximum.abouttea.tile;

import com.maximum.abouttea.init.ModBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileTeaSet extends TileEntity implements ITickableTileEntity {
    private LazyOptional<IItemHandler> cupHandler=LazyOptional.of(()->new ItemStackHandler(2));
    public TileTeaSet() {
        super(ModBlock.TEA_SET_TILE.get());
    }
    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side){
        if(cap==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)return cupHandler.cast();
        return super.getCapability(cap,side);
    }
    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT inv=compound.getCompound("inv");
        cupHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(inv));
    }
    public CompoundNBT write(CompoundNBT compound){
        cupHandler.ifPresent(h -> {
            CompoundNBT inv=((INBTSerializable<CompoundNBT>)h).serializeNBT();
                                    compound.put("inv",inv);
        });
        return super.write(compound);
    }

    @Override
    public void tick() {

    }
    public LazyOptional<IItemHandler> getCupHandler(){
        return cupHandler;
    }
}
