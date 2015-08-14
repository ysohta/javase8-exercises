package ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * This class can be used for image transformation in parallel by the specified
 * sequences.
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
		pendingOperations.add((x, y, c) -> op.apply(c));
		return this;
	}

	/**
	 * Returns transformed image based on specified transformation sequences in
	 * parallel.
	 * 
	 * @return transformed image
	 */
	public Image toImageInParallel() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		int n = Runtime.getRuntime().availableProcessors();

		Color[][] inData = getColorsFromImage();
		for (int i = 0; i < pendingOperations.size(); i++) {
			ColorTransformer f = pendingOperations.get(i);

			try {
				ExecutorService pool = Executors.newCachedThreadPool();
				for (int j = 0; j < n; j++) {
					int fromY = height / n * j;
					int toY = height / n * (j + 1);
					pool.submit(() -> {
						for (int x = 0; x < width; x++) {
							for (int y = fromY; y < toY; y++) {
								inData[y][x] = f.apply(x, y, inData[y][x]);
							}
						}
					});
				}
				pool.shutdown();

				pool.awaitTermination(1, TimeUnit.HOURS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		WritableImage out = new WritableImage(width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				out.getPixelWriter().setColor(x, y, inData[y][x]);
			}
		}

		return out;
	}

	private Color[][] getColorsFromImage() {
		int height = (int) in.getHeight();
		int width = (int) in.getWidth();
		Color[][] inData = new Color[height][width];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				inData[j][i] = in.getPixelReader().getColor(i, j);
			}
		}

		return inData;
	}
}
