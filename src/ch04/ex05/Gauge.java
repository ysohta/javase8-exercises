package ch04.ex05;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class is a JavaFX application to show gauge with buttons to adjust.
 * Created by yukiohta on 2015/08/23.
 */
public class Gauge extends Application {

    /**
     * Entry point of JavaFX application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        Button smaller = new Button("Smaller");
        Button larger = new Button("Larger");
        Rectangle gauge = new Rectangle(0, 5, 50, 15);
        Rectangle outline = new Rectangle(0, 5, 100, 15);
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        Pane pane = new Pane();
        pane.getChildren().addAll(gauge, outline);
        Label label = new Label(Double.toString(gauge.getWidth()));
        label.setPrefWidth(50.0);
        label.setPrefHeight(25.0);
        label.setAlignment(Pos.BASELINE_CENTER);

        // setup property
        smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
        larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));

        // observe method
        smaller.disableProperty().bind(observe(t -> t.doubleValue() <= 0, gauge.widthProperty()));
        larger.disableProperty().bind(observe(t -> t.doubleValue() >= 100, gauge.widthProperty()));

        HBox box = new HBox(10);
        box.getChildren().addAll(smaller, pane, label, larger);
        Scene scene = new Scene(box);

        // observe method
        gauge.fillProperty().bind(
                observe((t, u) -> (t.doubleValue() > 400.0 || u.doubleValue() > 400.0) ? Color.RED : Color.BLACK,
                        scene.widthProperty(), scene.heightProperty()));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns observable value of R converted by the function.
     *
     * @param f   function
     * @param t   original value
     * @param <T> the type of observable value
     * @param <R> the type of observable value to convert
     * @return converted observable
     * @throws NullPointerException if f or t is null
     */
    public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t) {
        Objects.requireNonNull(f, "f must not be null");
        Objects.requireNonNull(t, "t must not be null");

        return new ObservableValue<R>() {
            @Override
            public void addListener(ChangeListener<? super R> listener) {
                t.addListener((observable, oldValue, newValue) -> {
                    ObservableValue<R> observableValue = Bindings.createObjectBinding(() -> f.apply(t.getValue()), t);
                    listener.changed(observableValue, f.apply(oldValue), f.apply(newValue));
                });
            }

            @Override
            public void removeListener(ChangeListener<? super R> listener) {
                throw new UnsupportedOperationException();
            }

            @Override
            public R getValue() {
                return f.apply(t.getValue());
            }

            @Override
            public void addListener(InvalidationListener listener) {
                t.addListener(listener);
            }

            @Override
            public void removeListener(InvalidationListener listener) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Returns observable value of R converted by the function.
     *
     * @param f   function
     * @param t   first original value
     * @param u   second original value
     * @param <T> the type of first observable value
     * @param <U> the type of second observable value
     * @param <R> the type of observable value to convert
     * @return converted observable
     * @throws NullPointerException if f, t or u is null
     */
    public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
        Objects.requireNonNull(f, "f must not be null");
        Objects.requireNonNull(t, "t must not be null");
        Objects.requireNonNull(u, "u must not be null");

        return new ObservableValue<R>() {
            @Override
            public void addListener(ChangeListener<? super R> listener) {
                t.addListener((observable1, oldValue1, newValue1) -> {
                });
                u.addListener((observable1, oldValue1, newValue1) -> {
                });
            }

            @Override
            public void removeListener(ChangeListener<? super R> listener) {
                throw new UnsupportedOperationException();

            }

            @Override
            public R getValue() {
                return f.apply(t.getValue(), u.getValue());
            }

            @Override
            public void addListener(InvalidationListener listener) {
                t.addListener(listener);
                u.addListener(listener);
            }

            @Override
            public void removeListener(InvalidationListener listener) {
                throw new UnsupportedOperationException();
            }
        };
    }
}
