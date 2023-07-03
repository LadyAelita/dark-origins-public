package aelita.dark_origins.fluid;

import com.mojang.math.Vector3f;

public class BloodFluidBase extends CustomFluidType {
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
