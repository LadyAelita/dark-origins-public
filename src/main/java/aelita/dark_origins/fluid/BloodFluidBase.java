package aelita.dark_origins.fluid;

import com.mojang.math.Vector3f;

import net.minecraft.resources.ResourceLocation;

public class BloodFluidBase extends CustomFluidType {
	public BloodFluidBase(ResourceLocation overlayTexture, int tintColor, Vector3f fogColor, Properties typeProperties) {
		super(overlayTexture, tintColor, fogColor, typeProperties);
	}
	public BloodFluidBase(ResourceLocation overlayTexture, int tintColor, Properties typeProperties) {
		super(overlayTexture, tintColor, typeProperties);
	}
	public BloodFluidBase(int tintColor, Vector3f fogColor, Properties typeProperties) {
		super(tintColor, fogColor, typeProperties);
	}
	public BloodFluidBase(int tintColor, Properties typeProperties) {
		super(tintColor, typeProperties);
	}

	public static Properties createProperties() {
		return Properties.create()
			.canDrown(true)
			.canExtinguish(true)
			.canHydrate(true)
			.canPushEntity(true)
			.canSwim(true)
			.supportsBoating(true);
	}
}
