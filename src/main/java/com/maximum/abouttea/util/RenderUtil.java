package com.maximum.abouttea.util;

import com.google.common.collect.Lists;
import com.maximum.abouttea.effect.EffectInstanceWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.maximum.abouttea.AboutTea.prefix;

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
    //原版PotionUtils下方法，略有改动
    @OnlyIn(Dist.CLIENT)
    public static void addPotionToolTips(List<EffectInstance> list, List<ITextComponent> lores, float durationFactor){
        List<Tuple<String, AttributeModifier>> list1 = Lists.newArrayList();
        if (list.isEmpty()) {
            lores.add((new TranslationTextComponent("effect.none")).applyTextStyle(TextFormatting.GRAY));
        } else {
            for(EffectInstance effectinstance : list) {
                ITextComponent itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
                Effect effect = effectinstance.getPotion();
                Map<IAttribute, AttributeModifier> map = effect.getAttributeModifierMap();
                if (!map.isEmpty()) {
                    for(Map.Entry<IAttribute, AttributeModifier> entry : map.entrySet()) {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierAmount(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                        list1.add(new Tuple<>(entry.getKey().getName(), attributemodifier1));
                    }
                }

                if (effectinstance.getAmplifier() > 0) {
                    itextcomponent.appendText(" ").appendSibling(new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
                }

                if (effectinstance.getDuration() > 20) {
                    itextcomponent.appendText(" (").appendText(EffectUtils.getPotionDurationString(effectinstance, durationFactor)).appendText(")");
                }

                lores.add(itextcomponent.applyTextStyle(effect.getEffectType().getColor()));
            }
        }

        if (!list1.isEmpty()) {
            lores.add(new StringTextComponent(""));
            lores.add((new TranslationTextComponent("potion.whenDrank")).applyTextStyle(TextFormatting.DARK_PURPLE));

            for (Tuple<String, AttributeModifier> tuple : list1) {
                AttributeModifier attributemodifier2 = tuple.getB();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }

                if (d0 > 0.0D) {
                    lores.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent("attribute.name." + (String) tuple.getA()))).applyTextStyle(TextFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 = d1 * -1.0D;
                    lores.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent("attribute.name." + (String) tuple.getA()))).applyTextStyle(TextFormatting.RED));
                }
            }
        }
    }
}
