package com.maximum.abouttea.tile;

import com.maximum.abouttea.gui.ContainerTeaSet;
import com.maximum.abouttea.init.ModTiles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileTeaSet extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private final Inventory inv = new Inventory(1);
    private final LazyOptional<IItemHandler> cupHandler = LazyOptional.of(() -> new ItemStackHandler(2));

    public TileTeaSet() {
        super(ModTiles.TEA_SET_TILE.get());
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return cupHandler.cast();
        return super.getCapability(cap, side);
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT inventory = compound.getCompound("inv");
        inv.addItem(ItemStack.read(inventory));
    }

    public CompoundNBT write(CompoundNBT compound) {
        ItemStack stack = inv.getStackInSlot(0).copy();
        compound.put("inv", stack.serializeNBT());
        return super.write(compound);
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerTeaSet(sycID, inventory, this.pos, this.world);
    }

    @Override
    public void tick() {

    }

    public LazyOptional<IItemHandler> getCupHandler() {
        return cupHandler;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("abouttea.teaset.title");
    }
}
