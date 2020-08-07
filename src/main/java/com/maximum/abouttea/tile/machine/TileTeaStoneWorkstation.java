package com.maximum.abouttea.tile.machine;

import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileMachineBase;
import net.minecraft.tileentity.TileEntityType;

public class TileTeaStoneWorkstation extends TileMachineBase {
    public TileTeaStoneWorkstation() {
        super(ModTiles., 24000, 800, 0, false, true);
    }

    @Override
    public void doWork() {
        
    }

    @Override
    public boolean canWork() {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    public int getInvSize() {
        return 10;
    }
}
