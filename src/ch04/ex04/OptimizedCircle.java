package ch04.ex04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static javafx.beans.binding.Bindings.divide;
import static javafx.beans.binding.Bindings.min;

/**
 * This class is a JavaFX application to show circle optimized by the window size.
 * Created by yukiohta on 2015/08/23.
 */

public class OptimizedCircle extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle();
        Scene scene = new Scene(new StackPane(circle));

        // place center of scene
        circle.centerXProperty().bind(divide(scene.widthProperty(), 2));

        // resize to fit the size of scene
        circle.radiusProperty().bind(divide(min(scene.widthProperty(), scene.heightProperty()), 2));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
