package com.maximum.abouttea.tile.manual;

import com.maximum.abouttea.tile.TileBase;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileTeaMixer extends TileBase implements ITickableTileEntity {
    private int tick;
    public TileTeaMixer(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    @Override
    public int getInvSize() {
        return 4;
    }

    @Override
    public void tick() {

    }
}
