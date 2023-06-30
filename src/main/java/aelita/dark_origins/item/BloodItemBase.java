package aelita.dark_origins.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import aelita.dark_origins.origin.OriginLocations;
import aelita.dark_origins.origin.OriginsHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BloodItemBase extends Item {
	public static final String TAG_BLOOD = "Blood";
	public static final String TAG_DONOR_UUID = "DonorUUID";

	public final FoodProperties vampireFood;
	public final FoodProperties humanFood;

	public BloodItemBase(FoodProperties vampireFood, FoodProperties humanFood, Item.Properties properties) {
		super(properties);
		this.vampireFood = vampireFood;
		this.humanFood = humanFood;
	}

	@Override
	public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
		if (entity instanceof Player player && OriginsHelper.hasPlayerOrigin(player, OriginLocations.VAMPIRE)) {
			return vampireFood;
		}
		return humanFood;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		// if (!OriginsHelper.hasPlayerOrigin(player, OriginLocations.VAMPIRE)) {
		// 	return InteractionResultHolder.fail(stack);
		// }
		return super.use(level, player, hand);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemStack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(
		ItemStack stack,
		@Nullable Level level,
		List<Component> tooltipComponents,
		TooltipFlag isAdvanced
	) {
		if (level == null) {
			return;
		}
		UUID donorUUID = getDonorUUID(stack);
		if (donorUUID == null) {
			return;
		}
		Player donor = level.getPlayerByUUID(donorUUID);
		if (donor == null) {
			return;
		}
		tooltipComponents.add(donor.getName());
	}

	private @Nullable UUID getDonorUUID(ItemStack stack) {
		CompoundTag bloodNBT = stack.getTagElement(TAG_BLOOD);
		if (bloodNBT == null) {
			return null;
		}
		if (!bloodNBT.contains(TAG_DONOR_UUID)) {
			return null;
		}
		UUID donorUUID = bloodNBT.getUUID(TAG_DONOR_UUID);
		return donorUUID;
	}
}
