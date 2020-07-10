package com.maximum.abouttea.gui;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerTeaSet extends Container {
    public ContainerTeaSet(int id, PlayerInventory inv, PacketBuffer buffer, BlockPos pos, World world) {
        super(ModContainer.containerteaset.get(), id);
        TileTeaSet teaSet = (TileTeaSet) world.getTileEntity(pos);
        if (teaSet != null) {
            teaSet.getCupHandler().ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 50, 50));
            });
        }
        //玩家物品栏
        int xPos=8;
        int yPos=84;
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(inv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(inv, x, xPos + x * 18, yPos + 58));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}