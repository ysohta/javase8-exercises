package ch03.ex13;

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

		Image finalImage = LatentImage.from(image)
		/* edge detection */
		.transform((x, y, r) -> {
			IntermediateColor c = IntermediateColor.from(r.getColor(x, y)).multiply(4.0);
			for (int i = x - 1; i <= x + 1; i += 2) {
				int j = y;

				if (i < 0 || i >= image.getWidth() || j < 0 || j >= image.getHeight()) {
					continue;
				}

				c = c.subtract(IntermediateColor.from(r.getColor(i, j)));
			}

			for (int j = y - 1; j <= y + 1; j += 2) {
				int i = x;

				if (i < 0 || i >= image.getWidth() || j < 0 || j >= image.getHeight()) {
					continue;
				}

				c = c.subtract(IntermediateColor.from(r.getColor(i, j)));
			}

			return c.toColor();
		})
		/* blurring */
		.transform((x, y, r) -> {
			IntermediateColor c = IntermediateColor.from(Color.BLACK);
			int neibors = 1;
			int cnt = 0;
			for (int i = x - neibors; i <= x + neibors; i++) {
				for (int j = y - neibors; j <= y + neibors; j++) {
					if (i < 0 || i >= image.getWidth() || j < 0 || j >= image.getHeight()) {
						continue;
					}

					c = c.add(IntermediateColor.from(r.getColor(i, j)));
					cnt++;
				}
			}

			return c.devide(cnt).toColor();
		}).toImage();

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
