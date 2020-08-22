package com.maximum.abouttea.tile.manual;

import com.maximum.abouttea.api.recipes.IDryerRecipe;
import com.maximum.abouttea.init.ModRecipeType;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.stream.Collectors;

public class TileTeaDryer extends TileBase implements ITickableTileEntity {
    protected final int[] ticks = new int[4];

    public TileTeaDryer() {
        super(ModTiles.MANUAL_TEA_DRYER_TILE.get());
    }

    public TileTeaDryer(TileEntityType<?> type) {
        super(type);
    }

    public static IDryerRecipe findRecipe(RecipeManager manager, ItemStack input) {
        List<IDryerRecipe> recipes = manager.getRecipes(ModRecipeType.DRYER_RECIPE).values().stream()
                .filter(r -> r instanceof IDryerRecipe)
                .map(r -> (IDryerRecipe) r)
                .collect(Collectors.toList());
        for (IDryerRecipe recipe : recipes) {
            if (recipe.matches(input)) {
                return recipe;
            }
        }
        return null;
    }

    public boolean addItem(ItemStack stack, PlayerEntity player, Hand hand) {
        for (int i = 0; i < inv.getSlots(); i++) {
            if (inv.getStackInSlot(i).isEmpty()) {
                ItemStack stack1 = stack.copy();
                stack1.setCount(1);
                inv.setStackInSlot(i, stack1);
                stack.setCount(stack.getCount() - 1);
                player.setHeldItem(hand, stack);
                player.getServer().getPlayerList().sendPacketToAllPlayers(this.getUpdatePacket());
                return true;
            }
        }
        return false;
    }

    public boolean extractItem(ItemStack stack, PlayerEntity player, Hand hand) {
        for (int i = inv.getSlots() - 1; i >= 0; --i) {
            if (!inv.getStackInSlot(i).isEmpty()) {
                ItemStack stack1 = inv.getStackInSlot(i).copy();
                if (stack.isEmpty() || (stack.getItem() == stack1.getItem() && stack.getMaxStackSize() > stack.getCount())) {
                    ItemHandlerHelper.giveItemToPlayer(player, stack1);
                    inv.setStackInSlot(i, ItemStack.EMPTY);
                    player.getServer().getPlayerList().sendPacketToAllPlayers(this.getUpdatePacket());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getInvSize() {
        return 4;
    }

    protected IDryerRecipe findRecipe(ItemStack input) {
        return findRecipe(world.getRecipeManager(), input);
    }

    @Override
    public void tick() {
        for (int i = 0; i < inv.getSlots(); i++) {
            if (findRecipe(inv.getStackInSlot(i)) != null) {
                IDryerRecipe recipe = findRecipe(inv.getStackInSlot(i));
                if (!(ticks[i] >= recipe.getTicks())) {
                    ticks[i]++;
                } else {
                    inv.setStackInSlot(i, recipe.getRecipeOutput());
                    ticks[i] = 0;
                    markDirty();
                }
            }
        }
    }
}
