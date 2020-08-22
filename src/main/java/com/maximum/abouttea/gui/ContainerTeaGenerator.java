package com.maximum.abouttea.gui;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.TileTeaGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTeaGenerator extends Container {
    private TileTeaGenerator tile;
    private IIntArray data;

    public ContainerTeaGenerator(int id, PlayerInventory playerInv, PacketBuffer buffer) {
        this(id, playerInv, (TileTeaGenerator) playerInv.player.world.getTileEntity(buffer.readBlockPos()), ((TileTeaGenerator) playerInv.player.world.getTileEntity(buffer.readBlockPos())).getData());
    }

    public ContainerTeaGenerator(int id, PlayerInventory playerInv, TileTeaGenerator tile, IIntArray data) {
        super(ModContainer.TEA_GENERATOR.get(), id);
        this.tile = tile;
        this.data = data;
        trackIntArray(tile.getData());
        createTileInv();
        createPlayerInv(playerInv);
    }

    private void createTileInv() {
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv -> {
            addSlot(new SlotItemHandler(inv, 0, 81, 52));
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
            if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (this.mergeItemStack(itemstack1, 0, 1, false)) { //Forge Fix Shift Clicking in beacons with stacks larger then 1.
                return ItemStack.EMPTY;
            } else if (index >= 1 && index < 28) {
                if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 28 && index < 37) {
                if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 1, 37, false)) {
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

    public IIntArray getData() {
        return data;
    }
}
