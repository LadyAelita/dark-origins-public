package aelita.dark_origins.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BloodItemBase extends Item {
	public static final String TAG_BLOOD = "Blood";
	public static final String TAG_DONOR_UUID = "DonorUUID";

	public BloodItemBase(Item.Properties properties) {
		super(properties);
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
