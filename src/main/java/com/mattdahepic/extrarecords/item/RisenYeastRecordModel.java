package com.mattdahepic.extrarecords.item;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;

import java.util.Collection;
import java.util.function.Function;

public class RisenYeastRecordModel implements IModel {
    /*
     * Returns all model locations that this model depends on.
     * Assume that returned collection is immutable.
     */
    public Collection<ResourceLocation> getDependencies();

    /*
     * Returns all texture locations that this model depends on.
     * Assume that returned collection is immutable.
     */
    public Collection<ResourceLocation> getTextures();

    /*
     * All model texture coordinates should be resolved at this method.
     * Returned model should be in the simplest form possible, for performance
     * reasons (if it's not ISmartBlock/ItemModel - then it should be
     * represented by List<BakedQuad> internally).
     * Returned model's getFormat() can me less specific than the passed
     * format argument (some attributes can be replaced with padding),
     * if there's no such info in this model.
     */
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter);

    /*
     * Default state this model will be baked with.
     * See IModelState.
     */
    public IModelState getDefaultState();
}
