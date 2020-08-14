package com.maximum.abouttea.tile;

import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.util.network.SimpleEnergyNetwork;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import java.util.Objects;
//来自4z大佬教程
public class TileWire extends TileEntity {
    private Integer tmpEnergy = null;

    public TileWire() {
        super(ModTiles.WIRE_TILE.get());
    }
    private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage()
    {
        private final SimpleEnergyNetwork network = SimpleEnergyNetwork.Factory.get(TileWire.this.world);

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int energy = this.getEnergyStored();
            int diff = Math.min(500, Math.min(this.getMaxEnergyStored() - energy, maxReceive));
            if (!simulate) {
                this.network.addEnergy(TileWire.this.pos, diff);
                if (diff != 0) {
                    TileWire.this.markDirty();
                }
            }
            return diff;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int energy = this.getEnergyStored();
            int diff = Math.min(500, Math.min(energy, maxExtract));
            if (!simulate) {
                this.network.addEnergy(TileWire.this.pos, -diff);
                if (diff != 0) {
                    TileWire.this.markDirty();
                }
            }
            return diff;
        }

        @Override
        public int getEnergyStored()
        {
            return Math.min(this.getMaxEnergyStored(), this.network.getNetworkEnergy(TileWire.this.pos));
        }

        @Override
        public int getMaxEnergyStored()
        {
            return 1_000 * this.network.getNetworkSize(TileWire.this.pos);
        }

        @Override
        public boolean canExtract()
        {
            return true;
        }

        @Override
        public boolean canReceive()
        {
            return true;
        }
    });
    @Override
    public void read(@Nonnull CompoundNBT compound) {
        this.tmpEnergy = compound.getInt("WireEnergy");
        super.read(compound);
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        SimpleEnergyNetwork network = SimpleEnergyNetwork.Factory.get(this.world);
        compound.putInt("WireEnergy", network.getSharedEnergy(this.pos));
        return super.write(compound);
    }
    @Override
    public void onLoad() {
        if (this.world != null && !this.world.isRemote)
        {
            SimpleEnergyNetwork network = SimpleEnergyNetwork.Factory.get(this.world);
            if (this.tmpEnergy != null)
            {
                int diff = this.tmpEnergy - network.getSharedEnergy(this.pos);
                network.addEnergy(this.pos, diff);
                this.tmpEnergy = null;
            }
            network.enableBlock(this.pos, this::markDirty);
        }
        super.onLoad();
    }
    @Override
    public void onChunkUnloaded() {
        if (this.world != null && !this.world.isRemote)
        {
            SimpleEnergyNetwork network = SimpleEnergyNetwork.Factory.get(this.world);
            network.disableBlock(this.pos, this::markDirty);
        }
        super.onChunkUnloaded();
    }

    @Override
    public void remove() {
        if (this.world != null && !this.world.isRemote)
        {
            SimpleEnergyNetwork network = SimpleEnergyNetwork.Factory.get(this.world);
            network.disableBlock(this.pos, () ->
            {
                int diff = network.getSharedEnergy(this.pos);
                network.addEnergy(this.pos, -diff);
                this.markDirty();
            });
        }
        super.remove();
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        boolean isEnergy = Objects.equals(cap, CapabilityEnergy.ENERGY);
        return isEnergy ? this.lazyOptional.cast() : super.getCapability(cap, side);
    }
}
