package aelita.dark_origins.server.events;

import aelita.dark_origins.DarkOriginsMod;
import aelita.dark_origins.origin.OriginsHelper;
import io.github.edwinmindcraft.origins.api.capabilities.IOriginContainer;
import io.github.edwinmindcraft.origins.api.origin.Origin;
import io.github.edwinmindcraft.origins.api.origin.OriginLayer;
import io.github.edwinmindcraft.origins.common.registry.OriginRegisters;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DarkOriginsMod.MOD_ID)
public class DimensionEventSubscriber {
	private static final ResourceLocation SYNC_LAYER_ID = new ResourceLocation(DarkOriginsMod.MOD_ID, "sync_fix/sync_fix_layer");
	private static final ResourceKey<OriginLayer> SYNC_LAYER_KEY = OriginsHelper.getOriginLayerKey(SYNC_LAYER_ID);

	private static final ResourceLocation SYNC_ORIGIN_ID = new ResourceLocation(DarkOriginsMod.MOD_ID, "sync_fix/sync_fix_origin");
	private static final ResourceKey<Origin> SYNC_ORIGIN_KEY = OriginsHelper.getOriginKey(SYNC_ORIGIN_ID);

	/**
	 * Origins gets all messed up when a player goes through a portal.
	 * It's very likely, that the client somehow doesn't correcty receive NBTs
	 *  with players' Origins data - it becomes just an empty {}.
	 * This is a hacky, yet surprisingly non-invasive workaround, that triggers
	 *  said data to be updated on the client, and all is well with the world ^^
	 * You're such a smart girl, Aelita <3
	 */
	@SubscribeEvent
	public static void onTravelToDimension(EntityTravelToDimensionEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof ServerPlayer serverPlayer) {
			IOriginContainer playerOrigins = OriginsHelper.getPlayerOriginContainer(serverPlayer);
			if (playerOrigins == null) {
				return;
			}

			// Trigger re-sync by removing and adding an origin *dedicated*
			//  for that purpose, so that the actual origins don't get
			//  messed up
			DarkOriginsMod.LOGGER.debug(String.format("Triggering Origins re-sync for player \"%s\" (%s)", serverPlayer.getName().getString(), serverPlayer.getStringUUID()));
			playerOrigins.setOrigin(SYNC_LAYER_KEY, OriginRegisters.EMPTY.getKey());
			playerOrigins.setOrigin(SYNC_LAYER_KEY, SYNC_ORIGIN_KEY);
		}
	}
}
