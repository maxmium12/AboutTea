package com.maximum.abouttea.tile.machine;

import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.tile.TileBase;
import com.maximum.abouttea.tile.TileMachineBase;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class TileMachineDryer extends TileTeaDryer implements ITickableTileEntity {
    protected int energy=0;
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int energy=this.getEnergyStored();
            int diff=Math.min(getMaxEnergyStored()-energy,maxReceive);
            if(!simulate){
                TileMachineDryer.this.energy+=diff;
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
                TileMachineDryer.this.energy-=diff;
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
    @Override
    public void tick() {
        for(int i = 0;i<inv.getSlots();i++){
            IDryerRecipe recipe;
            if((recipe = findRecipe(inv.getStackInSlot(i)))!=null){
                if (!(ticks[i]>=recipe.getTicks()) && energy >= 40){
                    ticks[i]++;
                    energy -= 40;
                }else {
                    inv.setStackInSlot(i,recipe.getRecipeOutput());
                    ticks[i]=0;
                    markDirty();
                }
            }
        }
    }
    @Override
    public void readPacketNBT(CompoundNBT compound) {
        super.readPacketNBT(compound);
        energy = compound.getInt("energy");
    }

    @Override
    public void writePacketNBT(CompoundNBT nbt){
        nbt.putInt("energy",energy);
        super.writePacketNBT(nbt);
    }
}
