package aelita.dark_origins.fluid;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import aelita.dark_origins.Blocks;
import aelita.dark_origins.Fluids;
import aelita.dark_origins.Items;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;
import net.minecraftforge.registries.RegistryObject;

public class FluidFamily {
	public final String name;

	public final RegistryObject<FluidType> type;
	public final FluidType.Properties typeProperties;

	private final ForgeFlowingFluid.Properties fluidProperties;
	public final RegistryObject<ForgeFlowingFluid.Source> source;
	public final RegistryObject<ForgeFlowingFluid.Flowing> flowing;

	public final RegistryObject<LiquidBlock> block;
	public final RegistryObject<BucketItem> bucket;

	public FluidFamily(
		String name,
		FluidType.Properties typeProperties,
		Function<FluidType.Properties, FluidType> typeFactory,
		@Nullable Consumer<ForgeFlowingFluid.Properties> fluidPropertiesTransformer,
		@Nullable BlockBehaviour.Properties blockProperties,
		@Nullable Item.Properties bucketItemProperties
	) {
		this.name = name;
		this.typeProperties = typeProperties;
		this.type = Fluids.FLUID_TYPES.register(name, () -> typeFactory.apply(typeProperties));

		this.source = Fluids.FLUIDS.register(name + "_source", () -> createSource());
		this.flowing = Fluids.FLUIDS.register(name + "_flowing", () -> createFlowing());
		this.fluidProperties = new ForgeFlowingFluid.Properties(type, source, flowing);

		final BlockBehaviour.Properties liquidBlockProperties = blockProperties != null ? blockProperties
			: BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.WATER);
		this.block = Blocks.REGISTRY.register(name, () -> new LiquidBlock(this.source, liquidBlockProperties));
		fluidProperties.block(block);

		final Item.Properties bucketProperties = bucketItemProperties != null
			? bucketItemProperties
			: defaultBucketItemProperties();
		this.bucket = Items.REGISTRY.register(name + "_bucket", () -> new BucketItem(this.source, bucketProperties));
		fluidProperties.bucket(bucket);

		if (fluidPropertiesTransformer != null) {
			fluidPropertiesTransformer.accept(fluidProperties);
		}
	}
	public FluidFamily(
		String name,
		FluidType.Properties typeProperties,
		Function<FluidType.Properties, FluidType> typeFactory,
		Consumer<ForgeFlowingFluid.Properties> fluidPropertiesTransformer,
		BlockBehaviour.Properties blockProperties
	) {
		this(name, typeProperties, typeFactory, fluidPropertiesTransformer, blockProperties, null);
	}
	public FluidFamily(
		String name,
		FluidType.Properties typeProperties,
		Function<FluidType.Properties, FluidType> typeFactory,
		Consumer<ForgeFlowingFluid.Properties> fluidPropertiesTransformer
	) {
		this(name, typeProperties, typeFactory, fluidPropertiesTransformer, null, null);
	}
	public FluidFamily(
		String name,
		FluidType.Properties typeProperties,
		Function<FluidType.Properties, FluidType> typeFactory
	) {
		this(name, typeProperties, typeFactory, null, null, null);
	}

	public FluidFamily(
		String name,
		Supplier<CustomFluidType> typeFactory,
		@Nullable Consumer<ForgeFlowingFluid.Properties> fluidPropertiesTransformer,
		@Nullable BlockBehaviour.Properties blockProperties,
		@Nullable Item.Properties bucketItemProperties
	) {
		this(name, typeFactory.get().getProperties(), (properties) -> typeFactory.get(), fluidPropertiesTransformer, blockProperties, bucketItemProperties);
	}
	public FluidFamily(
		String name,
		Supplier<CustomFluidType> typeFactory,
		Consumer<ForgeFlowingFluid.Properties> fluidPropertiesTransformer,
		BlockBehaviour.Properties blockProperties
	) {
		this(name, typeFactory, fluidPropertiesTransformer, blockProperties, null);
	}
	public FluidFamily(
		String name,
		Supplier<CustomFluidType> typeFactory,
		Consumer<ForgeFlowingFluid.Properties> fluidPropertiesTransformer
	) {
		this(name, typeFactory, fluidPropertiesTransformer, null, null);
	}
	public FluidFamily(
		String name,
		Supplier<CustomFluidType> typeFactory,
		CreativeModeTab bucketTab
	) {
		this(name, typeFactory, null, null, defaultBucketItemProperties().tab(bucketTab));
	}
	public FluidFamily(String name, Supplier<CustomFluidType> typeFactory) {
		this(name, typeFactory, null, null, null);
	}

	private Source createSource() {
		return new ForgeFlowingFluid.Source(this.fluidProperties);
	}
	private Flowing createFlowing() {
		return new ForgeFlowingFluid.Flowing(this.fluidProperties);
	}

	public static Item.Properties defaultBucketItemProperties() {
		return new Item.Properties()
			.craftRemainder(net.minecraft.world.item.Items.BUCKET)
			.stacksTo(1)
			.tab(CreativeModeTab.TAB_MISC);
	}
}
