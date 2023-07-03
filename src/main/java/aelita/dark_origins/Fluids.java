package aelita.dark_origins;

import aelita.dark_origins.fluid.BloodFluid;
import aelita.dark_origins.fluid.FluidFamily;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Fluids {
	public static final DeferredRegister<Fluid> FLUIDS
		= DeferredRegister.create(ForgeRegistries.FLUIDS, DarkOriginsMod.MOD_ID);
	public static final DeferredRegister<FluidType> FLUID_TYPES
		= DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, DarkOriginsMod.MOD_ID);
	
	public static final FluidFamily BLOOD = BloodFluid.factory.get();

	public static void register(IEventBus bus) {
		FLUIDS.register(bus);
		FLUID_TYPES.register(bus);
	}
}
