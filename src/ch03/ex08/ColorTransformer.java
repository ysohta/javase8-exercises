package ch03.ex08;

import javafx.scene.image.Image;
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
	 * @param colorAtXY
	 *            color at the position
	 * @return transformed color
	 */
	Color apply(int x, int y, Color colorAtXY);

	/**
	 * Creates {@link ColorTransformer} object to add frame. 
	 * @param image input image 
	 * @param frameWidth pixel width of frame
	 * @param color color of frame
	 * @return transformer
	 */
	static ColorTransformer createWithFrame(Image image, int frameWidth, Color color) {
		return (x, y, c) -> (x < frameWidth || x >= image.getWidth() - frameWidth || y < frameWidth 
				|| y >= image.getHeight() - frameWidth) ? color : c;
	}
}
