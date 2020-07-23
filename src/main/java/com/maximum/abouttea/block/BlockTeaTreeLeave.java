package com.maximum.abouttea.block;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.api.IItemTea;
import com.maximum.abouttea.item.ItemTea;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class BlockTeaTreeLeave extends LeavesBlock {
    private static final IntegerProperty GROW= IntegerProperty.create("grow",0,3);
    private String tea;
    public BlockTeaTreeLeave(Properties properties, String tea) {
        super(properties.notSolid());
        this.tea=tea;
        this.setDefaultState(this.getStateContainer().getBaseState().with(GROW,0));
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            if(state.get(GROW)==3){
                Random random=new Random();
                int count=random.nextInt(3);
                spawnAsEntity(worldIn,pos,new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(AboutTea.MODID,tea)),count));
                state.cycle(GROW);
            }
        }
        return ActionResultType.PASS;
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GROW);
        super.fillStateContainer(builder);
    }
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if(random.nextInt(100)>=99&&state.get(GROW)<3){
            state.cycle(GROW);
        }
        super.randomTick(state,worldIn,pos,random);
    }
}
