package com.maximum.abouttea.block.machine;

import com.maximum.abouttea.capabilities.AboutTeaCap;
import com.maximum.abouttea.capabilities.CapabilityHandler;
import com.maximum.abouttea.capabilities.IAboutTeaCap;
import com.maximum.abouttea.init.ModTiles;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockTeaStoneWorkStation extends Block {

    public BlockTeaStoneWorkStation() {
        super(Properties.create(Material.ROCK).lightValue(10));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            IAboutTeaCap cap = player.getCapability(CapabilityHandler.ABOUTTEACAP).orElse(new AboutTeaCap.Impl());
            if (cap.isUnlock()) {
                NetworkHooks.openGui((ServerPlayerEntity) player, ModTiles.TEA_STONE_WORKSTATION.get().create(), buffer -> {
                    buffer.writeBlockPos(pos);
                    buffer.writeBlockPos(pos);
                });
                return ActionResultType.SUCCESS;
            } else {
                player.sendStatusMessage(new TranslationTextComponent("abouttea.teastoneworkstation.fail"), false);
                return ActionResultType.FAIL;
            }
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
        return ModTiles.TEA_STONE_WORKSTATION.get().create();
    }
}
