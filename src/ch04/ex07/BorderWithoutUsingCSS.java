package ch04.ex07;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is a JavaFX application to show control border without using CSS.
 * Created by yukiohta on 2015/08/24.
 */
public class BorderWithoutUsingCSS extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        final double rem = new Text().getLayoutBounds().getHeight();
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(0.8 * rem));
        pane.setHgap(0.8 * rem);
        pane.setVgap(0.8 * rem);

        Label usernameLabel = new Label("User name:");
        Label passwordLabel = new Label("Password:");
        pane.add(usernameLabel, 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(passwordLabel, 0, 1);
        pane.add(new TextField(), 1, 1);
        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);

        HBox buttons = new HBox(new Button("OK"), new Button("Cancel"));
        buttons.setSpacing(0.8 * rem);
        buttons.setAlignment(Pos.CENTER);

        // show border without using CSS
        buttons.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));

        pane.add(buttons, 0, 2, 2, 1);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
