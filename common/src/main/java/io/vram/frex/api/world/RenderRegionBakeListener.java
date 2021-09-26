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

package io.vram.frex.api.world;

import java.util.List;
import java.util.function.Predicate;

import io.vram.frex.impl.world.RenderRegionBakeListenerImpl;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface RenderRegionBakeListener {
	void bake(RenderRegionContext context, BlockStateRenderer blockStateRenderer);

	static void register(Predicate<? super RenderRegionContext> predicate, RenderRegionBakeListener listener) {
		RenderRegionBakeListenerImpl.register(predicate, listener);
	}

	/**
	 * For use by renderer implementations.  Implementations are responsible for providing a thread-safe list
	 * instance and if populated, invoking all listeners in the list at the appropriate time. Renderer must
	 * also clear the list instance if needed before calling.
	 */
	static void prepareInvocations(RenderRegionContext context, List<RenderRegionBakeListener> listeners) {
		RenderRegionBakeListenerImpl.prepareInvocations(context, listeners);
	}

	public interface RenderRegionContext {
		/**
		 * Not available until chunk baking.  Predicate tests must
		 * be done based on block position only.
		 */
		@Nullable BlockAndTintGetter blockView();

		/**
		 * Min position (inclusive) of the area being built.
		 * The region backing {@link #blockView()} will typically have some padding
		 * extending beyond this.
		 */
		BlockPos origin();

		/**
		 * Size of the area being built, on x-axis,, including the origin.
		 * The region backing {@link #blockView()} will typically have some padding
		 * extending beyond this.
		 *
		 * <p>In vanilla, regions are consistently 16x16x16.  A renderer mod
		 * could change region size so this should not be assumed.
		 */
		default int xSize() {
			return 16;
		}

		/**
		 * Size of the area being built, on y-axis,, including the origin.
		 * The region backing {@link #blockView()} will typically have some padding
		 * extending beyond this.
		 *
		 * <p>In vanilla, regions are consistently 16x16x16.  A renderer mod
		 * could change region size so this should not be assumed.
		 */
		default int ySize() {
			return 16;
		}

		/**
		 * Size of the area being built, on z-axis, including the origin.
		 * The region backing {@link #blockView()} will typically have some padding
		 * extending beyond this.
		 *
		 * <p>In vanilla, regions are consistently 16x16x16.  A renderer mod
		 * could change region size so this should not be assumed.
		 */
		default int zSize() {
			return 16;
		}
	}

	@FunctionalInterface
	public interface BlockStateRenderer {
		void bake(BlockPos pos, BlockState state);
	}
}