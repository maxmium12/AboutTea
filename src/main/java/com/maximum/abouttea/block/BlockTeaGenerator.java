package com.maximum.abouttea.block;


import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileTeaGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class BlockTeaGenerator extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockTeaGenerator() {
        super(Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).harvestLevel(2).hardnessAndResistance(2f));
    }

    @Override
    public boolean hasTileEntity(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return ModTiles.TEA_GENERATOR_TILE.get().create();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (!player.isSneaking() && tileentity instanceof TileTeaGenerator) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (TileTeaGenerator) tileentity, buffer -> {
                buffer.writeBlockPos(pos);
                buffer.writeBlockPos(pos);
            });
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() != state.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileTeaGenerator) {
                InventoryHelper.dropInventoryItems(worldIn, pos, ((TileTeaGenerator) tileentity).getRecipeWrapper());
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
