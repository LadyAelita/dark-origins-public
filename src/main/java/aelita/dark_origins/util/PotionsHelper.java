package aelita.dark_origins.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	private static List<Pair<Potion, Potion>> enhancedPotions = Arrays.asList(
		Pair.of(Potions.LEAPING, Potions.STRONG_LEAPING),
		Pair.of(Potions.SWIFTNESS, Potions.STRONG_SWIFTNESS),
		Pair.of(Potions.SLOWNESS, Potions.STRONG_SLOWNESS),
		Pair.of(Potions.TURTLE_MASTER, Potions.STRONG_TURTLE_MASTER),
		Pair.of(Potions.HEALING, Potions.STRONG_HEALING),
		Pair.of(Potions.HARMING, Potions.STRONG_HARMING),
		Pair.of(Potions.POISON, Potions.STRONG_POISON),
		Pair.of(Potions.REGENERATION, Potions.STRONG_REGENERATION),
		Pair.of(Potions.STRENGTH, Potions.STRONG_STRENGTH)
	);

	private static List<Pair<Potion, Potion>> extendedPotions = Arrays.asList(
		Pair.of(Potions.NIGHT_VISION, Potions.LONG_NIGHT_VISION),
		Pair.of(Potions.INVISIBILITY, Potions.LONG_INVISIBILITY),
		Pair.of(Potions.LEAPING, Potions.LONG_LEAPING),
		Pair.of(Potions.FIRE_RESISTANCE, Potions.LONG_FIRE_RESISTANCE),
		Pair.of(Potions.SWIFTNESS, Potions.LONG_SWIFTNESS),
		Pair.of(Potions.SLOWNESS, Potions.LONG_SLOWNESS),
		Pair.of(Potions.TURTLE_MASTER, Potions.LONG_TURTLE_MASTER),
		Pair.of(Potions.WATER_BREATHING, Potions.LONG_WATER_BREATHING),
		Pair.of(Potions.POISON, Potions.LONG_POISON),
		Pair.of(Potions.REGENERATION, Potions.LONG_REGENERATION),
		Pair.of(Potions.STRENGTH, Potions.LONG_STRENGTH),
		Pair.of(Potions.WEAKNESS, Potions.LONG_WEAKNESS),
		Pair.of(Potions.SLOW_FALLING, Potions.LONG_SLOW_FALLING)
	);

	public static List<Pair<Potion, Potion>> getOpposites() {
		return Collections.unmodifiableList(oppositePotions);
	}

	public static List<Pair<Potion, Potion>> getEnhanced() {
		return Collections.unmodifiableList(enhancedPotions);
	}

	public static List<Pair<Potion, Potion>> getExtended() {
		return Collections.unmodifiableList(extendedPotions);
	}

	public static List<Pair<Potion, Potion>> getEnhancedOrExtended() {
		final HashMap<Potion, Potion> improvedPotionsMap = new HashMap<Potion, Potion>();
		for (Pair<Potion, Potion> pair : enhancedPotions) {
			improvedPotionsMap.put(pair.getFirst(), pair.getSecond());
		}
		for (Pair<Potion, Potion> pair : extendedPotions) {
			improvedPotionsMap.putIfAbsent(pair.getFirst(), pair.getSecond());
		}
		final LinkedList<Pair<Potion, Potion>> improvedPotions = new LinkedList<Pair<Potion, Potion>>();
		for (Map.Entry<Potion, Potion> entry : improvedPotionsMap.entrySet()) {
			improvedPotions.add(Pair.of(entry.getKey(), entry.getValue()));
		}
		return Collections.unmodifiableList(improvedPotions);
	}

	public static List<Pair<Potion, Potion>> getExtendedOrEnhanced() {
		final HashMap<Potion, Potion> improvedPotionsMap = new HashMap<Potion, Potion>();
		for (Pair<Potion, Potion> pair : extendedPotions) {
			improvedPotionsMap.put(pair.getFirst(), pair.getSecond());
		}
		for (Pair<Potion, Potion> pair : enhancedPotions) {
			improvedPotionsMap.putIfAbsent(pair.getFirst(), pair.getSecond());
		}
		final LinkedList<Pair<Potion, Potion>> improvedPotions = new LinkedList<Pair<Potion, Potion>>();
		for (Map.Entry<Potion, Potion> entry : improvedPotionsMap.entrySet()) {
			improvedPotions.add(Pair.of(entry.getKey(), entry.getValue()));
		}
		return Collections.unmodifiableList(improvedPotions);
	}
}
