package ch04.ex10;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static javafx.beans.binding.Bindings.equal;

/**
 * This class is a JavaFX application for web browsing.
 * Created by yukiohta on 2015/08/25.
 */
public class WebBrowser extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {

        String locationHome = "http://www.google.com";

        // control
        Button backButton = new Button("back");
        TextField urlBar = new TextField(locationHome);
        urlBar.setMinWidth(500.0);
        WebView browser = new WebView();
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox(backButton, urlBar);
        borderPane.setTop(hBox);
        borderPane.setCenter(browser);
        primaryStage.setScene(new Scene(borderPane));

        // setup engine
        WebEngine engine = browser.getEngine();

        // setup actions
        backButton.disableProperty().bind(equal(0, engine.getHistory().currentIndexProperty()));
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != Worker.State.SUCCEEDED)
                return;

            primaryStage.setTitle(engine.getTitle());
            urlBar.setText(engine.getLocation());
        });

        urlBar.setOnKeyPressed(keyEvent -> {
            if (!keyEvent.getCode().equals(KeyCode.ENTER))
                return;

            engine.load(urlBar.getText());
        });

        backButton.setOnAction(event -> {
            // go back to the previous location
            engine.getHistory().go(-1);
        });

        engine.load(locationHome);
        primaryStage.show();
    }
}
