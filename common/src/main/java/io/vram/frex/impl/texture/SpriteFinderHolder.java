/*
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License.  You may obtain a copy
 *  of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */

package io.vram.frex.impl.texture;

import net.minecraft.client.renderer.texture.TextureAtlas;

import io.vram.frex.api.texture.SpriteFinder;

public class SpriteFinderHolder {
	// When Fabric API is present, this is changed via Mixin to use the fabric variant
	public static SpriteFinder get(TextureAtlas atlas) {
		return SpriteFinderImpl.get(atlas);
	}
}
