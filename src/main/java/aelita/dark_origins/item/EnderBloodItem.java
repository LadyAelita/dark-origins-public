package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnderBloodItem extends BloodItemBase {
	public static final String ID = "ender_blood";

	public static final FoodProperties VAMPIRE_FOOD = (new FoodProperties.Builder())
		.nutrition(14).saturationMod(2.4f)
		.build();

	public static final FoodProperties HUMAN_FOOD = (new FoodProperties.Builder())
		.nutrition(2).saturationMod(2.4f)
		.effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.1f)
		.build();

	public EnderBloodItem(Properties properties) {
		super(VAMPIRE_FOOD, HUMAN_FOOD, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	public static Supplier<Item> factory = () -> new EnderBloodItem(
		(new Item.Properties())
			.tab(CreativeModeTab.TAB_FOOD)
			.food(VAMPIRE_FOOD)
	);
}
