package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is a JavaFX application to show simple view.
 * Created by yukiohta on 2015/08/14.
 */
public class TextViewer extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField("Hello, JavaFX");
        Label label = new Label(textField.getText());
        label.setFont(new Font(100));
        textField.textProperty().addListener(
                (property, oldValue, newValue) -> {
                    label.setText(property.getValue());
                }
        );

        primaryStage.setScene(new Scene(new VBox(textField, label)));
        primaryStage.show();
    }
}
