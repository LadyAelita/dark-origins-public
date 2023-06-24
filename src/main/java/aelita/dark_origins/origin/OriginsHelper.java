package aelita.dark_origins.origin;

import java.util.Optional;

import javax.annotation.Nullable;

import aelita.dark_origins.util.ResourceLocationUtils;
import io.github.apace100.origins.Origins;
import io.github.edwinmindcraft.origins.api.capabilities.IOriginContainer;
import io.github.edwinmindcraft.origins.api.origin.Origin;
import io.github.edwinmindcraft.origins.api.origin.OriginLayer;
import io.github.edwinmindcraft.origins.api.registry.OriginsDynamicRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public class OriginsHelper {
	public static final ResourceLocation DEFAULT_LAYER_ID = new ResourceLocation(Origins.MODID, "origin");
	public static final ResourceKey<OriginLayer> DEFAULT_LAYER_KEY = getOriginLayerKey(DEFAULT_LAYER_ID);

	public static ResourceKey<OriginLayer> getOriginLayerKey(ResourceLocation originLayerId) {
		return ResourceKey.create(OriginsDynamicRegistries.LAYERS_REGISTRY, originLayerId);
	}

	public static ResourceKey<Origin> getOriginKey(ResourceLocation originId) {
		return ResourceKey.create(OriginsDynamicRegistries.ORIGINS_REGISTRY, originId);
	}

	public static ResourceKey<Origin> getPlayerOriginKey(Player player, ResourceLocation originLayerId) {
		IOriginContainer origins = getPlayerOriginContainer(player);
		if (origins == null) {
			// TODO: Log warning here?
			return null;
		}
		ResourceKey<OriginLayer> layerKey = getOriginLayerKey(originLayerId);
		return origins.getOrigin(layerKey);
	}
	public static @Nullable ResourceKey<Origin> getPlayerOriginKey(Player player) {
		return getPlayerOriginKey(player, DEFAULT_LAYER_ID);
	}

	public static @Nullable ResourceLocation getPlayerOriginId(Player player, ResourceLocation originLayerId) {
		ResourceKey<Origin> key = getPlayerOriginKey(player, originLayerId);
		if (key == null) {
			return null;
		}
		return key.location();
	}
	public static @Nullable ResourceLocation getPlayerOriginId(Player player) {
		return getPlayerOriginId(player, DEFAULT_LAYER_ID);
	}

	public static boolean hasPlayerOrigin(Player player, ResourceLocation originLayerId, ResourceLocation originId) {
		ResourceLocation currentOriginId = getPlayerOriginId(player, originLayerId);
		return ResourceLocationUtils.isEqual(currentOriginId, originId);
	}
	public static boolean hasPlayerOrigin(Player player, ResourceLocation originId) {
		return hasPlayerOrigin(player, DEFAULT_LAYER_ID, originId);
	}
	public static boolean hasPlayerOrigin(Player player, ResourceKey<Origin> originKey) {
		return hasPlayerOrigin(player, originKey.registry(), originKey.location());
	}

	public static @Nullable IOriginContainer getPlayerOriginContainer(Player player) {
		LazyOptional<IOriginContainer> containerLazyOptional = IOriginContainer.get(player);
		Optional<IOriginContainer> containerOptional = containerLazyOptional.resolve();
		if (containerOptional.isEmpty()) {
			return null;
		}
		IOriginContainer container = containerOptional.get();
		return container;
	}
}
