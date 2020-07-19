package com.maximum.abouttea.client.render;

import com.maximum.abouttea.item.ItemTea;
import com.maximum.abouttea.item.ItemTeaCup;
import com.maximum.abouttea.tile.TileTeaSet;
import com.maximum.abouttea.util.RenderUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.items.CapabilityItemHandler;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
//Testing
public class RenderTeaSet extends TileEntityRenderer<TileTeaSet> {
    private final ResourceLocation water=Fluids.WATER.getAttributes().getStillTexture();
    public RenderTeaSet(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaSet tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        BlockPos pos=tileEntityIn.getPos();
        matrixStackIn.translate(pos.getX()+0.5f,pos.getY(),pos.getZ()+0.5f);
        //渲染一个杯子

        tileEntityIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
            Item tea=ItemTeaCup.getTea(iItemHandler.getStackInSlot(0)).getItem();
            ItemRenderer renderer= Minecraft.getInstance().getItemRenderer();
            renderer.renderItem(new ItemStack(Items.PUMPKIN), ItemCameraTransforms.TransformType.FIXED,1,1,matrixStackIn,bufferIn);
            if(tea instanceof ItemTea){
                int color= ((ItemTea)tea).getColor();
                //TODO 渲染杯子本身
                //渲染杯里的茶水
                TextureAtlasSprite sprite= RenderUtil.getSpite(water);
                IVertexBuilder builder=bufferIn.getBuffer(RenderType.getWaterMask());
                matrixStackIn.translate(0,1.3,0);
                //染色
                float red = (float)(color >> 16 & 255) / 255.0F;
                float blue = (float)(color >> 8 & 255) / 255.0F;
                float green = (float)(color & 255) / 255.0F;
                Vector3f[] vertexes=new Vector3f[50];
                ArrayList<Pair<Vector3f, Vec2f>> list=RenderUtil.getCircularVertexUV(vertexes,0.25f);
                for(Pair<Vector3f,Vec2f> vertex:list){
                    Vector3f vector3f=vertex.getLeft();
                    Vec2f vec2f=vertex.getRight();
                    builder.pos(matrixStackIn.getLast().getMatrix(),vector3f.getX(),vector3f.getY(),vector3f.getZ()).tex(vec2f.x,vec2f.y).color(red,green,blue,1f).endVertex();
                    }
                }
        });
        matrixStackIn.pop();
    }
}
