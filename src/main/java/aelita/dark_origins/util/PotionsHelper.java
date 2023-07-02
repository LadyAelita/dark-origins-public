package aelita.dark_origins.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class PotionsHelper {
	private static List<Pair<Potion, Potion>> oppositePotions = Arrays.asList(
		Pair.of(Potions.NIGHT_VISION, Potions.INVISIBILITY),
		Pair.of(Potions.LONG_NIGHT_VISION, Potions.LONG_INVISIBILITY),
		Pair.of(Potions.FIRE_RESISTANCE, Potions.WATER_BREATHING),
		Pair.of(Potions.LONG_FIRE_RESISTANCE, Potions.LONG_WATER_BREATHING),
		Pair.of(Potions.SWIFTNESS, Potions.SLOWNESS),
		Pair.of(Potions.LONG_SWIFTNESS, Potions.LONG_SLOWNESS),
		Pair.of(Potions.STRONG_SWIFTNESS, Potions.STRONG_SLOWNESS),
		Pair.of(Potions.HEALING, Potions.HARMING),
		Pair.of(Potions.STRONG_HEALING, Potions.STRONG_HARMING),
		Pair.of(Potions.POISON, Potions.REGENERATION),
		Pair.of(Potions.LONG_POISON, Potions.LONG_REGENERATION),
		Pair.of(Potions.STRONG_POISON, Potions.STRONG_REGENERATION),
		Pair.of(Potions.STRENGTH, Potions.WEAKNESS),
		Pair.of(Potions.LONG_STRENGTH, Potions.LONG_WEAKNESS),
		Pair.of(Potions.LEAPING, Potions.SLOW_FALLING),
		Pair.of(Potions.LONG_LEAPING, Potions.LONG_SLOW_FALLING)
	);

	public static List<Pair<Potion, Potion>> getOpposites() {
		return Collections.unmodifiableList(oppositePotions);
	}
}
