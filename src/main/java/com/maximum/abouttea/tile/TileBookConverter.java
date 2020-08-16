package com.maximum.abouttea.tile;

import com.maximum.abouttea.AboutTea;
import com.maximum.abouttea.block.BlockTeaBookConverter;
import com.maximum.abouttea.init.ModBlock;
import com.maximum.abouttea.init.ModItems;
import com.maximum.abouttea.init.ModTiles;
import com.maximum.abouttea.item.ItemTeaBook;
import com.maximum.abouttea.item.ItemTeaStone;
import com.maximum.abouttea.item.ItemTechBook;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;


public class TileBookConverter extends TileEntity implements ITickableTileEntity{
    private long ticks=0;
    private boolean canConvert=false;
    private final LazyValue<IMultiblock> multiBlock=new LazyValue<>(()-> PatchouliAPI.instance.makeMultiblock(
            new String[][]{{
                    "___",
                    "_P_",
                    "___"
            },
            {
                    "GLG",
                    "L0L",
                    "GLG"
            }
    },'P', ModBlock.blockTeaBookConverter.get(),
            'G', Blocks.LAPIS_BLOCK,
            'L',ModBlock.blockTeaBlock.get(),
            '0',Blocks.LAPIS_BLOCK));
    public TileBookConverter() {
        super(ModTiles.TEA_BOOK_CONVERTER_TILE.get());
    }

    @Override
    public void tick() {
        if(multiBlock.getValue().validate(world,pos.down())!=null){
            List<ItemEntity> items=getItems();
            if(areItemValid(items) && world.getBlockState(pos).propagatesSkylightDown(world,pos)){
                if(ticks>=40){
                    canConvert=true;
                    if(!world.getBlockState(pos).get(BlockTeaBookConverter.CONVERT)){
                        BlockState state = world.getBlockState(pos).cycle(BlockTeaBookConverter.CONVERT);
                        world.setBlockState(pos, state, 3);
                    }
                    return;
                }
                ticks++;
                return;
            }else {
                setDefault();
                return;
            }
        }else {
            setDefault();
        }
    }
    public List<ItemEntity> getItems(){
        return world.getEntitiesWithinAABB(ItemEntity.class,new AxisAlignedBB(pos,pos.add(1,1,1)));
    }
    private void setDefault(){
        ticks=0;
        canConvert=false;
        if(world.getBlockState(pos).get(BlockTeaBookConverter.CONVERT)){
            world.getBlockState(pos).cycle(BlockTeaBookConverter.CONVERT);
        }
    }
    private boolean areItemValid(List<ItemEntity> items){
        if(items.size()!=3){
            return false;
        }
        ItemStack stone=ItemStack.EMPTY;
        ItemStack book=ItemStack.EMPTY;
        ItemStack unlockbook=ItemStack.EMPTY;
        for(ItemEntity item:items){
            ItemStack stack=item.getItem();
            if(stack.getItem() instanceof ItemTeaStone){
                if(stack.getCount()!=3){
                    return false;
                }
                stone=stack;
            }
            if(stack.getItem() instanceof ItemTeaBook){
                if(stack.getCount()!=1){
                    return false;
                }
                book=stack;
            }
            if(stack.getItem() instanceof ItemTechBook){
                if(stack.getCount()!=1){
                    return false;
                }
                unlockbook=stack;
            }
        }
        return !stone.isEmpty()&&!book.isEmpty()&&!unlockbook.isEmpty();
    }
}
