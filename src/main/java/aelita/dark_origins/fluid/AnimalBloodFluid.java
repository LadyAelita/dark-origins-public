package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import aelita.dark_origins.DarkOriginsMod;
import net.minecraft.resources.ResourceLocation;

public class AnimalBloodFluid extends BloodFluidBase {
	public static final String ID = "animal_blood";

	public static final int COLOR = 0xff8c1205;

	public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation(DarkOriginsMod.MOD_ID, "textures/overlay/blood.png");

	public AnimalBloodFluid() {
		super(
			BloodFluidBase.defaultRenderProperties(COLOR)
				.renderOverlayTexture(RENDER_OVERLAY_TEXTURE),
			BloodFluidBase.defaultProperties()
		);
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new AnimalBloodFluid()
	);
}
