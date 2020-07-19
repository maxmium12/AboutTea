package com.maximum.abouttea.block;

import com.maximum.abouttea.init.ModContainer;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileTeaSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockTeaSet extends Block {
    public BlockTeaSet() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(1f));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileTeaSet();
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        NetworkHooks.openGui((ServerPlayerEntity)player,new SimpleNamedContainerProvider((i,inv,entity)->ModContainer.containerteaset.get().create(i,inv),new TranslationTextComponent("a.b.c")));
        return ActionResultType.PASS;
    }
}
