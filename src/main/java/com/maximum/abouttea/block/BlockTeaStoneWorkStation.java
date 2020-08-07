package com.maximum.abouttea.block;

import com.maximum.abouttea.capabilities.AboutTeaCap;
import com.maximum.abouttea.capabilities.CapabilityHandler;
import com.maximum.abouttea.capabilities.IAboutTeaCap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BlockTeaStoneWorkStation extends Block {

    public BlockTeaStoneWorkStation() {
        super(Properties.create(Material.ROCK).lightValue(10));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            if(!player.getCapability(CapabilityHandler.ABOUTTEACAP).isPresent()){
                player.sendStatusMessage(new TranslationTextComponent("abouttea.teastoneworkstation.fail"), false);
                return ActionResultType.FAIL;
            }
            final IAboutTeaCap[] capability = new IAboutTeaCap[1];
            player.getCapability(CapabilityHandler.ABOUTTEACAP).ifPresent(cap -> {
                capability[0] = cap;
            });
            if(capability[0].isUnlock()){

            }
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }
}
