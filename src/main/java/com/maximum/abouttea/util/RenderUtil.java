package com.maximum.abouttea.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class RenderUtil {
    public static TextureAtlasSprite getSpite(ResourceLocation location){
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(location);
    }
    /*
    * 渲染圆形
    * @return 顶点和纹理坐标的Pair
    * 注意返回的圆形顶点为x,y平面需要自己旋转
    */
    public static ArrayList<Pair<Vector3f, Vec2f>> getCircularVertexUV(Vector3f[] vertexes, float r){
        float angle=(float) (2*Math.PI/vertexes.length);
        int n =vertexes.length;
        ArrayList<Pair<Vector3f,Vec2f>> list=new ArrayList<>();
        Vec2f[] uv=new Vec2f[n];
        for(int i=0;i<=n;++i){
            vertexes[i] = new Vector3f(r* MathHelper.cos((float) (2*Math.PI)/n*i),r* MathHelper.sin((float) (2*Math.PI/n*i)),0f);
            uv[i] = new Vec2f((float)( 1.0 - ((r * MathHelper.cos(angle * i)) + 1.0)*0.5),(float) ((r * MathHelper.sin(angle * i)) + 1.0*0.5));
            list.add(new ImmutablePair<>(vertexes[i], uv[i]));
        }
        return list;
    }
}
