package com.maximum.abouttea.tile.manual;

import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.api.recipes.impl.DryerRecipes;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.stream.Collectors;

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
    private IDryerRecipe findRecipe(ItemStack input){
       List<IDryerRecipe> recipes = world.getRecipeManager().getRecipes(ModRecipeType.DRYER_RECIPE).values().stream()
                                                                    .filter(r -> r instanceof IDryerRecipe)
                                                                    .map(r -> (IDryerRecipe) r)
                                                                    .collect(Collectors.toList());
       for(IDryerRecipe recipe:recipes){
           if(recipe.matches(input)){
               return recipe;
           }
       }
       return null;
    }
    @Override
    public void tick() {
        for(int i = 0;i<inv.getSlots();i++){
            if(findRecipe(inv.getStackInSlot(i))!=null){
                IDryerRecipe recipe=findRecipe(inv.getStackInSlot(i));
                if (!(ticks[i]>=recipe.getTicks())){
                    ticks[i]++;
                }else {
                    inv.setStackInSlot(i,recipe.getOutput());
                    ticks[i]=0;
                }
            }
        }
    }
}
