package ch03.ex11;

import java.nio.file.Paths;
import java.util.function.UnaryOperator;

import ch03.ex08.ColorTransformer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is a JavaFX application to show image transformation.
 * 
 * @author yukiohta
 *
 */
public class ImageTransformer extends Application {

	/**
	 * Returns an operator to apply first operator and second operator
	 * subsequently.
	 * 
	 * @param first
	 *            first operator
	 * @param second
	 *            second operator
	 * @return composed operator
	 */
	public static ColorTransformer compose(ColorTransformer first, ColorTransformer second) {
		return (x, y, c) -> second.apply(x, y, first.apply(x, y, c));
	}

	/**
	 * Convert operator to {@link ch03.ex11.ColorTransformer} object.
	 * 
	 * @param op
	 *            operator to be converted
	 * @return transformer
	 */
	public static ColorTransformer convertToColorTransformer(UnaryOperator<Color> op) {
		return (x, y, c) -> op.apply(c);
	}

	/**
	 * Transform image based on the transformer.
	 * 
	 * @param image
	 *            input image
	 * @param transformer
	 *            color transformer
	 * @return transformed image
	 */
	public static Image transform(Image image, ColorTransformer transformer) {
		int w = (int) image.getWidth();
		int h = (int) image.getHeight();
		WritableImage out = new WritableImage(w, h);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Color c = image.getPixelReader().getColor(x, y);
				out.getPixelWriter().setColor(x, y, transformer.apply(x, y, c));
			}
		}

		return out;
	}

	/**
	 * Entry point of JavaFX application.
	 * 
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		String uri = Paths.get("res/eiffel-tower.jpg").toUri().toString();
		Image image = new Image(uri);

		int frameWidth = 10;
		Image finalImage = transform(
				image,
				compose(convertToColorTransformer(Color::brighter),
						(x, y, c) -> (x < frameWidth || x >= image.getWidth() - frameWidth || y < frameWidth || y >= image
								.getHeight() - frameWidth) ? Color.GRAY : c));
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
		stage.show();
	}

	/**
	 * Entry point of application.
	 * 
	 * @param args
	 *            arguments for application
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
