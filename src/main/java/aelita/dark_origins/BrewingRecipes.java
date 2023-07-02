package aelita.dark_origins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.PHANTOM_ESSENCE.get(), Potions.INVISIBILITY);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.ENDER_BLOOD.get(), Potions.NIGHT_VISION);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.ENCHANTED_BLOOD.get(), Items.EXPERIENCE_BOTTLE);
		addRecipe(Potions.AWKWARD, aelita.dark_origins.Items.VAMPIRE_BLOOD.get(), Potions.STRONG_HARMING);
	}

	private static void addRecipe(Potion input, ItemStack ingredient, ItemStack output) {
		BrewingRecipeRegistry.addRecipe(
			Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), input)),
			Ingredient.of(ingredient),
			output
		);
	}
	private static void addRecipe(Potion input, Item ingredient, Item output) {
		addRecipe(input, new ItemStack(ingredient), new ItemStack(output));
	}
	private static void addRecipe(Potion input, Item ingredient, Potion output) {
		addRecipe(input, new ItemStack(ingredient), PotionUtils.setPotion(new ItemStack(Items.POTION), output));
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
