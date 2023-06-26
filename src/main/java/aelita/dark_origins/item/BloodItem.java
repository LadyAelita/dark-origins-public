package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class BloodItem {
	public static final String ID = "blood";
	public static final FoodProperties FOOD = BloodItemBase.FOOD;

	public static Supplier<Item> factory = () -> {
		return new BloodItemBase((new Item.Properties())
			.tab(CreativeModeTab.TAB_FOOD)
			.food(FOOD)
		);
	};
}
