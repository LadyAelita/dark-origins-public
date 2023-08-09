package aelita.dark_origins.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BloodVialItemBase extends Item {
	public BloodVialItemBase(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasCraftingRemainingItem() {
		return aelita.odds_and_ends.Items.GLASS_VIAL.isPresent();
	}
	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return hasCraftingRemainingItem();
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		if (!hasCraftingRemainingItem(itemStack)) {
			return ItemStack.EMPTY;
		}
		return new ItemStack(aelita.odds_and_ends.Items.GLASS_VIAL.get());
	}
}
