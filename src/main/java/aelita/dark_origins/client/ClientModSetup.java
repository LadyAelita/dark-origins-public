package aelita.dark_origins.client;

import aelita.dark_origins.DarkOriginsMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DarkOriginsMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModSetup {
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		// TODO: Make Fluids translucent here. Or not
	}
}
