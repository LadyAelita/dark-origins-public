package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

public class EnderBloodFluid extends BloodFluidBase {
	public static final String ID = "ender_blood";

	public static final int COLOR = 0xffd83cff;

	public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation("textures/misc/underwater.png");
	public EnderBloodFluid() {
		super(
			BloodFluidBase.defaultRenderProperties(COLOR)
				.renderOverlayTexture(RENDER_OVERLAY_TEXTURE),
			BloodFluidBase.defaultProperties()
		);
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new EnderBloodFluid()
	);
}
