package aelita.dark_origins.fluid;

import java.util.function.Supplier;

import aelita.dark_origins.DarkOriginsMod;
import aelita.dark_origins.util.ColorUtils;
import net.minecraft.resources.ResourceLocation;

public class EnchantedBloodFluid extends BloodFluidBase {
	public static final String ID = "enchanted_blood";

	public static final int COLOR = 0xffffffff;

	public static final ResourceLocation STILL_TEXTURE = new ResourceLocation(DarkOriginsMod.MOD_ID, "block/enchanted_blood_still");
	public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(DarkOriginsMod.MOD_ID, "block/enchanted_blood_flow");
	public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation(DarkOriginsMod.MOD_ID, "textures/overlay/enchanted_blood.png");

	public EnchantedBloodFluid() {
		super(
			BloodFluidBase.defaultRenderProperties(COLOR)
				.stillTexture(STILL_TEXTURE)
				.flowingTexture(FLOWING_TEXTURE)
				.renderOverlayTexture(RENDER_OVERLAY_TEXTURE)
				.fogColor(ColorUtils.hexToRGB(BloodFluid.COLOR)),
			BloodFluidBase.defaultProperties()
		);
	}

	public static final Supplier<FluidFamily> factory = () -> new FluidFamily(ID,
		() -> new EnchantedBloodFluid()
	);
}
