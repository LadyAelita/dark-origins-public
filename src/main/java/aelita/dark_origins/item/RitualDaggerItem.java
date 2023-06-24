package aelita.dark_origins.item;

import java.util.function.Supplier;

import aelita.dark_origins.Items;
import aelita.dark_origins.origin.OriginLocations;
import aelita.dark_origins.origin.OriginsHelper;
import aelita.dark_origins.util.ResourceLocationUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

// TODO: Ugly, refactor pls : <
//  Like, make helper classes and shit
public class RitualDaggerItem extends Item {
	public static final String ID = "ritual_dagger";

	public static final int COOLDOWN = 100;
	public static final int DURABILITY = 32;
	public static final CreativeModeTab CREATIVE_TAB  = CreativeModeTab.TAB_TOOLS;

	public RitualDaggerItem(Properties properties) {
		super(properties);
	}

	public static Supplier<Item> factory = () -> new RitualDaggerItem(
		(new Properties())
			.durability(DURABILITY)
			.tab(CREATIVE_TAB)
	);

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		float damage = getBleedDamage(player);
		float health = player.getHealth();
		float healthAfterBleeding = health - damage;

		// I'm a generous goddess, I can forgive the player one heart
		if (healthAfterBleeding > -2.0f && healthAfterBleeding <= 0) {
			damage = Math.max(1.0f, damage - 2.0f);
			healthAfterBleeding = health - damage;
		}

		// If the damage is still too big - be it normal, or reduced - abort
		if (healthAfterBleeding < 0.0f) {
			return InteractionResultHolder.fail(itemStack);
		}

		// I need to set a cooldown, because otherwise, the right click
		//  can be held, continuously giving a lot of blood, while not dealing
		//  a lot of damage - seems that damage itself has a cooldown?
		player.getCooldowns().addCooldown(this, COOLDOWN);

		if (player instanceof ServerPlayer serverPlayer) {
			serverPlayer.hurt(DamageSource.GENERIC, damage);

			applyBleedAfterEffects(serverPlayer);

			itemStack.hurtAndBreak(1, player, (_player) -> {
				_player.broadcastBreakEvent(hand);
			});

			player.addItem(getDrawnBloodItemStack(player));
		}

		return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
	}

	public float getBleedDamage(Player player) {
		return 6.0f;
	}

	private void applyBleedAfterEffects(ServerPlayer player) {
		float foodExhaustion = 6.0f;

		int nauseaDurationPerStack = 10 * 20;
		int debuffDuration = 2 * 60 * 20;

		// The `MobEffects.WEAKNESS` here is NOT a mistake! I look at the
		//  number of Weakness stacks to determine Nausea severity - any Nausea
		//  from previous bleedings would have probably disappeared by now
		int nauseaDuration = intTimesAmplifier(player, MobEffects.WEAKNESS, nauseaDurationPerStack);

		player.causeFoodExhaustion(foodExhaustion);
		player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, nauseaDuration));
		player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, debuffDuration, stackEffectAmplifier(player, MobEffects.WEAKNESS)));
		player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, debuffDuration, stackEffectAmplifier(player, MobEffects.MOVEMENT_SLOWDOWN)));
		player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, debuffDuration, stackEffectAmplifier(player, MobEffects.DIG_SLOWDOWN)));
	}

	private int stackEffectAmplifier(Player player, MobEffect effect) {
		MobEffectInstance effectInstance = player.getEffect(effect);
		if (effectInstance == null) {
			return 0;
		} else {
			return effectInstance.getAmplifier() + 1;
		}
	}

	private int intTimesAmplifier(Player player, MobEffect effect, int durationPerStack) {
		MobEffectInstance effectInstance = player.getEffect(effect);
		if (effectInstance == null) {
			return durationPerStack;
		} else {
			return durationPerStack * (1 + effectInstance.getAmplifier());
		}
	}

	private ItemStack getDrawnBloodItemStack(Player player) {
		Item bloodItem = getDrawnBloodItem(player);
		ItemStack blood = new ItemStack(bloodItem, 1);

		CompoundTag bloodNBT = blood.getOrCreateTagElement(BloodItemBase.TAG_BLOOD);
		bloodNBT.putUUID(BloodItemBase.TAG_DONOR_UUID, player.getUUID());

		String playerName = player.getName().getString();
		String bloodName = blood.getItem().getName(blood).getString();
		// TODO: Localize this somehow? I18n cannot be used server-side : <
		String customName = String.format("%s's %s", playerName, bloodName);
		blood.setHoverName(Component.literal(customName).withStyle(ChatFormatting.ITALIC));

		return blood;
	}

	private Item getDrawnBloodItem(Player player) {
		ResourceLocation playerOriginId = OriginsHelper.getPlayerOriginId(player);

		if (ResourceLocationUtils.isEqual(playerOriginId, OriginLocations.WITCH)) {
			return Items.ENCHANTED_BLOOD.get();
		} else if (ResourceLocationUtils.isEqual(playerOriginId, OriginLocations.VAMPIRE)) {
			return Items.VAMPIRE_BLOOD.get();
		} else {
			return Items.BLOOD.get();
		}
	}
}
