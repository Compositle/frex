package io.vram.frex.api.rendertype;

import io.vram.frex.api.material.MaterialFinder;
import io.vram.frex.api.material.RenderMaterial;
import io.vram.frex.impl.material.RenderTypeUtilImpl;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

import net.minecraft.client.renderer.RenderType;

@NonExtendable
public interface RenderTypeUtil {
	static boolean toMaterialFinder(MaterialFinder finder, RenderType renderType) {
		return RenderTypeUtilImpl.toMaterialFinder(finder, renderType);
	}

	static RenderMaterial toMaterial(RenderType renderType) {
		return toMaterial(renderType, false);
	}

	static RenderMaterial toMaterial(RenderType renderType, boolean foilOverlay) {
		return RenderTypeUtilImpl.toMaterial(renderType, foilOverlay);
	}
}
