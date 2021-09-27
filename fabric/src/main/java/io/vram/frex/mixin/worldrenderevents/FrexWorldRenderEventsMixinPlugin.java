package io.vram.frex.mixin.worldrenderevents;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.FabricLoader;

public class FrexWorldRenderEventsMixinPlugin implements IMixinConfigPlugin {
	private final int packagePrefixLen = "io.vram.frex.mixin.worldrenderevents.".length();

	@SuppressWarnings("unused")
	private final Logger log = LogManager.getLogger("FREX");

	private final boolean isFabricPresent = FabricLoader.getInstance().isModLoaded("fabric-rendering-v1");

	@Override
	public void onLoad(String mixinPackage) {
		// NOOP
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		final var className = mixinClassName.substring(packagePrefixLen);

		if (className.equals("MixinLevelRendererEvents")) {
			// our event handlers only get loaded when Fabric's are not
			return !isFabricPresent;
		} else {
			// all the others are for fabric compat
			return isFabricPresent;
		}
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// NOOP
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NOOP
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NOOP
	}
}