package com.maximum.abouttea.tile.manual;

import com.maximum.abouttea.api.recipes.IMixerRecipe;
import com.maximum.abouttea.gui.ContainerMixer;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.tile.TileBase;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileTeaMixer extends TileBase implements ITickableTileEntity , INamedContainerProvider {
    private int tick=0;
    private int hasFire=0;
    private IIntArray data=new IIntArray() {
        @Override
        public int get(int index) {
            switch (index){
                case 0:
                    return tick;
                case 1:
                    return hasFire;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index){
                case 0:
                    tick=value;
                case 1:
                  hasFire=value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };
    public TileTeaMixer(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    @Override
    public int getInvSize() {
        return 5;
    }
    public IIntArray getData(){
        return data;
    }
    @Override
    public void tick() {
        if(world.getBlockState(pos.down()).getBlock() instanceof FireBlock){
            hasFire=1;
            IMixerRecipe recipe=findRecipe();
            if((recipe!=null && recipe.getRecipeOutput().getItem() instanceof ItemTea && ((ItemTea)recipe.getRecipeOutput().getItem()).getTier()==1)){
                if(tick>=recipe.getTicks()){
                    if(inv.insertItem(4,recipe.getRecipeOutput(),true).isEmpty()){
                        for(int i=0;i<inv.getSlots();i++){
                            inv.extractItem(i,1,false);
                        }
                        tick=0;
                    }
                }else tick++;
            }
        }
    }
    private IMixerRecipe findRecipe(){
        List<ItemStack> items=new ArrayList<>();
        for(int i=0;i<inv.getSlots();i++){
            items.add(inv.getStackInSlot(i));
        }
        for(IRecipe<?> recipe:world.getRecipeManager().getRecipes(ModRecipeType.MIXER_RECIPE).values()){
            if(recipe instanceof IMixerRecipe){
                if(((IMixerRecipe) recipe).matches(items.toArray(new ItemStack[0]))){
                    return (IMixerRecipe) recipe;
                }
            }
        }
        return null;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("魔法锅");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerinv, PlayerEntity player) {
        return new ContainerMixer(id,playerinv,this,data);
    }
}
