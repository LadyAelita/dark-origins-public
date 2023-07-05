package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import aelita.dark_origins.DarkOriginsMod;
import aelita.dark_origins.util.ColorUtils;
import net.minecraft.resources.ResourceLocation;

public class VampireBloodFluid extends BloodFluidBase {
	public static final String ID = "vampire_blood";

	public static final int COLOR = 0xff3f0925;
	public static final int FOG_COLOR = 0x020001;

	public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation(DarkOriginsMod.MOD_ID, "textures/overlay/blood.png");

	public VampireBloodFluid() {
		super(
			BloodFluidBase.defaultRenderProperties(COLOR)
				.renderOverlayTexture(RENDER_OVERLAY_TEXTURE)
				.fogColor(ColorUtils.hexToRGB(FOG_COLOR)),
			BloodFluidBase.defaultProperties()
		);
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new VampireBloodFluid()
	);
}
