package com.maximum.abouttea.block.machine;

import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.machine.TileMachineDryer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockMachineDryer extends Block {
    public BlockMachineDryer() {
        super(Properties.create(Material.IRON));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileMachineDryer();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isRemote){
            return ActionResultType.SUCCESS;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if(!player.isSneaking() && tileentity instanceof TileMachineDryer){
            NetworkHooks.openGui((ServerPlayerEntity) player, (TileMachineDryer) tileentity, buffer -> {
                buffer.writeBlockPos(pos);
                buffer.writeBlockPos(pos);
            });
            LOGGER.info("test");
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
