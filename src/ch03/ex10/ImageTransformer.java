package ch03.ex10;

import java.nio.file.Paths;
import java.util.function.UnaryOperator;

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
	 * Transform image based on the transformer.
	 * 
	 * @param in
	 *            input image
	 * @param op
	 *            operator to transform color
	 * @return transformed image
	 */
	public static <T> Image transform(Image in, UnaryOperator<Color> op) {
		int w = (int) in.getWidth();
		int h = (int) in.getHeight();
		WritableImage out = new WritableImage(w, h);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				out.getPixelWriter().setColor(x, y, op.apply(c));
			}
		}

		return out;
	}

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
	public static <T> UnaryOperator<T> compose(UnaryOperator<T> first, UnaryOperator<T> second) {
		return t -> second.apply(first.apply(t));
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

		// compile error for the type mismatch on parameter of transform()
		// UnaryOperator is expected but Function is returned on
		// Function#compose
		// UnaryOperator<Color> op = Color::brighter;
		// Image transformed = transform(image, op.compose(Color::grayscale));

		// use self-defined compose method instead
		Image finalImage = transform(image, compose(Color::brighter, Color::grayscale));
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
