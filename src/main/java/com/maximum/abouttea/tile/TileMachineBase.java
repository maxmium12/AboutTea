package com.maximum.abouttea.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public abstract class TileMachineBase extends TileBase implements ITickableTileEntity {
    private final int maxEnergy;
    private final int energyReceive;
    private final int energyExtract;
    private final boolean canReceive;
    private final boolean canExtract;
    public boolean isWork = false;
    protected int energy = 0;
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int energy = this.getEnergyStored();
            int diff = Math.min(getMaxEnergyStored() - energy, maxReceive);
            if (!simulate) {
                TileMachineBase.this.energy += diff;
                if (diff != 0) {
                    markDirty();
                }
            }
            return diff;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int energy = this.getEnergyStored();
            int diff = Math.min(energy, maxExtract);
            if (!simulate) {
                TileMachineBase.this.energy -= diff;
                if (diff != 0) {
                    markDirty();
                }
            }
            return diff;
        }

        @Override
        public int getEnergyStored() {
            return Math.max(0, Math.min(this.getMaxEnergyStored(), energy));
        }

        @Override
        public int getMaxEnergyStored() {
            return maxEnergy;
        }

        @Override
        public boolean canExtract() {
            return canExtract;
        }

        @Override
        public boolean canReceive() {
            return canReceive;
        }
    });

    public TileMachineBase(TileEntityType<?> type, int maxEnergy, int energyReceive, int energyExtract, boolean canExtract, boolean canReceive) {
        super(type);
        this.maxEnergy = maxEnergy;
        this.energyReceive = energyReceive;
        this.energyExtract = energyExtract;
        this.canExtract = canExtract;
        this.canReceive = canReceive;
    }

    public abstract void doWork();

    public abstract boolean canWork();

    public abstract void stop();

    public void tick() {
        if (canWork()) {
            isWork = true;
            doWork();
            return;
        }
        if (isWork) {
            isWork = false;
            stop();
        }
    }

    @Override
    public void readPacketNBT(CompoundNBT compound) {
        super.readPacketNBT(compound);
        energy = compound.getInt("energy");
    }

    @Override
    public void writePacketNBT(CompoundNBT nbt) {
        nbt.putInt("energy", energy);
        super.writePacketNBT(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityEnergy.ENERGY) return lazyOptional.cast();
        return super.getCapability(cap, side);
    }
}
