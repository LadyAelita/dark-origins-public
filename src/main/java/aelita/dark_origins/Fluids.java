package aelita.dark_origins;

import aelita.dark_origins.fluid.AnimalBloodFluid;
import aelita.dark_origins.fluid.BloodFluid;
import aelita.dark_origins.fluid.EnchantedBloodFluid;
import aelita.dark_origins.fluid.EnderBloodFluid;
import aelita.dark_origins.fluid.FluidFamily;
import aelita.dark_origins.fluid.HemolymphFluid;
import aelita.dark_origins.fluid.IllagerBloodFluid;
import aelita.dark_origins.fluid.SpiderHemolymphFluid;
import aelita.dark_origins.fluid.VampireBloodFluid;
import aelita.dark_origins.fluid.VillagerBloodFluid;
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
	public static final FluidFamily ANIMAL_BLOOD = AnimalBloodFluid.factory.get();
	public static final FluidFamily ENCHANTED_BLOOD = EnchantedBloodFluid.factory.get();
	public static final FluidFamily ENDER_BLOOD = EnderBloodFluid.factory.get();
	public static final FluidFamily ILLAGER_BLOOD = IllagerBloodFluid.factory.get();
	public static final FluidFamily VAMPIRE_BLOOD = VampireBloodFluid.factory.get();
	public static final FluidFamily VILLAGER_BLOOD = VillagerBloodFluid.factory.get();
	public static final FluidFamily HEMOLYMPH = HemolymphFluid.factory.get();
	public static final FluidFamily SPIDER_HEMOLYMPH = SpiderHemolymphFluid.factory.get();

	public static void register(IEventBus bus) {
		FLUIDS.register(bus);
		FLUID_TYPES.register(bus);
	}
}
