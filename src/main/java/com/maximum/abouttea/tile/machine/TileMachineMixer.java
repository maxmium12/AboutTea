package com.maximum.abouttea.tile.machine;

import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public class TileMachineMixer extends TileTeaDryer {
    protected int energy=0;
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int energy=this.getEnergyStored();
            int diff=Math.min(getMaxEnergyStored()-energy,maxReceive);
            if(!simulate){
                TileMachineMixer.this.energy+=diff;
                if(diff!=0){
                    markDirty();
                }
            }
            return diff;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int energy=this.getEnergyStored();
            int diff=Math.min(energy,maxExtract);
            if(!simulate){
                TileMachineMixer.this.energy-=diff;
                if(diff!=0){
                    markDirty();
                }
            }
            return diff;
        }

        @Override
        public int getEnergyStored() {
            return Math.max(0,Math.min(this.getMaxEnergyStored(),energy));
        }

        @Override
        public int getMaxEnergyStored() {
            return 120000;
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    });

    public TileMachineMixer(){
        super(ModTiles.MACHINE_MIXER.get());
    }
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if(cap == CapabilityEnergy.ENERGY) return lazyOptional.cast();
        return super.getCapability(cap, side);
    }
}
