package com.maximum.abouttea.tile;

import com.maximum.abouttea.init.ModTiles;
import net.minecraft.tileentity.TileEntityType;

public class TileTeaGenerator extends TileMachineBase{
    public TileTeaGenerator() {
        super(ModTiles.TEA_GENERATOR_TILE.get(), 128000, 0, 64, true, false);
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
        return 1;
    }
}
