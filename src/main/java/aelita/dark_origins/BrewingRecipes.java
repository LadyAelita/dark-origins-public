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