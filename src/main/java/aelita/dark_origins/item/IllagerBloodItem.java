package aelita.dark_origins.item;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class IllagerBloodItem {
	public static final String ID = "illager_blood";

	public static final FoodProperties VAMPIRE_FOOD = (new FoodProperties.Builder())
		.nutrition(8).saturationMod(1.6f)
		.build();

	public static final FoodProperties HUMAN_FOOD = (new FoodProperties.Builder())
		.nutrition(1).saturationMod(1.6f)
		.effect(() -> new MobEffectInstance(MobEffects.POISON, 200), 0.4f)
		.build();

	public static Supplier<Item> factory = () -> {
		return new BloodItemBase(VAMPIRE_FOOD, HUMAN_FOOD, (new Item.Properties())
			.tab(CreativeModeTab.TAB_FOOD)
			.food(VAMPIRE_FOOD)
		);
	};
}