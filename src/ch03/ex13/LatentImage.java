package ch03.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * This class can be used for image transformation by the specified sequences.
 * 
 * @author yukiohta
 *
 */
public class LatentImage {
	private Image in;
	private List<ColorTransformer> pendingOperations;

	private LatentImage(Image in) {
		this.in = in;
		this.pendingOperations = new ArrayList<>();
	}

	/**
	 * Creates LatentImage object from {@link Image}.
	 * 
	 * @param in
	 *            input image
	 * @return new LatentImage object
	 */
	public static LatentImage from(Image in) {
		return new LatentImage(in);
	}

	/**
	 * Adds a transformation and returns the updated LatentImage object.
	 * 
	 * @param t
	 *            color transformer
	 * @return updated image
	 */
	public LatentImage transform(ColorTransformer t) {
		pendingOperations.add(t);
		return this;
	}

	/**
	 * Adds an operation and returns the updated LatentImage object.
	 * 
	 * @param op
	 *            operation to convert color
	 * @return updated image
	 */

	public LatentImage transform(UnaryOperator<Color> op) {
		pendingOperations.add((x, y, r) -> op.apply(r.getColor(x, y)));
		return this;
	}

	/**
	 * Returns transformed image based on specified transformation sequences.
	 * 
	 * @return transformed image
	 */
	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		PixelReader reader = null;
		for (int i = 0; i < pendingOperations.size(); i++) {
			if (i == 0) {
				reader = in.getPixelReader();
			} else {
				reader = out.getPixelReader();
			}

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Color c = pendingOperations.get(i).apply(x, y, reader);
					out.getPixelWriter().setColor(x, y, c);
				}
			}
		}

		return out;
	}
}
