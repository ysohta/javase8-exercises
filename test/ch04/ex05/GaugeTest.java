package ch04.ex05;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch04.ex05.Gauge}.
 * Created by yukiohta on 2015/08/23.
 */
public class GaugeTest {

    @Test
    public void testObserveAddChangeListener() throws Exception {
        IntegerProperty integerProperty = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe(Object::toString, integerProperty);

        actual.addListener((observable, oldValue, newValue) -> {
        });

        integerProperty.set(3);
        assertThat(actual.getValue(), is("3"));
    }

    @Test
    public void testObserveAddInvalidateListener() throws Exception {
        IntegerProperty integerProperty = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe(Object::toString, integerProperty);

        actual.addListener(observable -> {
        });

        integerProperty.set(3);
        assertThat(actual.getValue(), is("3"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testObserveRemoveChangeListener() throws Exception {
        IntegerProperty integerProperty = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe(Object::toString, integerProperty);

        actual.removeListener((observable, oldValue, newValue) -> {
        });
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testObserveRemoveInvalidateListener() throws Exception {
        IntegerProperty integerProperty = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe(Object::toString, integerProperty);

        actual.removeListener(observable -> {
        });
    }

    @Test(expected = NullPointerException.class)
    public void testObserveNullPointerExceptionF() throws Exception {
        Gauge.observe(null, new SimpleStringProperty());
    }

    @Test(expected = NullPointerException.class)
    public void testObserveNullPointerExceptionT() throws Exception {
        Gauge.observe(o -> 0, null);
    }

    @Test
    public void testObserve1AddChangeListener() throws Exception {
        IntegerProperty integerProperty1 = new SimpleIntegerProperty(1);
        IntegerProperty integerProperty2 = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe((t, u) -> Integer.valueOf(t.intValue() + u.intValue()).toString(), integerProperty1, integerProperty2);

        actual.addListener((observable, oldValue, newValue) -> {
        });

        integerProperty1.set(2);
        integerProperty2.set(3);
        assertThat(actual.getValue(), is("5"));
    }

    @Test
    public void testObserve1AddInvalidateListener() throws Exception {
        IntegerProperty integerProperty1 = new SimpleIntegerProperty(1);
        IntegerProperty integerProperty2 = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe((t, u) -> Integer.valueOf(t.intValue() + u.intValue()).toString(), integerProperty1, integerProperty2);

        actual.addListener(observable -> {
        });

        integerProperty1.set(2);
        integerProperty2.set(3);
        assertThat(actual.getValue(), is("5"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testObserve1RemoveChangeListener() throws Exception {
        IntegerProperty integerProperty1 = new SimpleIntegerProperty(1);
        IntegerProperty integerProperty2 = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe((t, u) -> Integer.valueOf(t.intValue() + u.intValue()).toString(), integerProperty1, integerProperty2);

        actual.removeListener((observable, oldValue, newValue) -> {
        });
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testObserve1RemoveInvalidateListener() throws Exception {
        IntegerProperty integerProperty1 = new SimpleIntegerProperty(1);
        IntegerProperty integerProperty2 = new SimpleIntegerProperty(1);
        ObservableValue<String> actual = Gauge.observe((t, u) -> Integer.valueOf(t.intValue() + u.intValue()).toString(), integerProperty1, integerProperty2);

        actual.removeListener(observable -> {
        });
    }

    @Test(expected = NullPointerException.class)
    public void testObserve1NullPointerExceptionF() throws Exception {
        Gauge.observe(null, new SimpleStringProperty(), new SimpleStringProperty());
    }

    @Test(expected = NullPointerException.class)
    public void testObserve1NullPointerExceptionT() throws Exception {
        Gauge.observe((o, o2) -> 0, null, new SimpleStringProperty());
    }

    @Test(expected = NullPointerException.class)
    public void testObserve1NullPointerExceptionU() throws Exception {
        Gauge.observe((o, o2) -> 0, new SimpleStringProperty(), null);
    }
}