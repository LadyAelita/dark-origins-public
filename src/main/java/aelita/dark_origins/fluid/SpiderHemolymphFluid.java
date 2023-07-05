package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

public class SpiderHemolymphFluid extends BloodFluidBase {
	public static final String ID = "spider_hemolymph";

	public static final int COLOR = 0xff3a6477;

	public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation("textures/misc/underwater.png");

	public SpiderHemolymphFluid() {
		super(
			BloodFluidBase.defaultRenderProperties(COLOR)
				.renderOverlayTexture(RENDER_OVERLAY_TEXTURE),
			BloodFluidBase.defaultProperties()
		);
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new SpiderHemolymphFluid()
	);
}
