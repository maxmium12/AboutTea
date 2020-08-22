package com.maximum.abouttea.block;

import com.google.common.collect.Maps;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.tile.TileWire;
import com.maximum.abouttea.util.network.SimpleEnergyNetwork;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;

/*
 * 来自4z大佬的教程
 */
public class BlockWire extends Block {
    public static final Map<Direction, BooleanProperty> PROPERTY_MAP;

    static {
        Map<Direction, BooleanProperty> map = Maps.newEnumMap(Direction.class);
        map.put(Direction.NORTH, BlockStateProperties.NORTH);
        map.put(Direction.EAST, BlockStateProperties.EAST);
        map.put(Direction.SOUTH, BlockStateProperties.SOUTH);
        map.put(Direction.WEST, BlockStateProperties.WEST);
        map.put(Direction.UP, BlockStateProperties.UP);
        map.put(Direction.DOWN, BlockStateProperties.DOWN);
        PROPERTY_MAP = Collections.unmodifiableMap(map);
    }

    public BlockWire() {
        super(Properties.create(Material.ROCK).notSolid());
    }

    @Override
    public boolean hasTileEntity(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return ModTiles.WIRE_TILE.get().create();
    }

    @Override
    protected void fillStateContainer(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROPERTY_MAP.values().toArray(new IProperty<?>[0]));
        super.fillStateContainer(builder);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull BlockItemUseContext context) {
        BlockState state = this.getDefaultState();
        for (Direction facing : Direction.values()) {
            World world = context.getWorld();
            BlockPos facingPos = context.getPos().offset(facing);
            BlockState facingState = world.getBlockState(facingPos);
            state = state.with(PROPERTY_MAP.get(facing), this.canConnect(world, facing.getOpposite(), facingPos, facingState));
        }
        return state;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld world, @Nonnull BlockPos pos, @Nonnull BlockPos facingPos) {
        return state.with(PROPERTY_MAP.get(facing), this.canConnect(world, facing.getOpposite(), facingPos, facingState));
    }

    private boolean canConnect(@Nonnull IWorld world, @Nonnull Direction facing, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (!state.getBlock().equals(ModBlock.blockWire.get())) {
            TileEntity tileEntity = world.getTileEntity(pos);
            return tileEntity != null && tileEntity.getCapability(CapabilityEnergy.ENERGY, facing).isPresent();
        }
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Block fromBlock, @Nonnull BlockPos fromPos, boolean isMoving) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileWire) {
                SimpleEnergyNetwork.Factory.get(world).enableBlock(pos, te::markDirty);
            }
        }
    }

}
