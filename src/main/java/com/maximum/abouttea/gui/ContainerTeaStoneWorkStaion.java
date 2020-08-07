package com.maximum.abouttea.gui;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.manual.TileTeaMixer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;

import javax.annotation.Nullable;

public class ContainerTeaStoneWorkStaion extends Container {
    private final TileTeaMixer tile;
    public ContainerTeaStoneWorkStaion(int id, PlayerInventory playerInv, PacketBuffer buffer){
        this(id, playerInv, (TileTeaMixer) playerInv.player.world.getTileEntity(buffer.readBlockPos()),((TileTeaMixer) playerInv.player.world.getTileEntity(buffer.readBlockPos())).getData());
    }

    protected ContainerTeaStoneWorkStaion(int id, PlayerInventory playerInv, TileTeaMixer tile, IIntArray data) {
        super(ModContainer.TEA_STONE_WORKSTATION.get(), id);
        this.tile = tile;
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
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
