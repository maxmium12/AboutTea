package com.maximum.abouttea.tile;

import com.maximum.abouttea.gui.ContainerTeaGenerator;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.util.EnergyArray;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileTeaGenerator extends TileBase implements ITickableTileEntity, INamedContainerProvider {
    private final Direction.Plane DIRECTION = Direction.Plane.HORIZONTAL;
    protected int energy;
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int energy = this.getEnergyStored();
            int diff = Math.min(getMaxEnergyStored() - energy, maxReceive);
            if (!simulate) {
                TileTeaGenerator.this.energy += diff;
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
                TileTeaGenerator.this.energy -= diff;
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
    private int ticks;
    private int maxTicks;
    private ItemTea currentTea;
    private final EnergyArray data = new EnergyArray(ticks, energy, maxTicks);

    public TileTeaGenerator() {
        super(ModTiles.TEA_GENERATOR_TILE.get());
    }

    public static int getMaxEnergy() {
        return 120000;
    }

    @Override
    public int getInvSize() {
        return 1;
    }

    @Override
    public void tick() {
        ItemStack stack = inv.getStackInSlot(0).copy();
        if (stack.getItem() instanceof ItemTea) {
            if (ticks == 0) {
                currentTea = (ItemTea) stack.getItem();
                maxTicks = currentTea.getTier() * 300;
                inv.extractItem(0, 1, false);
                markDirty();
            }
        }
        if (ticks < maxTicks) {
            if (energy < getMaxEnergy()) {
                ticks++;
                energy += currentTea.getTier() * 40;
            }
        } else {
            ticks = 0;
            maxTicks = 0;
            markDirty();
        }
        if (!world.isRemote) {
            data.setEnergy(energy);
            data.set(0, ticks);
            data.set(2, maxTicks);
        }
        transferEnergy();
    }

    private void transferEnergy() {
        for (Direction direction : DIRECTION) {
            TileEntity tile = world.getTileEntity(pos.offset(direction));
            if (tile != null) {
                tile.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
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

    public EnergyArray getData() {
        return data;
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityEnergy.ENERGY) return lazyOptional.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new ContainerTeaGenerator(id, inv, this, data);
    }
}
