package ch03.ex13;

import javafx.scene.paint.Color;

/**
 * This class represents intermediate color for color value calculation. If RGB
 * values become out of range (from 0.0 to 1.0) during calculation, the object
 * retains calculated intermediate value. When {@link Color} object is created
 * from the object with color values out of range using
 * {@link IntermediateColor#toColor()} , the values are rolled up to boundary
 * values.
 * 
 * @author yukiohta
 *
 */
public class IntermediateColor {
	private static double MIN_COLOR_VALUE = 0.0;
	private static double MAX_COLOR_VALUE = 1.0;

	private final double r;
	private final double g;
	private final double b;

	private IntermediateColor(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Creates IntermediateColor object from {@link Color}.
	 * 
	 * @param c
	 *            based color
	 * @return object
	 */
	public static IntermediateColor from(Color c) {
		return new IntermediateColor(c.getRed(), c.getGreen(), c.getBlue());
	}

	/**
	 * Returns added color values.
	 * 
	 * @param c
	 *            color
	 * @return calculated color
	 */
	public IntermediateColor add(IntermediateColor c) {
		return new IntermediateColor(r + c.r, g + c.g, b + c.b);
	}

	/**
	 * Returns subtracted color values.
	 * 
	 * @param c
	 *            color
	 * @return calculated color
	 */
	public IntermediateColor subtract(IntermediateColor c) {
		return new IntermediateColor(r - c.r, g - c.g, b - c.b);
	}

	/**
	 * Returns multiplied color values.
	 * 
	 * @param c
	 *            color
	 * @return calculated color
	 */
	public IntermediateColor multiply(double coefficient) {
		return new IntermediateColor(r * coefficient, g * coefficient, b * coefficient);
	}

	/**
	 * Returns devided color values.
	 * 
	 * @param c
	 *            color
	 * @return calculated color
	 * @throws IllegalArgumentException
	 *             if divisor is 0
	 */
	public IntermediateColor devide(double devisor) {
		if (devisor == 0)
			throw new IllegalArgumentException("devisor must not be 0");

		return new IntermediateColor(r / devisor, g / devisor, b / devisor);
	}

	/**
	 * Returns {@link Color} object. If color values are out of range, the
	 * values are rolled up to boundary values.
	 * 
	 * @return color object
	 */
	public Color toColor() {
		return Color.color(replaceWithinRange(r), replaceWithinRange(g), replaceWithinRange(b));
	}

	@Override
	public String toString() {
		return "IntermediateColor [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

	private static double replaceWithinRange(double val) {
		if (val < MIN_COLOR_VALUE)
			return MIN_COLOR_VALUE;

		if (val > MAX_COLOR_VALUE)
			return MAX_COLOR_VALUE;

		return val;
	}
}
