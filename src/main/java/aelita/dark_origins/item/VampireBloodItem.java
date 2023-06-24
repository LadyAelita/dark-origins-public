package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class VampireBloodItem extends BloodItemBase {
	public static final String ID = "vampire_blood";

	public VampireBloodItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	public static Supplier<Item> factory = () -> new VampireBloodItem(
		(new Item.Properties())
			.tab(CreativeModeTab.TAB_MISC)
	);
}
