package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This class is a JavaFX application to show buttons in border layout.
 * Created by yukiohta on 2015/08/24.
 */
public class LayoutButtons extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        // put Top button into HBox
        HBox hBox1 = new HBox(new Button("Top"));
        hBox1.setAlignment(Pos.CENTER);
        pane.setTop(hBox1);

        pane.setLeft(new Button("Left"));
        pane.setCenter(new Button("Center"));
        pane.setRight(new Button("Right"));

        // put Bottom button into HBox
        HBox hBox2 = new HBox(new Button("Bottom"));
        hBox2.setAlignment(Pos.CENTER);
        pane.setBottom(hBox2);

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
