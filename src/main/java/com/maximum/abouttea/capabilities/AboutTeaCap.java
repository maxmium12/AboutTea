package com.maximum.abouttea.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AboutTeaCap{
    public static class Impl implements IAboutTeaCap{
        private boolean unlock;
        @Override
        public boolean isUnlock() {
            return unlock;
        }

        @Override
        public void setUnlock(boolean unlock) {
            this.unlock=unlock;
        }
    }
    public static class Storage implements Capability.IStorage<IAboutTeaCap>{

        @Nullable
        @Override
        public INBT writeNBT(Capability<IAboutTeaCap> capability, IAboutTeaCap instance, Direction side) {
            CompoundNBT nbt=new CompoundNBT();
            nbt.putBoolean("unlock",instance.isUnlock());
            return nbt;
        }

        @Override
        public void readNBT(Capability<IAboutTeaCap> capability, IAboutTeaCap instance, Direction side, INBT nbt) {
            CompoundNBT compound= (CompoundNBT) nbt;
            instance.setUnlock(compound.getBoolean("unlock"));
        }
    }
    public static class ProviderPlayer implements ICapabilitySerializable<CompoundNBT>{
        private final IAboutTeaCap aboutTeaCap=new Impl();
        private final Capability.IStorage<IAboutTeaCap> storage = CapabilityHandler.ABOUTTEACAP.getStorage();
        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if(CapabilityHandler.ABOUTTEACAP.equals(cap)){
                @SuppressWarnings("uncheck")
                T result=(T) cap;
                return LazyOptional.of(()->result);
            }
            return null;
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT compound=new CompoundNBT();
            compound.put("aboutteacap",storage.writeNBT(CapabilityHandler.ABOUTTEACAP,aboutTeaCap,null));
            return compound;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            CompoundNBT compound=nbt.getCompound("aboutteacap");
            storage.readNBT(CapabilityHandler.ABOUTTEACAP,aboutTeaCap,null,compound);
        }
    }
}
