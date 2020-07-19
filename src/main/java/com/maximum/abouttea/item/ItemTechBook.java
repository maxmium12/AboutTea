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

public class ItemTechBook extends Item {
    public ItemTechBook() {
        super(new Properties().group(ModGroup.GROUP));
    }
}
