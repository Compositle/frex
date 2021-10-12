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

package io.vram.frex.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import io.vram.frex.impl.texture.TextureAtlasPreparationExt;

@Mixin(TextureAtlas.Preparations.class)
public class MixinTextureAtlasPreparation implements TextureAtlasPreparationExt {
	@Shadow int width;
	@Shadow int height;
	@Shadow List<TextureAtlasSprite> regions;

	@Override
	public int frex_atlasWidth() {
		return width;
	}

	@Override
	public int frex_atlasHeight() {
		return height;
	}

	@Override
	public List<TextureAtlasSprite> frex_sprites() {
		return regions;
	}
}
