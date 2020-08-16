package com.maximum.abouttea.gui;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.TileBase;
import com.maximum.abouttea.tile.machine.TileMachineDryer;
import com.maximum.abouttea.tile.machine.TileTeaStoneWorkstation;
import com.maximum.abouttea.tile.manual.TileTeaMixer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerMachineDryer extends Container {
    private final TileMachineDryer tile;
    private final IIntArray data;
    public ContainerMachineDryer(int id, PlayerInventory playerInv, PacketBuffer buffer){
        this(id, playerInv, (TileMachineDryer) playerInv.player.world.getTileEntity(buffer.readBlockPos()),((TileMachineDryer) playerInv.player.world.getTileEntity(buffer.readBlockPos())).getData());
    }

    public ContainerMachineDryer(int id, PlayerInventory playerInv, TileMachineDryer tile, IIntArray data) {
        super(ModContainer.MACHINE_DRYER.get(), id);
        this.tile = tile;
        this.data = data;
        trackIntArray(data);
        addSlotPair(0, 20, 13);
        addSlotPair(1, 50, 13);
        addSlotPair(2, 89, 13);
        addSlotPair(3, 119, 13);
        createPlayerInv(playerInv);
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
    private void addTileSlots(TileBase tile){
        addSlotPair(0, 20, 13);
        addSlotPair(1, 50, 13);
        addSlotPair(2, 89, 13);
        addSlotPair(3, 119, 13);
    }
    private void addSlotPair(int index, int x, int y){
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv ->{
            addSlot(new SlotItemHandler(tile.getInv(), index, x, y));
            AboutTea.LOGGER.info(index);
            addSlot(new SlotItemHandler(tile.getInv(), index+4, x, y+46));
            AboutTea.LOGGER.info(index + 4);
        });
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if(index <= 7){
                if (!this.mergeItemStack(itemstack, 8, 44, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChanged();
            } else if (this.mergeItemStack(slotStack, 0, 3, false)) { //Forge Fix Shift Clicking in beacons with stacks larger then 1.
                return ItemStack.EMPTY;
            } else if (index >= 8 && index < 35) {
                if (!this.mergeItemStack(slotStack, 35, 44, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 35 && index < 44) {
                if (!this.mergeItemStack(slotStack, 8, 35, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(slotStack, 8, 44, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
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
