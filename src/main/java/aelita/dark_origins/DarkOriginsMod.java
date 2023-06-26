package aelita.dark_origins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DarkOriginsMod.MOD_ID)
public class DarkOriginsMod {
	public static final String MOD_ID = "aelitas_dark_origins";
	public static final Logger LOGGER = LogManager.getLogger(DarkOriginsMod.MOD_ID);

	public DarkOriginsMod() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		initRegistries(modEventBus);
	}

	private void initRegistries(IEventBus bus) {
		// All registries must be hooked up this way
		Items.REGISTRY.register(bus);

		// Brewing recipes are kinda different
		bus.addListener(BrewingRecipes::register);
	}
}
