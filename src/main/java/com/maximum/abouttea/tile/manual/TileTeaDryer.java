package com.maximum.abouttea.tile.manual;

import com.maximum.abouttea.api.DryerRecipes;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;

public class TileTeaDryer extends TileBase implements ITickableTileEntity {
    private final int[] ticks=new int[4];
    public TileTeaDryer() {
        super(ModTiles.MANUAL_TEA_DRYER_TILE.get());
    }
    public boolean addItem(ItemStack stack, PlayerEntity player, Hand hand){
        for(int i = 0;i< inv.getSlots();i++){
            if(inv.getStackInSlot(i).isEmpty()){
                inv.setStackInSlot(i,player.getHeldItem(hand));
                player.setHeldItem(hand,ItemStack.EMPTY);
                return true;
            }
        }
        return false;
    }
    @Override
    public int getInvSize() {
        return 4;
    }

    @Override
    public void tick() {
        for(int i = 0;i<inv.getSlots();i++){
            if(DryerRecipes.findRecipe(inv.getStackInSlot(i))!=null){
                DryerRecipes.Recipe recipe=DryerRecipes.findRecipe(inv.getStackInSlot(i));
                if (!(ticks[i]>=recipe.ticks)){
                    ticks[i]++;
                }else {
                    inv.setStackInSlot(i,recipe.output);
                    ticks[i]=0;
                }
            }
        }
    }
}
