package com.maximum.abouttea.gui;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.manual.TileTeaMixer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerMixer extends Container {
    private TileTeaMixer tile;

    public ContainerMixer(int id, PlayerInventory playerInv, PacketBuffer buffer) {
        this(id, playerInv, (TileTeaMixer) playerInv.player.world.getTileEntity(buffer.readBlockPos()),((TileTeaMixer) playerInv.player.world.getTileEntity(buffer.readBlockPos())).getData());
    }

    public ContainerMixer(int id, PlayerInventory playerInv, TileTeaMixer tile, IIntArray data) {
        super(ModContainer.MIXER_CONTAINER.get(), id);
        this.tile = tile;
        createTileInv();
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

    private void createTileInv() {
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inv ->{
          addSlot(new SlotItemHandler(inv,0,35,8));
          addSlot(new SlotItemHandler(inv,1,61,8));
          addSlot(new SlotItemHandler(inv,2,99,8));
          addSlot(new SlotItemHandler(inv,3,125,8));
          addSlot(new SlotItemHandler(inv,4,80,50));
        });
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            if (index < 5) {
                if (!mergeItemStack(slot.getStack(), 5, 41, true)) {
                    return ItemStack.EMPTY;
                }
            } else{
                if(!mergeItemStack(slot.getStack(),0,3,false)){
                    return ItemStack.EMPTY;
                }
            }

        }
        return slot.getStack();
    }
}