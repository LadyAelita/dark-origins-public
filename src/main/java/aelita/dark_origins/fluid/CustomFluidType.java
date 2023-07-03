package aelita.dark_origins.fluid;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Vector3f;

import aelita.dark_origins.util.ColorUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

public class CustomFluidType extends FluidType {
	private static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
	private static final ResourceLocation WATER_FLOW = new ResourceLocation("block/water_flow");
	private static final @Nullable ResourceLocation FLUID_OVERLAY = null;

	private final ResourceLocation stillTexture;
	private final ResourceLocation flowingTexture;
	private final @Nullable ResourceLocation overlayTexture;

	private final int tintColor;
	private final Vector3f fogColor;

	private final Properties properties;

	public CustomFluidType(
		final ResourceLocation stillTexture,
		final ResourceLocation flowingTexture,
		final @Nullable ResourceLocation overlayTexture,
		final int tintColor,
		final Vector3f fogColor,
		final Properties properties
	) {
		super(properties);
		this.stillTexture = stillTexture;
		this.flowingTexture = flowingTexture;
		this.overlayTexture = overlayTexture;
		this.tintColor = tintColor;
		this.fogColor = fogColor;
		this.properties = properties;
	}

	public CustomFluidType(
		final ResourceLocation stillTexture,
		final ResourceLocation flowingTexture,
		final @Nullable ResourceLocation overlayTexture,
		final int color,
		final Properties properties
	) {
		this(stillTexture, flowingTexture, overlayTexture, color, ColorUtils.hexToRGB(color), properties);
	}

	public CustomFluidType(
		final int tintColor,
		final Vector3f fogColor,
		final Properties properties
	) {
		this(WATER_STILL, WATER_FLOW, FLUID_OVERLAY, tintColor, fogColor, properties);
	}

	public CustomFluidType(
		final int color,
		final Properties properties
	) {
		this(color, ColorUtils.hexToRGB(color), properties);
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			@Override
			public int getTintColor() {
				return tintColor;
			}

			@Override
			public ResourceLocation getStillTexture() {
				return stillTexture;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return flowingTexture;
			}

			@Override
			public @Nullable ResourceLocation getOverlayTexture() {
				return overlayTexture;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return null;
			}
			
			@Override
			public @NotNull Vector3f modifyFogColor(
				Camera camera, float partialTick, ClientLevel level, int renderDistance,
				float darkenWorldAmount, Vector3f fluidFogColor
			) {
				return fogColor;
			}

			@Override
			public void modifyFogRender(Camera camera, FogMode mode,
				float renderDistance, float partialTick, float nearDistance,
				float farDistance, FogShape shape
			) {
				RenderSystem.setShaderFogStart(1.0f);
				RenderSystem.setShaderFogEnd(6.0f);
			}
		});
	}

	public ResourceLocation getStillTexture() {
		return stillTexture;
	}

	public ResourceLocation getFlowingTexture() {
		return flowingTexture;
	}

	public @Nullable ResourceLocation getOverlayTexture() {
		return overlayTexture;
	}

	public int getTintColor() {
		return tintColor;
	}

	public Vector3f getFogColor() {
		return fogColor;
	}

	public Properties getProperties() {
		return properties;
	}
}
