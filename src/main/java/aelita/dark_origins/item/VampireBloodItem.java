package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class VampireBloodItem extends BloodItemBase {
	public static final String ID = "vampire_blood";

	public static final FoodProperties VAMPIRE_FOOD = (new FoodProperties.Builder())
		.nutrition(14).saturationMod(1.2f)
		.build();

	public static final FoodProperties HUMAN_FOOD = (new FoodProperties.Builder())
		.nutrition(2).saturationMod(1.2f)
		.effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.1f)
		.build();

	public VampireBloodItem(Properties properties) {
		super(VAMPIRE_FOOD, HUMAN_FOOD, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	public static Supplier<Item> factory = () -> new VampireBloodItem(
		(new Item.Properties())
			.tab(CreativeModeTab.TAB_FOOD)
			.food(VAMPIRE_FOOD)
	);
}
