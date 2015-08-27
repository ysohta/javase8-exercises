package ch04.ex03;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch04.ex03.ComponentWithProperties}.
 * Created by yukiohta on 2015/08/22.
 */
public class ComponentWithPropertiesTest {
    ComponentWithProperties target;
    boolean isCalled;

    @Before
    public void setup() {
        target = new ComponentWithProperties();
        isCalled = false;
    }

    @Test
    public void testTextProperty() throws Exception {
        target.textProperty().addListener((observable, oldValue, newValue) -> {
            isCalled = true;
        });
        assertThat(isCalled, is(false));

        target.setText("newValue");

        assertThat(isCalled, is(true));
    }

    @Test
    public void testTextPropertyReturnsSameObject() throws Exception {
        StringProperty first = target.textProperty();
        StringProperty second = target.textProperty();

        assertThat(first, sameInstance(second));
    }

    @Test
    public void testGetText() throws Exception {
        assertThat(target.getText(), is(""));

        target.setText("newValue");

        assertThat(target.getText(), is("newValue"));
    }

    @Test
    public void testSetText() throws Exception {
        target.setText("newValue");
        assertThat(target.getText(), is("newValue"));
    }

    @Test
    public void testSetTextEmpty() throws Exception {
        target.setText("");
        assertThat(target.getText(), is(""));
    }

    @Test(expected = NullPointerException.class)
    public void testSetTextNull() throws Exception {
        target.setText(null);
    }

    @Test
    public void testValueProperty() throws Exception {
        target.valueProperty().addListener((observable, oldValue, newValue) -> {
            isCalled = true;
        });
        assertThat(isCalled, is(false));

        target.setValue(100.0);

        assertThat(isCalled, is(true));
    }

    @Test
    public void testValuePropertyReturnsSameObject() throws Exception {
        DoubleProperty first = target.valueProperty();
        DoubleProperty second = target.valueProperty();

        assertThat(first, sameInstance(second));
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(0.0, target.getValue(), 0.01);

        target.setValue(100.0);

        assertEquals(100.0, target.getValue(), 0.01);
    }

    @Test
    public void testSetValue() throws Exception {
        target.setValue(100.0);

        assertEquals(100.0, target.getValue(), 0.01);
    }

    @Test
    public void testListPropertyAsserted() throws Exception {
        target.listProperty().addListener((observable, oldValue, newValue) -> {
            isCalled = true;
        });
        assertThat(isCalled, is(false));

        target.setList(FXCollections.observableList(Arrays.asList(new Person("Daniel", 26))));

        assertThat(isCalled, is(true));
    }

    @Test
    public void testListPropertyAdded() throws Exception {
        target.listProperty().addListener((observable, oldValue, newValue) -> {
            isCalled = true;
        });
        assertThat(isCalled, is(false));

        target.getList().add(new Person("Daniel", 26));

        assertThat(isCalled, is(true));
    }

    @Test
    public void testListPropertyModifiedList() throws Exception {
        Person p = new Person("Daniel", 26);
        ObservableList<Person> list = FXCollections.observableArrayList(Arrays.asList(p));
        target.setList(list);
        target.listProperty().addListener((observable, oldValue, newValue) -> {
            isCalled = true;
        });
        assertThat(isCalled, is(false));

        p.setName("Johnny");
        p.setAge(40);

        assertThat("not fired event if element modified", isCalled, is(false));

        list.remove(p);

        assertThat("fired event if element removed", isCalled, is(true));
    }

    @Test
    public void testListPropertyReturnsSameObject() throws Exception {
        ListProperty first = target.listProperty();
        ListProperty second = target.listProperty();

        assertThat(first, sameInstance(second));
    }

    @Test
    public void testGetList() throws Exception {
        assertThat(target.getList().isEmpty(), is(true));

        Person p = new Person("Daniel", 26);
        target.setList(FXCollections.observableList(Arrays.asList(p)));

        assertThat(target.getList(), hasItem(p));
    }

    @Test
    public void testSetList() throws Exception {
        Person p = new Person("Daniel", 26);
        target.setList(FXCollections.observableList(Arrays.asList(p)));

        assertThat(target.getList(), hasItem(p));
    }

    @Test
    public void testSetListEmpty() throws Exception {
        target.setList(FXCollections.observableList(Collections.emptyList()));
        assertThat(target.getList().isEmpty(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testSetListNull() throws Exception {
        target.setList(null);
    }

    @Test
    public void testObjProperty() throws Exception {
        target.objProperty().addListener((observable, oldValue, newValue) -> {
            isCalled = true;
        });
        assertThat(isCalled, is(false));

        target.setObj(new Person("Daniel", 26));

        assertThat(isCalled, is(true));
    }

    @Test
    public void testObjPropertyReturnsSameObject() throws Exception {
        ObjectProperty<Person> first = target.objProperty();
        ObjectProperty<Person> second = target.objProperty();

        assertThat(first, sameInstance(second));
    }

    @Test
    public void testGetObj() throws Exception {
        Person actual = target.getObj();
        assertThat(actual.getName(), is(""));
        assertThat(actual.getAge(), is(0));

        Person p = new Person("Daniel", 26);
        target.setObj(p);

        actual = target.getObj();
        assertThat(actual.getName(), is("Daniel"));
        assertThat(actual.getAge(), is(26));
    }

    @Test
    public void testSetObj() throws Exception {
        Person p = new Person("Daniel", 26);
        target.setObj(p);

        Person actual = target.getObj();
        assertThat(actual.getName(), is("Daniel"));
        assertThat(actual.getAge(), is(26));
    }
}