package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import aelita.dark_origins.DarkOriginsMod;
import net.minecraft.resources.ResourceLocation;

public class BloodFluid extends BloodFluidBase {
	public static final String ID = "blood";

	public static final int COLOR = 0xff7a0000;

	public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(DarkOriginsMod.MOD_ID, "textures/overlay/blood.png");

	public BloodFluid() {
		super(OVERLAY_TEXTURE, COLOR, BloodFluidBase.createProperties());
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new BloodFluid()
	);
}
