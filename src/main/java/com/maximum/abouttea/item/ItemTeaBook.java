package com.maximum.abouttea.item;

import com.maximum.abouttea.init.ModGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nonnull;

public class ItemTeaBook extends Item {
    public ItemTeaBook() {
        super(new Properties().group(ModGroup.GROUP).maxStackSize(1));
    }

    public static boolean isUnlockTech(ItemStack stack) {
        return stack.hasTag() && stack.getTag().getBoolean("unlock");
    }

    public static void setUnlockTech(ItemStack stack, boolean unlock) {
        stack.getOrCreateTag().putBoolean("unlock", unlock);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerIn;
            PatchouliAPI.instance.openBookGUI((ServerPlayerEntity) playerIn, Registry.ITEM.getKey(this));
        }
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}
