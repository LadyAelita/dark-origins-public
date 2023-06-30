package aelita.dark_origins;

import aelita.dark_origins.item.AnimalBloodItem;
import aelita.dark_origins.item.BloodItem;
import aelita.dark_origins.item.Broom;
import aelita.dark_origins.item.CoagulatedBloodItem;
import aelita.dark_origins.item.EnchantedBloodItem;
import aelita.dark_origins.item.EnderBloodItem;
import aelita.dark_origins.item.IllagerBloodItem;
import aelita.dark_origins.item.PhantomEssenceItem;
import aelita.dark_origins.item.RitualDaggerItem;
import aelita.dark_origins.item.SunscreenBottle;
import aelita.dark_origins.item.VampireBloodItem;
import aelita.dark_origins.item.VillagerBloodItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
	public static final DeferredRegister<Item> REGISTRY
		= DeferredRegister.create(ForgeRegistries.ITEMS, DarkOriginsMod.MOD_ID);

	public static final RegistryObject<Item> BLOOD = REGISTRY.register(BloodItem.ID, BloodItem.factory);
	public static final RegistryObject<Item> ANIMAL_BLOOD = REGISTRY.register(AnimalBloodItem.ID, AnimalBloodItem.factory);
	public static final RegistryObject<Item> COAGULATED_BLOOD = REGISTRY.register(CoagulatedBloodItem.ID, CoagulatedBloodItem.factory);
	public static final RegistryObject<Item> ENCHANTED_BLOOD = REGISTRY.register(EnchantedBloodItem.ID, EnchantedBloodItem.factory);
	public static final RegistryObject<Item> ENDER_BLOOD = REGISTRY.register(EnderBloodItem.ID, EnderBloodItem.factory);
	public static final RegistryObject<Item> ILLAGER_BLOOD = REGISTRY.register(IllagerBloodItem.ID, IllagerBloodItem.factory);
	public static final RegistryObject<Item> PHANTOM_ESSENCE = REGISTRY.register(PhantomEssenceItem.ID, PhantomEssenceItem.factory);
	public static final RegistryObject<Item> VAMPIRE_BLOOD = REGISTRY.register(VampireBloodItem.ID, VampireBloodItem.factory);
	public static final RegistryObject<Item> VILLAGER_BLOOD = REGISTRY.register(VillagerBloodItem.ID, VillagerBloodItem.factory);

	public static final RegistryObject<Item> BROOM = REGISTRY.register(Broom.ID, Broom.factory);
	public static final RegistryObject<Item> RITUAL_DAGGER = REGISTRY.register(RitualDaggerItem.ID, RitualDaggerItem.factory);
	public static final RegistryObject<Item> SUNSCREEN_BOTTLE = REGISTRY.register(SunscreenBottle.ID, SunscreenBottle.factory);

	// "Icon items" - that is, unobtainable items that have no function outside
	//  of having a specific texture. This can be used, for example, as icons
	//  for custom origins
	public static final RegistryObject<Item> VAMPIRE_LIPS_ICON = registerIconItem("vampire_lips_icon");

	private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block) {
		final var blockName = block.getId().getPath();
		return REGISTRY.register(blockName, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block, CreativeModeTab tab) {
		final var blockName = block.getId().getPath();
		return REGISTRY.register(blockName, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}

	private static RegistryObject<Item> registerIconItem(String id) {
		return REGISTRY.register(id, () -> new Item(
			(new Item.Properties()).rarity(Rarity.EPIC)
		));
	}
}
