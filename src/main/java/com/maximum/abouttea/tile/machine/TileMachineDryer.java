package com.maximum.abouttea.tile.machine;

import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.gui.ContainerMachineDryer;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileBase;
import com.maximum.abouttea.tile.TileMachineBase;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TileMachineDryer extends TileTeaDryer implements ITickableTileEntity, INamedContainerProvider {
    protected int energy=0;
    private IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index){
                case 0: return ticks[0];
                case 1: return ticks[1];
                case 2: return ticks[2];
                case 3: return ticks[3];
                case 4: return energy;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index){
                case 0: ticks[0] = value;
                case 1: ticks[1] = value;
                case 2: ticks[2] = value;
                case 3: ticks[3] = value;
                case 4: energy = value;
            }
        }

        @Override
        public int size() {
            return 5;
        }
    };
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
            return TileMachineDryer.getMaxEnergy();
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

    public TileMachineDryer(){
        super(ModTiles.MACHINE_DRYER.get());
    }

    @Override
    public int getInvSize() {
        return 8;
    }
    @Override
    public void tick() {
        for(int i = 0;i<4;i++){
            IDryerRecipe recipe;
            if((recipe = findRecipe(inv.getStackInSlot(i)))!=null && energy >= 40){
                if (!(ticks[i]>=recipe.getTicks())){
                    ticks[i]+=3;
                    energy -= 40;
                }else {
                    if(inv.insertItem(i + 4,recipe.getRecipeOutput(),true).isEmpty()) {
                        inv.insertItem(i + 4,recipe.getRecipeOutput(),false);
                        ticks[i] = 0;
                        markDirty();
                    }
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

    public IIntArray getData() {
        return data;
    }

    public static int getMaxEnergy(){
        return 120000;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("abouttea.machinedryer.title");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
        return new ContainerMachineDryer(id, playerInv, this, data);
    }
}
