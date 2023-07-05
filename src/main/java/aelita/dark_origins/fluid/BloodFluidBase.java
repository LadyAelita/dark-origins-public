package aelita.dark_origins.fluid;

public class BloodFluidBase extends CustomFluidType {
	public BloodFluidBase(RenderingProperties renderingProperties, Properties typeProperties) {
		super(renderingProperties, typeProperties);
	}
	public BloodFluidBase(int color, Properties typeProperties) {
		super(color, typeProperties);
	}

	public static Properties defaultProperties() {
		return Properties.create()
			.canDrown(true)
			.canExtinguish(true)
			.canHydrate(true)
			.canPushEntity(true)
			.canSwim(true)
			.supportsBoating(true);
	}

	public static RenderingProperties defaultRenderProperties(int color) {
		return RenderingProperties.create(color)
			.setShaderFogStart(0.0f)
			.setShaderFogEnd(1.0f);
	}
}
