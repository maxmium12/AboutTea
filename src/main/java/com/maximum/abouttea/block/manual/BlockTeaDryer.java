package com.maximum.abouttea.block.manual;


import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.tile.manual.TileTeaDryer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTeaDryer extends Block {
    public BlockTeaDryer() {
        super(Properties.create(Material.WOOD).notSolid().hardnessAndResistance(2.0f));
    }
    private static final VoxelShape shape;

    static {
        VoxelShape base = Block.makeCuboidShape(0, 0, 0, 16, 1, 16);
        VoxelShape column1 = Block.makeCuboidShape(0, 1, 0, 15, 8, 1);
        VoxelShape column2 = Block.makeCuboidShape(15, 1, 0, 16, 8, 15);
        VoxelShape column3 = Block.makeCuboidShape(0, 1, 15, 15, 8, 16);
        VoxelShape column4 = Block.makeCuboidShape(0, 1, 1, 1, 8, 16);
        shape = VoxelShapes.or(base, column1, column2, column3, column4);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileTeaDryer();
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileTeaDryer dryer = (TileTeaDryer) worldIn.getTileEntity(pos);
            if(dryer != null){
                if(!player.isSneaking()){
                    if(dryer.addItem(player.getHeldItem(handIn),player,handIn)) {
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.FAIL;
                }else {
                    if(dryer.extractItem(player.getHeldItem(handIn),player,handIn)){
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.FAIL;
                }
            }
        }
        return ActionResultType.PASS;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }
}
