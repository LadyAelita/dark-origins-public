package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

public class HemolymphFluid extends BloodFluidBase {
	public static final String ID = "hemolymph";

	public static final int COLOR = 0xff00728c;

	public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation("textures/misc/underwater.png");

	public HemolymphFluid() {
		super(
			BloodFluidBase.defaultRenderProperties(COLOR)
				.renderOverlayTexture(RENDER_OVERLAY_TEXTURE),
			BloodFluidBase.defaultProperties()
		);
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new HemolymphFluid()
	);
}
