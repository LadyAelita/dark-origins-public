package aelita.dark_origins.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BloodBottleItemBase extends BloodItemBase {
	public BloodBottleItemBase(final FoodProperties vampireFood, final FoodProperties humanFood, final Properties properties) {
		super(vampireFood, humanFood, properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		ItemStack resultStack = super.finishUsingItem(stack, level, entity);

		if (entity instanceof Player player && player.getAbilities().instabuild) {
			return resultStack;
		} else {
			return new ItemStack(Items.GLASS_BOTTLE);
		}
	}
}
