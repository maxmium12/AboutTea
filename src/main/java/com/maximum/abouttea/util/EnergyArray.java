package com.maximum.abouttea.util;

import net.minecraft.util.IIntArray;

public class EnergyArray implements IIntArray {
    private int ticks;
    private int energy;
    private int maxTicks;
    public EnergyArray(int ticks, int energy, int maxTicks){
        this.ticks = ticks;
        this.energy = energy;
        this.maxTicks = maxTicks;
    }
    @Override
    public int get(int index) {
        switch (index){
            case 0:return ticks;
            case 1:return energy;
            case 2:return maxTicks;
        }
        return 0;
    }

    @Override
    public void set(int index, int value) {
        switch (index){
            case 0:
                ticks = value;
                break;
            case 1:
                energy = value;
                break;
            case 2:maxTicks = value;
        }
    }

    @Override
    public int size() {
        return 3;
    }
}
