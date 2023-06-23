package aelita.dark_origins;

import aelita.dark_origins.item.BloodItem;
import aelita.dark_origins.item.RitualDaggerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
	public static final DeferredRegister<Item> REGISTRY
		= DeferredRegister.create(ForgeRegistries.ITEMS, DarkOriginsMod.MOD_ID);

	public static final RegistryObject<Item> BLOOD = REGISTRY.register(BloodItem.ID, BloodItem.factory);
	public static final RegistryObject<Item> RITUAL_DAGGER = REGISTRY.register(RitualDaggerItem.ID, RitualDaggerItem.factory);

	private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block) {
		final var blockName = block.getId().getPath();
		return REGISTRY.register(blockName, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block, CreativeModeTab tab) {
		final var blockName = block.getId().getPath();
		return REGISTRY.register(blockName, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
