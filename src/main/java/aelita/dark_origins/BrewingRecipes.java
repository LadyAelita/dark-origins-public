package aelita.dark_origins;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import aelita.dark_origins.util.PotionsHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class BrewingRecipes {
	private static void registerRecipes() {
		addRecipe(Potions.MUNDANE, Items.SLIME_BALL, aelita.dark_origins.Items.SUNSCREEN_BOTTLE.get());

		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.HEMOLYMPH.get(), Potions.SWIFTNESS);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.SPIDER_HEMOLYMPH.get(), Potions.HARMING);
		addRecipe(Potions.MUNDANE, aelita.dark_origins.Items.POISONOUS_HEMOLYMPH.get(), Potions.POISON);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.POISONOUS_HEMOLYMPH.get(), Potions.HARMING);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.COAGULATED_BLOOD.get(), Potions.HEALING);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.ILLAGER_BLOOD.get(), Items.EXPERIENCE_BOTTLE);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.BLOOD.get(), Items.EXPERIENCE_BOTTLE);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.BLOOD_VIAL.get(), Items.EXPERIENCE_BOTTLE);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.PHANTOM_ESSENCE.get(), Potions.INVISIBILITY);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.ENDER_BLOOD.get(), Potions.NIGHT_VISION);
		addRecipe(Potions.MUNDANE, aelita.dark_origins.Items.ENCHANTED_BLOOD.get(), Items.EXPERIENCE_BOTTLE);
		addRecipe(Potions.MUNDANE, aelita.dark_origins.Items.ENCHANTED_BLOOD_VIAL.get(), Items.EXPERIENCE_BOTTLE);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.VAMPIRE_BLOOD.get(), Potions.STRONG_HARMING);

		// Ender Blood flips the effect of the potion
		for (Pair<Potion, Potion> oppositePotions : PotionsHelper.getOpposites()) {
			addRecipe(oppositePotions.getFirst(), aelita.dark_origins.Items.ENDER_BLOOD.get(), oppositePotions.getSecond());
			addRecipe(oppositePotions.getSecond(), aelita.dark_origins.Items.ENDER_BLOOD.get(), oppositePotions.getFirst());
		}

		// Enchanted Blood makes the effect of the potion stronger, or, if not possible, longer
		for (Pair<Potion, Potion> pair : PotionsHelper.getEnhancedOrExtended()) {
			addRecipe(pair.getFirst(), aelita.dark_origins.Items.ENCHANTED_BLOOD.get(), pair.getSecond());
		}

		// Blood purification recipes
		final List<Item> purifiableBloodVials = List.of(
			aelita.dark_origins.Items.ANIMAL_BLOOD_VIAL.get(),
			aelita.dark_origins.Items.VILLAGER_BLOOD_VIAL.get(),
			aelita.dark_origins.Items.ILLAGER_BLOOD_VIAL.get(),
			aelita.dark_origins.Items.ENCHANTED_BLOOD_VIAL.get()
		);
		final List<Item> purifiableHemolymphVials = List.of(
			aelita.dark_origins.Items.SPIDER_HEMOLYMPH_VIAL.get(),
			aelita.dark_origins.Items.POISONOUS_HEMOLYMPH_VIAL.get()
		);

		for (final Item impureBloodVial : purifiableBloodVials) {
			addRecipe(impureBloodVial, aelita.odds_and_ends.Items.DIAMOND_DUST.get(), aelita.dark_origins.Items.BLOOD_VIAL.get());
		}
		for (final Item impureHemolymphVial : purifiableHemolymphVials) {
			addRecipe(impureHemolymphVial, aelita.odds_and_ends.Items.DIAMOND_DUST.get(), aelita.dark_origins.Items.HEMOLYMPH_VIAL.get());
		}

		// Use certain modded potions, if they exist
		final Potion manaRegenPotion = ForgeRegistries.POTIONS.getValue(new ResourceLocation("ars_nouveau", "mana_regen_potion"));
		final Potion manaRegenPotionStrong = ForgeRegistries.POTIONS.getValue(new ResourceLocation("ars_nouveau", "mana_regen_potion_strong"));
		final Potion manaRegenPotionLong = ForgeRegistries.POTIONS.getValue(new ResourceLocation("ars_nouveau", "mana_regen_potion_long"));
		if (!PotionsHelper.isNull(manaRegenPotion)) {
			addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.ENCHANTED_BLOOD.get(), manaRegenPotion);
			if (!PotionsHelper.isNull(manaRegenPotionStrong)) {
				addRecipe(manaRegenPotion, aelita.dark_origins.Items.ENCHANTED_BLOOD.get(), manaRegenPotionStrong);
			} else if (!PotionsHelper.isNull(manaRegenPotionLong)) {
				addRecipe(manaRegenPotion, aelita.dark_origins.Items.ENCHANTED_BLOOD.get(), manaRegenPotionLong);
			}
		}
	}

	private static void addRecipe(ItemStack input, ItemStack ingredient, ItemStack output) {
		BrewingRecipeRegistry.addRecipe(
			Ingredient.of(input),
			Ingredient.of(ingredient),
			output
		);
	}
	private static void addRecipe(Potion input, ItemStack ingredient, ItemStack output) {
		addRecipe(PotionUtils.setPotion(new ItemStack(Items.POTION), input), ingredient, output);
	}
	private static void addRecipe(Potion input, Item ingredient, Item output) {
		addRecipe(input, new ItemStack(ingredient), new ItemStack(output));
	}
	private static void addRecipe(Potion input, Item ingredient, Potion output) {
		addRecipe(input, new ItemStack(ingredient), PotionUtils.setPotion(new ItemStack(Items.POTION), output));
	}
	private static void addRecipe(Item input, Item ingredient, Item output) {
		addRecipe(new ItemStack(input), new ItemStack(ingredient), new ItemStack(output));
	}

	/**
	 * Add this method as listener of FMLJavaModLoadingContext.get().getModEventBus()
	 *  during mod setup.
	 */
	public static void register(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			registerRecipes();
		});
	}
}
