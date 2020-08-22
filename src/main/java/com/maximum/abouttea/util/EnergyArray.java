package com.maximum.abouttea.util;

import net.minecraft.util.IIntArray;

public class EnergyArray implements IIntArray {
    private int ticks;
    private int energy;
    private short energyPar1;
    private short energyPar2;
    private int maxTicks;

    public EnergyArray(int ticks, int energy, int maxTicks) {
        this.ticks = ticks;
        energyPar1 = (short) (energy << 16);
        energyPar2 = (short) (energy >> 16);
        this.energy = energy;
        this.maxTicks = maxTicks;
    }

    @Override
    public int get(int index) {
        switch (index) {
            case 0:
                return ticks;
            case 1:
                return energyPar1;
            case 2:
                return maxTicks;
            case 3:
                return energyPar2;
        }
        return 0;
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0:
                ticks = value;
                break;
            case 1:
                energyPar1 = (short) value;
                break;
            case 2:
                maxTicks = value;
                break;
            case 3:
                energyPar2 = (short) value;
        }
    }

    @Override
    public int size() {
        return 4;
    }

    public int getEnergy() {
        energy = energyPar1;
        energy <<= 16;
        energyPar2 |= energyPar2;
        return energy;
    }

    public void setEnergy(int energy) {
        energyPar1 = (short) (energy << 16);
        energyPar2 = (short) (energy >> 16);
        this.set(1, energyPar1);
        this.set(3, energyPar2);
    }
}
