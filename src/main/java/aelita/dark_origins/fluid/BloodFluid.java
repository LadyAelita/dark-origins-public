package aelita.dark_origins.fluid;

import java.util.function.Supplier;

public class BloodFluid extends BloodFluidBase {
	public static final String ID = "blood";

	public static final int COLOR = 0xff7a0000;

	public BloodFluid() {
		super(COLOR, BloodFluidBase.createProperties());
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new BloodFluid()
	);
}
