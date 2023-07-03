package aelita.dark_origins.util;

import com.mojang.math.Vector3f;

public class ColorUtils {
	public static Vector3f hexToRGB(int hexColor) {
		int redHex = (hexColor & 0xFF0000) >>> 16;
		int greenHex = (hexColor & 0x00FF00) >>> 8;
		int blueHex = (hexColor & 0x0000FF);
		Vector3f floatColor = new Vector3f(redHex / 255f, greenHex / 255f, blueHex / 255f);
		return floatColor;
	}
}
