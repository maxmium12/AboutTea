package com.maximum.abouttea.util;

import net.minecraft.util.IIntArray;

public class EnergyArray implements IIntArray {
    private int ticks;
    private int energy;
    public EnergyArray(int ticks, int energy){
        this.ticks = ticks;
        this.energy = energy;
    }
    @Override
    public int get(int index) {
        switch (index){
            case 0:return ticks;
            case 1:return energy;
        }
        return 0;
    }

    @Override
    public void set(int index, int value) {
        switch (index){
            case 0:ticks = value;
            case 1:energy = value;
        }
    }

    @Override
    public int size() {
        return 2;
    }
}
