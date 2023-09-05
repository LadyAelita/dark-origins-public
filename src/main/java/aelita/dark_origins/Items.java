package aelita.dark_origins;

import aelita.dark_origins.item.SunscreenBottle;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
	public static final DeferredRegister<Item> REGISTRY
		= DeferredRegister.create(ForgeRegistries.ITEMS, DarkOriginsMod.MOD_ID);

	public static final RegistryObject<Item> SUNSCREEN_BOTTLE = REGISTRY.register(SunscreenBottle.ID, SunscreenBottle.factory);
}
