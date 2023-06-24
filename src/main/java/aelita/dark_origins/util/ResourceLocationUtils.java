package aelita.dark_origins.util;

import javax.annotation.Nullable;

import net.minecraft.resources.ResourceLocation;

public class ResourceLocationUtils {
	public static boolean isEqual(@Nullable ResourceLocation lhs, @Nullable ResourceLocation rhs) {
		if (lhs == rhs) {
			return true;
		} else if (lhs == null || rhs == null) {
			return false;
		} else {
			return lhs.compareNamespaced(rhs) == 0;
		}
	}
}
