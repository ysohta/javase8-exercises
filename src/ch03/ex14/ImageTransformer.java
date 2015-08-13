package ch03.ex14;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
		String uri = Paths.get("res/queen-mary.png").toUri().toString();
		Image image = new Image(uri);
		int width = (int) image.getWidth();

		Image finalImage = LatentImage.from(image).transform((x, y, r) -> r.getColor(width - x - 1, y))
				.transform((x, y, r) -> r.getColor(x, y).grayscale()).toImage();

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
