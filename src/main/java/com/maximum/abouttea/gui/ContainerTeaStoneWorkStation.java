package com.maximum.abouttea.gui;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.machine.TileTeaStoneWorkstation;
import com.maximum.abouttea.util.EnergyArray;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTeaStoneWorkStation extends Container {
    private final TileTeaStoneWorkstation tile;
    private final EnergyArray data;

    public ContainerTeaStoneWorkStation(int id, PlayerInventory playerInv, PacketBuffer buffer) {
        this(id, playerInv, (TileTeaStoneWorkstation) playerInv.player.world.getTileEntity(buffer.readBlockPos()), ((TileTeaStoneWorkstation) playerInv.player.world.getTileEntity(buffer.readBlockPos())).getData());
    }

    public ContainerTeaStoneWorkStation(int id, PlayerInventory playerInv, TileTeaStoneWorkstation tile, IIntArray data) {
        super(ModContainer.TEA_STONE_WORKSTATION.get(), id);
        this.tile = tile;
        this.data = (EnergyArray) data;
        trackIntArray(data);
        addTileInv();
        createPlayerInv(playerInv);
    }

    private void addTileInv() {
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv -> {
            this.addSlot(new SlotItemHandler(inv, 9, 124, 35));
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    this.addSlot(new SlotItemHandler(inv, j + i * 3, 30 + j * 18, 17 + i * 18));
                }
            }
        });
    }

    private void createPlayerInv(PlayerInventory inv) {
        for (int k = 0; k < 9; k++) {
            addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index >= 0 && index < 10) {
                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (this.mergeItemStack(itemstack1, 1, 9, true)) { //Forge Fix Shift Clicking in beacons with stacks larger then 1.
                return ItemStack.EMPTY;
            } else if (index >= 10 && index < 37) {
                if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 37 && index < 46) {
                if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public TileTeaStoneWorkstation getTile() {
        return tile;
    }

    public EnergyArray getData() {
        return data;
    }
}
