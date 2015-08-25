package ch04.ex09;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is a JavaFX application to show planet orbit path.
 * Created by yukiohta on 2015/08/25.
 */
public class Planet extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        Circle sun = new Circle(50);
        sun.setFill(Color.LIGHTYELLOW);
        sun.setStroke(Color.BLACK);

        Circle planet = new Circle(20);
        planet.setFill(Color.ALICEBLUE);
        planet.setStroke(Color.BLACK);

        Ellipse ellipsePlanet = new Ellipse();
        ellipsePlanet.setCenterX(sun.getTranslateX());
        ellipsePlanet.setCenterY(sun.getTranslateY());
        ellipsePlanet.translateXProperty().bind(sun.translateXProperty());
        ellipsePlanet.translateYProperty().bind(sun.translateYProperty());
        ellipsePlanet.setRadiusX(150);
        ellipsePlanet.setRadiusY(100);
        ellipsePlanet.setRotate(10.0);
        ellipsePlanet.setFill(Color.TRANSPARENT);
        ellipsePlanet.setStroke(Color.BLACK);

        PathTransition transition = new PathTransition();
        transition.setPath(ellipsePlanet);
        transition.setNode(planet);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setDuration(Duration.seconds(5));
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();

        StackPane root = new StackPane();
        root.getChildren().add(ellipsePlanet);
        root.getChildren().add(sun);
        root.getChildren().add(planet);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
