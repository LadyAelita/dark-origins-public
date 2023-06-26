package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class SunscreenBottle extends Item {
	public static final String ID = "sunscreen_bottle";

	public static final int COOLDOWN = 20;
	public static final int STACK_SIZE = 16;
	public static final CreativeModeTab CREATIVE_TAB = CreativeModeTab.TAB_BREWING;

	public SunscreenBottle(Properties properties) {
		super(properties);
	}

	public static Supplier<Item> factory = () -> new SunscreenBottle(
		(new Properties())
			.stacksTo(STACK_SIZE)
			.tab(CREATIVE_TAB)
	);
}
