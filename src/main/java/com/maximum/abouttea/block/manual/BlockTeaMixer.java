package com.maximum.abouttea.block.manual;

import com.maximum.abouttea.tile.manual.TileTeaMixer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockTeaMixer extends Block {
    public BlockTeaMixer() {
        super(Properties.create(Material.IRON).notSolid().hardnessAndResistance(2f));
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileTeaMixer tile= (TileTeaMixer) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity)player, tile, (buffer -> {buffer.writeBlockPos(pos);}));
        }
        return ActionResultType.PASS;
    }
}
