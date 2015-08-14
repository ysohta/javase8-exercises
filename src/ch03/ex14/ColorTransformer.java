package ch03.ex14;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * This interface represents transformer that produces color at the specific
 * position in an image.
 * 
 * @author yukiohta
 *
 */
@FunctionalInterface
public interface ColorTransformer {

	/**
	 * Applies the color transformation at the position of x and y.
	 * 
	 * @param x
	 *            horizontal index of image
	 * @param y
	 *            vertical index of image
	 * @param reader
	 *            image reader
	 * @return transformed color
	 */
	Color apply(int x, int y, PixelReader reader);
}
