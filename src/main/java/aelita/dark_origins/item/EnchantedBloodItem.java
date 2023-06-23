package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantedBloodItem extends BloodItemBase {
	public static final String ID = "enchanted_blood";

	public EnchantedBloodItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFoil(ItemStack p_41453_) {
		return true;
	}

	public static Supplier<Item> factory = () -> {
		return new EnchantedBloodItem((new Item.Properties())
			.tab(CreativeModeTab.TAB_MISC)
		);
	};
}
