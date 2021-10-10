/*
 * Copyright © Contributing Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional copyright and licensing notices may apply for content that was
 * included from other projects. For more information, see ATTRIBUTION.md.
 */

package io.vram.frex.impl.texture;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlas.Preparations;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import io.vram.frex.api.texture.SpriteFinder;
import io.vram.frex.base.renderer.util.ResourceCache;

public class SpriteIndex {
	private static final Object2ObjectOpenHashMap<ResourceLocation, SpriteIndex> MAP = new Object2ObjectOpenHashMap<>(64, Hash.VERY_FAST_LOAD_FACTOR);

	public static final SpriteIndex getOrCreate(ResourceLocation id) {
		return MAP.computeIfAbsent(id, SpriteIndex::new);
	}

	private ObjectArrayList<TextureAtlasSprite> spriteIndexList = null;
	private TextureAtlas atlas;
	private ResourceCache<SpriteFinder> spriteFinder;
	private int atlasWidth;
	private int atlasHeight;
	public final ResourceLocation id;

	private SpriteIndex(ResourceLocation id) {
		this.id = id;
		spriteFinder = new ResourceCache<>(this::loadSpriteFinder);
	}

	private SpriteFinder loadSpriteFinder() {
		return SpriteFinder.get(atlas);
	}

	public void reset(Preparations dataIn, ObjectArrayList<TextureAtlasSprite> spriteIndexIn, TextureAtlas atlasIn) {
		atlas = atlasIn;

		spriteIndexList = spriteIndexIn;
		atlasWidth = ((TextureAtlasPreparationExt) dataIn).frex_atlasWidth();
		atlasHeight = ((TextureAtlasPreparationExt) dataIn).frex_atlasHeight();
	}

	public TextureAtlasSprite fromIndex(int spriteId) {
		return spriteIndexList.get(spriteId);
	}

	public float mapU(int spriteId, float unmappedU) {
		final TextureAtlasSprite sprite = spriteIndexList.get(spriteId);
		final float u0 = sprite.getU0();
		return u0 + unmappedU * (sprite.getU1() - u0);
	}

	public float mapV(int spriteId, float unmappedV) {
		final TextureAtlasSprite sprite = spriteIndexList.get(spriteId);
		final float v0 = sprite.getV0();
		return v0 + unmappedV * (sprite.getV1() - v0);
	}

	public int atlasWidth() {
		return atlasWidth;
	}

	public int atlasHeight() {
		return atlasHeight;
	}

	public TextureAtlas atlas() {
		return atlas;
	}

	public SpriteFinder spriteFinder() {
		return spriteFinder.getOrLoad();
	}
}
