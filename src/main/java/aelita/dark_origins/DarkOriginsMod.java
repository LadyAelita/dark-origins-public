package aelita.dark_origins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DarkOriginsMod.MOD_ID)
public class DarkOriginsMod {
	public static final String MOD_ID = "aelitas_dark_origins";
	public static final Logger LOGGER = LogManager.getLogger(DarkOriginsMod.MOD_ID);

	public DarkOriginsMod() {
		initRegistries();
	}

	private void initRegistries() {
		// All registries must be hooked up this way
		Items.REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
