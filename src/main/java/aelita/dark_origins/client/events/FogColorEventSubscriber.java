package aelita.dark_origins.client.events;

import aelita.dark_origins.DarkOriginsMod;
import aelita.dark_origins.origin.OriginLocations;
import aelita.dark_origins.origin.OriginsHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent.ComputeFogColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DarkOriginsMod.MOD_ID, value = Dist.CLIENT)
public class FogColorEventSubscriber
{
	@SubscribeEvent
	public static void onComputeFogColor(ComputeFogColor event) {
		Minecraft mc = Minecraft.getInstance();
		ClientLevel level = mc.level;
		if (level == null) {
			return;
		}
		LocalPlayer player = mc.player;
		if (player == null) {
			return;
		}
		
		/* Origin: Vampire */
		if (OriginsHelper.hasPlayerOrigin(player, OriginLocations.VAMPIRE)) {
			// Vampires hate light, so when they go blind, it's white instead of dark
			if (player.hasEffect(MobEffects.BLINDNESS)) {
				event.setRed(1.0f);
				event.setGreen(1.0f);
				event.setBlue(1.0f);
			}	
		}
	}
}
