package ch03.ex06;

import java.nio.file.Paths;
import java.util.function.BiFunction;

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
	 * Transform image based on BiFunction.
	 * 
	 * @param in
	 *            input image
	 * @param f
	 *            function for color transformation
	 * @param arg
	 *            argument to transform color
	 * @return transformed image
	 */
	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
		int w = (int) in.getWidth();
		int h = (int) in.getHeight();
		WritableImage out = new WritableImage(w, h);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				out.getPixelWriter().setColor(x, y, f.apply(c, arg));
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
		String uri = Paths.get("res/queen-mary.png").toUri().toString();
		Image image = new Image(uri);
		// make brighter
		Image transformed = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformed))));
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
