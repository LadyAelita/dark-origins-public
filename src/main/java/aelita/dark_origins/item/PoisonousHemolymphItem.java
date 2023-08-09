package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class PoisonousHemolymphItem {
	public static final String ID = "poisonous_hemolymph";

	public static final FoodProperties VAMPIRE_FOOD = (new FoodProperties.Builder())
		.nutrition(7).saturationMod(0.8f)
		.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 1), 1.0f)
		.build();

	public static final FoodProperties HUMAN_FOOD = (new FoodProperties.Builder())
		.nutrition(1).saturationMod(0.8f)
		.effect(() -> new MobEffectInstance(MobEffects.POISON, 200), 1.0f)
		.build();

	public static Supplier<Item> factory = () -> {
		return new BloodItemBase(VAMPIRE_FOOD, HUMAN_FOOD, (new Item.Properties())
			.tab(CreativeModeTab.TAB_FOOD)
			.food(VAMPIRE_FOOD)
		);
	};

	public static Supplier<Item> bottleFactory = () -> new BloodBottleItemBase(VAMPIRE_FOOD, HUMAN_FOOD,
		(new Item.Properties())
			.tab(CreativeModeTab.TAB_BREWING)
			.food(VAMPIRE_FOOD)
			.stacksTo(16)
	);

	public static Supplier<Item> vialFactory = () -> new BloodVialItemBase(
		(new Item.Properties())
			.tab(CreativeModeTab.TAB_BREWING)
	);
}
