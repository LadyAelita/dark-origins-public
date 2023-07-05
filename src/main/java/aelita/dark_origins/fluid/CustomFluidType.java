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
	private static final @Nullable ResourceLocation FLUID_RENDER_OVERLAY = null;

	private final ResourceLocation stillTexture;
	private final ResourceLocation flowingTexture;
	private final @Nullable ResourceLocation overlayTexture;
	private final @Nullable ResourceLocation renderOverlayTexture;

	private final int tintColor;
	private final Vector3f fogColor;

	private final float setShaderFogStart;
	private final float setShaderFogEnd;

	private final Properties properties;

	public CustomFluidType(
		final RenderingProperties renderingProperties,
		final Properties properties
	) {
		super(properties);

		this.stillTexture = renderingProperties.stillTexture;
		this.flowingTexture = renderingProperties.flowingTexture;
		this.overlayTexture = renderingProperties.overlayTexture;
		this.renderOverlayTexture = renderingProperties.renderOverlayTexture;
		this.tintColor = renderingProperties.tintColor;
		this.fogColor = renderingProperties.fogColor != null
			? renderingProperties.fogColor
			: ColorUtils.hexToRGB(tintColor);
		this.setShaderFogStart = renderingProperties.setShaderFogStart;
		this.setShaderFogEnd = renderingProperties.setShaderFogEnd;

		this.properties = properties;
	}

	public CustomFluidType(
		final int color,
		final Properties properties
	) {
		this(RenderingProperties.create(color), properties);
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
				return renderOverlayTexture;
			}

			@Override
			public @NotNull Vector3f modifyFogColor(
				Camera camera, float partialTick, ClientLevel level, int renderDistance,
				float darkenWorldAmount, Vector3f fluidFogColor
			) {
				// NOTE: If I returned the original this.fogColor vector here,
				//  it could get altered permanently!
				return fogColor.copy();
			}

			@Override
			public void modifyFogRender(Camera camera, FogMode mode,
				float renderDistance, float partialTick, float nearDistance,
				float farDistance, FogShape shape
			) {
				RenderSystem.setShaderFogStart(setShaderFogStart);
				RenderSystem.setShaderFogEnd(setShaderFogEnd);
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

	public static class RenderingProperties {
		private ResourceLocation stillTexture = WATER_STILL;
		private ResourceLocation flowingTexture = WATER_FLOW;
		private @Nullable ResourceLocation overlayTexture = FLUID_OVERLAY;
		private @Nullable ResourceLocation renderOverlayTexture = FLUID_RENDER_OVERLAY;

		private int tintColor;
		private @Nullable Vector3f fogColor = null;

		private float setShaderFogStart = 1.0f;
		private float setShaderFogEnd = 6.0f;

		private RenderingProperties(int color) {
			tintColor = color;
		}

		public RenderingProperties stillTexture(ResourceLocation texture) {
			this.stillTexture = texture;
			return this;
		}
		public RenderingProperties flowingTexture(ResourceLocation texture) {
			this.flowingTexture = texture;
			return this;
		}
		public RenderingProperties overlayTexture(@Nullable ResourceLocation texture) {
			this.overlayTexture = texture;
			return this;
		}
		public RenderingProperties renderOverlayTexture(@Nullable ResourceLocation texture) {
			this.renderOverlayTexture = texture;
			return this;
		}
		public RenderingProperties fogColor(@Nullable Vector3f fogColor) {
			this.fogColor = fogColor;
			return this;
		}
		public RenderingProperties setShaderFogStart(float value) {
			this.setShaderFogStart = value;
			return this;
		}
		public RenderingProperties setShaderFogEnd(float value) {
			this.setShaderFogEnd = value;
			return this;
		}

		public static RenderingProperties create(int color) {
			return new RenderingProperties(color);
		}
	}
}
