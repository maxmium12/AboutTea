package com.maximum.abouttea.tile.machine;

import com.maximum.abouttea.api.recipes.ITeaStoneCraftingTableRecipe;
import com.maximum.abouttea.gui.ContainerTeaStoneWorkStaion;
import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileMachineBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class TileTeaStoneWorkstation extends TileMachineBase implements INamedContainerProvider {
    public TileTeaStoneWorkstation() {
        super(ModTiles.TEA_STONE_WORKSTATION.get(), 24000, 800, 0, false, true);
    }
    private ITeaStoneCraftingTableRecipe currentRecipe;
    private int ticks = 0;
    private IIntArray data=new IIntArray() {
        @Override
        public int get(int index) {
            return ticks;
        }

        @Override
        public void set(int index, int value) {
            ticks = value;
        }

        @Override
        public int size() {
            return 1;
        }
    };
    @Override
    public void doWork() {
        if(ticks >= currentRecipe.getTicks()){
            ItemStack currentOutput = inv.getStackInSlot(9).copy();
            if(currentOutput.getMaxStackSize() <= currentOutput.getCount()) return;
            for(int i = 0;i < 9;i++){
                inv.extractItem(i, 1, false);
            }
            inv.insertItem(9, currentRecipe.getRecipeOutput(), false);
            ticks = 0;
        }else {
            if(energy >= 40){
                energy-=40;
            }else {
                return;
            }
            ticks++;
        }
    }

    @Override
    public boolean canWork() {
        ITeaStoneCraftingTableRecipe recipe = findRecipe(new RecipeWrapper(inv));
        if(recipe != null) {
            currentRecipe = recipe;
            return true;
        }
        return false;
    }

    @Override
    public void stop() {
        ticks = 0;
    }
    private ITeaStoneCraftingTableRecipe findRecipe(RecipeWrapper wrapper){
        List<ITeaStoneCraftingTableRecipe> recipes = world.getRecipeManager().getRecipes(ModRecipeType.TEA_STONE_WORKSTATION_RECIPE).values().stream()
                .filter(r -> r instanceof ITeaStoneCraftingTableRecipe)
                .map(r -> (ITeaStoneCraftingTableRecipe)r)
                .collect(Collectors.toList());
        for(ITeaStoneCraftingTableRecipe recipe:recipes){
            if (recipe.matches(wrapper,world)){
                return recipe;
            }
        }
        return null;
    }
    @Override
    public int getInvSize() {
        return 10;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("abouttea.teastoneworkstation.title");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
        return new ContainerTeaStoneWorkStaion(id, playerInv, this, data);
    }
}
