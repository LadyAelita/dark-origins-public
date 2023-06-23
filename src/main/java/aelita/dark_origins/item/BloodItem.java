package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class BloodItem {
	public static final String ID = "blood";

	public static Supplier<Item> factory = () -> {
		return new BloodItemBase((new Item.Properties())
			.tab(CreativeModeTab.TAB_MISC)
		);
	};
}
