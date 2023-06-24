package aelita.dark_origins.origin;

import java.util.Optional;

import javax.annotation.Nullable;

import io.github.apace100.origins.Origins;
import io.github.edwinmindcraft.origins.api.OriginsAPI;
import io.github.edwinmindcraft.origins.api.capabilities.IOriginContainer;
import io.github.edwinmindcraft.origins.api.origin.Origin;
import io.github.edwinmindcraft.origins.api.origin.OriginLayer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class OriginsHelper {
	public static @Nullable OriginLayer getOriginsLayer(Registry<OriginLayer> layerRegistry, ResourceLocation originLayerId) {
		return layerRegistry.get(originLayerId);
	}
	public static @Nullable OriginLayer getOriginsLayer(MinecraftServer server, ResourceLocation originLayerId) {
		return getOriginsLayer(OriginsAPI.getLayersRegistry(server), originLayerId);
	}
	public static @Nullable OriginLayer getOriginsLayer(ResourceLocation originLayerId) {
		return getOriginsLayer(OriginsAPI.getLayersRegistry(), originLayerId);
	}
	public static @Nullable OriginLayer getOriginsLayer(Player player, ResourceLocation originLayerId) {
		MinecraftServer server = player.getServer();
		if (server != null) {
			return getOriginsLayer(server, originLayerId);
		} else {
			return getOriginsLayer(originLayerId);
		}
	}

	public static @Nullable ResourceKey<Origin> getPlayerOriginKey(Player player, ResourceLocation originLayerId) {
		OriginLayer layer = getOriginsLayer(player, originLayerId);
		if (layer == null) {
			// TODO: Log warning here?
			return null;
		}
		IOriginContainer origins = getPlayerOriginContainer(player);
		if (origins == null) {
			// TODO: Log warning here?
			return null;
		}
		return origins.getOrigin(layer);
	}
	public static @Nullable ResourceKey<Origin> getPlayerOriginKey(Player player) {
		return getPlayerOriginKey(player, new ResourceLocation(Origins.MODID, "origin"));
	}

	public static @Nullable ResourceLocation getPlayerOriginId(Player player, ResourceLocation originLayerId) {
		ResourceKey<Origin> key = getPlayerOriginKey(player, originLayerId);
		if (key == null) {
			return null;
		}
		return key.location();
	}
	public static @Nullable ResourceLocation getPlayerOriginId(Player player) {
		return getPlayerOriginId(player, new ResourceLocation(Origins.MODID, "origin"));
	}

	public static @Nullable IOriginContainer getPlayerOriginContainer(Player player) {
		Optional<IOriginContainer> containerOptional = player.getCapability(OriginsAPI.ORIGIN_CONTAINER).resolve();
		if (containerOptional.isEmpty()) {
			return null;
		}
		return containerOptional.get();
	}
}
