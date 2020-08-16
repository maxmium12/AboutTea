package com.maximum.abouttea.gui;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.TileMachineBase;
import com.maximum.abouttea.tile.machine.TileMachineDryer;
import com.maximum.abouttea.tile.machine.TileTeaStoneWorkstation;
import com.maximum.abouttea.tile.manual.TileTeaMixer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTeaStoneWorkStation extends Container {
    private final TileTeaStoneWorkstation tile;
    private final IIntArray data;
    public ContainerTeaStoneWorkStation(int id, PlayerInventory playerInv, PacketBuffer buffer){
        this(id, playerInv, (TileTeaStoneWorkstation) playerInv.player.world.getTileEntity(buffer.readBlockPos()),((TileTeaStoneWorkstation) playerInv.player.world.getTileEntity(buffer.readBlockPos())).getData());
    }

    public ContainerTeaStoneWorkStation(int id, PlayerInventory playerInv, TileTeaStoneWorkstation tile, IIntArray data) {
        super(ModContainer.TEA_STONE_WORKSTATION.get(), id);
        this.tile = tile;
        this.data = data;
        trackIntArray(data);
        addTileInv();
        createPlayerInv(playerInv);
    }
    private void addTileInv(){
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent( inv -> {
            this.addSlot(new SlotItemHandler(inv, 9,124, 35));
            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
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
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public TileTeaStoneWorkstation getTile() {
        return tile;
    }

    public IIntArray getData() {
        return data;
    }
}
