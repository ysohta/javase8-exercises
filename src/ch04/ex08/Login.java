package ch04.ex08;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is a JavaFX application to show login window which can notify empty fields.
 * Created by yukiohta on 2015/08/24.
 */
public class Login extends Application implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Initializes a controller.
     *
     * @param location  location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String style = username.getStyle();
        okButton.setOnAction(event -> {
            username.setStyle(username.getText().isEmpty() ? "-fx-border-color: red;" : style);
            password.setStyle(password.getText().isEmpty() ? "-fx-border-color: red;" : style);
        });
        cancelButton.setOnAction(event -> {
            username.clear();
            password.clear();
        });
    }
}
