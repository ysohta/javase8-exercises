package ch03.ex05;

import java.nio.file.Paths;

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
	 * Transform image based on he transformer.
	 * 
	 * @param image
	 *            input image
	 * @param transformer
	 *            transformer of color
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
		String uri = Paths.get("res/queen-mary.png").toUri().toString();
		Image image = new Image(uri);
		int frameWidth = 10;
		Image framed = transform(image, (x, y, c) -> (x < frameWidth || x >= image.getWidth() - frameWidth
				|| y < frameWidth || y >= image.getHeight() - frameWidth) ? Color.GRAY : c);
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(framed))));
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
