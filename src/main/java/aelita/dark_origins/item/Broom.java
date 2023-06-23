package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Broom {
	public static final String ID = "broom";
	public static final CreativeModeTab CREATIVE_TAB  = CreativeModeTab.TAB_MISC;

	public static final Supplier<Item> factory = () -> new Item(
		(new Item.Properties()).tab(CREATIVE_TAB).stacksTo(1)
	);
}
