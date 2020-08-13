package com.maximum.abouttea.block;

import com.maximum.abouttea.init.ModTrigger;
import com.maximum.abouttea.item.ItemTeaBook;
import com.maximum.abouttea.tile.TileBookConverter;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.util.UnlockTechTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class BlockTeaBookConverter extends Block {
    public static BooleanProperty CONVERT=BooleanProperty.create("can_convert");
    public VoxelShape SHAPE=Block.makeCuboidShape(0,0,0,16,3,16) ;
    public BlockTeaBookConverter() {
        super(Properties.create(Material.IRON).hardnessAndResistance(2f).notSolid());
        this.setDefaultState(getDefaultState().with(CONVERT,false));
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(CONVERT);
        super.fillStateContainer(builder);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(state.get(CONVERT)){
            TileBookConverter tile= (TileBookConverter) worldIn.getTileEntity(pos);
            ItemStack stack=ItemStack.EMPTY;
            List<ItemEntity> items=tile.getItems();
            Iterator<ItemEntity> iterator=items.iterator();
            while (iterator.hasNext()){
                ItemEntity item=iterator.next();
                if(item.getItem().getItem() instanceof ItemTeaBook){
                    stack=item.getItem();
                }else {
                    item.remove();
                    iterator.remove();
                }
            }
            ItemTeaBook.setUnlockTech(stack,true);
            if(!worldIn.isRemote) ModTrigger.UnlockTechTrigger.trigger((ServerPlayerEntity) player, state);
            state.cycle(CONVERT);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileBookConverter();
    }
}
