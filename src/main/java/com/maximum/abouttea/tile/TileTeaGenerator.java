package com.maximum.abouttea.tile;

import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.tile.machine.TileMachineDryer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileTeaGenerator extends TileBase implements ITickableTileEntity {
    protected int energy=0;
    private int ticks = 0;
    private int maxTicks = 0;
    private ItemTea currentTea;
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int energy=this.getEnergyStored();
            int diff=Math.min(getMaxEnergyStored()-energy,maxReceive);
            if(!simulate){
                TileTeaGenerator.this.energy+=diff;
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
                TileTeaGenerator.this.energy-=diff;
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
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    });

    public TileTeaGenerator() {
        super(ModTiles.TEA_GENERATOR_TILE.get());
    }

    @Override
    public int getInvSize() {
        return 1;
    }

    @Override
    public void tick() {
        ItemStack stack = inv.getStackInSlot(0).copy();
        if(stack.getItem() instanceof ItemTea){
            if(ticks == 0){
                currentTea = (ItemTea) stack.getItem();
                maxTicks = currentTea.getTier() * 300;
                inv.extractItem(0, 1, false);
                markDirty();
            }
        }
        if(ticks < maxTicks){
            ticks++;
            energy+=currentTea.getTier() * 40;
        } else {
            ticks = 0;
            maxTicks = 0;
            markDirty();
        }
        transferEnergy();
    }
    private final Direction.Plane DIRECTION = Direction.Plane.HORIZONTAL;
    private void transferEnergy(){
        for(Direction direction:DIRECTION){
            TileEntity tile = world.getTileEntity(pos.offset(direction));
            if(tile != null){
                tile.getCapability(CapabilityEnergy.ENERGY,direction.getOpposite()).ifPresent(storage -> {
                    if (storage.canReceive()) {
                        int diff = storage.receiveEnergy(Math.min(500, this.energy), false);
                        if (diff != 0) {
                            this.energy -= diff;
                            this.markDirty();
                        }
                    }
                });
            }
        }
    }
}
