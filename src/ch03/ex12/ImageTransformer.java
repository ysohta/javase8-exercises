package ch03.ex12;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	 * Entry point of JavaFX application.
	 * 
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		String uri = Paths.get("res/eiffel-tower.jpg").toUri().toString();
		Image image = new Image(uri);

		int frameWidth = 10;

		Image finalImage = LatentImage
				.from(image)
				.transform(Color::grayscale)
				.transform(
						(x, y, c) -> (x < frameWidth || x >= image.getWidth() - frameWidth || y < frameWidth || y >= image
								.getHeight() - frameWidth) ? Color.GRAY : c).toImage();

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
