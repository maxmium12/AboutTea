package com.maximum.abouttea.block.manual;

import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.tile.manual.TileTeaMixer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockTeaMixer extends Block {
    public BlockTeaMixer() {
        super(Properties.create(Material.IRON).notSolid().hardnessAndResistance(2f));
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote&&!player.isSneaking()){
            TileTeaMixer tile= (TileTeaMixer) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity)player, tile, (buffer -> {
                buffer.writeBlockPos(pos);
                buffer.writeBlockPos(pos);}));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileTeaMixer();
    }
}
