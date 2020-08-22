package com.maximum.abouttea.tile.manual;

import com.maximum.abouttea.api.recipes.IMixerRecipe;
import com.maximum.abouttea.gui.ContainerMixer;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.tile.TileBase;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileTeaMixer extends TileBase implements ITickableTileEntity, INamedContainerProvider {
    private int tick = 0;
    private int hasFire = 0;
    private int maxTick;
    private IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return tick;
                case 1:
                    return hasFire;
                case 2:
                    return maxTick;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    tick = value;
                case 1:
                    hasFire = value;
                case 2:
                    maxTick = value;
            }
        }

        @Override
        public int size() {
            return 3;
        }
    };

    public TileTeaMixer() {
        super(ModTiles.MANUAL_TEA_MIXER_TILE.get());
    }

    public TileTeaMixer(TileEntityType<?> type) {
        super(type);
    }

    public static IMixerRecipe findRecipe(RecipeManager manager, ItemStack[] inputs) {
        for (IRecipe<?> recipe : manager.getRecipes(ModRecipeType.MIXER_RECIPE).values()) {
            if (recipe instanceof IMixerRecipe) {
                if (((IMixerRecipe) recipe).matches(inputs)) {
                    return (IMixerRecipe) recipe;
                }
            }
        }
        return null;
    }

    @Override
    public int getInvSize() {
        return 5;
    }

    public IIntArray getData() {
        return data;
    }

    @Override
    public void tick() {
        if (world.getBlockState(pos.down()).getBlock() instanceof FireBlock) {
            hasFire = 1;
            IMixerRecipe recipe = findRecipe();
            if (canWork(recipe)) {
                maxTick = recipe.getTicks();
                if (tick >= recipe.getTicks()) {
                    if (inv.insertItem(4, recipe.getRecipeOutput(), true).isEmpty()) {
                        for (Ingredient input : recipe.getInputs()) {
                            for (int i = 0; i < inv.getSlots() - 1; i++) {
                                if (input.test(inv.getStackInSlot(i))) {
                                    inv.extractItem(i, 1, false);
                                    break;
                                }
                            }
                        }
                        inv.insertItem(4, recipe.getRecipeOutput(), false);
                        tick = 0;
                        markDirty();
                    }
                } else {
                    tick++;
                    return;
                }
            } else {
                tick = 0;
                return;
            }
            ;
        } else hasFire = 0;
    }

    private IMixerRecipe findRecipe() {
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (!inv.getStackInSlot(i).isEmpty()) items.add(inv.getStackInSlot(i));
        }
        return findRecipe(world.getRecipeManager(), items.toArray(new ItemStack[0]));
    }

    private boolean canWork(IMixerRecipe recipe) {
        if (recipe != null && hasFire == 1) {
            ItemStack stack = recipe.getRecipeOutput();
            if ((stack.getItem() instanceof ItemTea && ((ItemTea) stack.getItem()).getTier() <= 1)) {
                return true;
            } else if (!(stack.getItem() instanceof ItemTea) && !stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("魔法锅");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerinv, PlayerEntity player) {
        return new ContainerMixer(id, playerinv, this, data);
    }
}
