package ch04.ex02;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * This class consists of methods to handle properties.
 * Created by yukiohta on 2015/08/22.
 */
public class ComponentWithProperties {
    private static final String DEFAULT_TEXT = "";
    private static final double DEFAULT_VALUE = 0.0;
    private static final String DEFAULT_PERSON_NAME = "";
    private static final int DEFAULT_PERSON_AGE = 0;

    private StringProperty text;
    private DoubleProperty value;
    private ListProperty<Person> list;
    private ObjectProperty<Person> obj;
    private Person initialObj = new Person(DEFAULT_PERSON_NAME, DEFAULT_PERSON_AGE);

    /**
     * Returns text property. The instance is created when this method is called on the first time.
     *
     * @return text property
     */
    public final StringProperty textProperty() {
        if (text != null) {
            return text;
        }

        text = new SimpleStringProperty(DEFAULT_TEXT);
        return text;
    }

    /**
     * Gets text of text property.
     *
     * @return text
     */
    public final String getText() {
        if (text == null) {
            return DEFAULT_TEXT;
        }

        return text.get();
    }

    /**
     * Sets text of text property.
     *
     * @param val text
     * @throws NullPointerException if val is null
     */
    public final void setText(String val) {
        Objects.requireNonNull(val, "val must not be null");
        if (text == null) {
            text = new SimpleStringProperty(val);
        } else {
            text.set(val);
        }
    }

    /**
     * Returns value property. The instance is created when this method is called on the first time.
     *
     * @return value property
     */
    public final DoubleProperty valueProperty() {
        if (value != null) {
            return value;
        }

        value = new SimpleDoubleProperty(DEFAULT_VALUE);
        return value;
    }

    /**
     * Gets value of value property.
     *
     * @return text
     */
    public final double getValue() {
        if (value == null) {
            return DEFAULT_VALUE;
        }

        return value.get();
    }

    /**
     * Sets value of value property.
     *
     * @param val value
     */
    public final void setValue(double val) {
        if (value == null) {
            value = new SimpleDoubleProperty(val);
        } else {
            value.set(val);
        }
    }

    /**
     * Returns list property. The instance is created when this method is called on the first time.
     *
     * @return list property
     */
    public final ListProperty<Person> listProperty() {
        if (list != null) {
            return list;
        }

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        return list;
    }

    /**
     * Gets list of list property.
     *
     * @return text
     */
    public final ObservableList<Person> getList() {
        if (list == null) {
            list = new SimpleListProperty<>(FXCollections.observableArrayList());
        }

        return list.get();
    }

    /**
     * Sets list of list property.
     *
     * @param val observable list of persons
     * @throws NullPointerException if val is null
     */
    public final void setList(ObservableList<Person> val) {
        Objects.requireNonNull(val, "val must not be null");
        if (list == null) {
            list = new SimpleListProperty<>(val);
        } else {
            list.set(val);
        }
    }

    /**
     * Returns obj property. The instance is created when this method is called on the first time.
     *
     * @return object property
     */
    public final ObjectProperty<Person> objProperty() {

        if (obj != null) {
            return obj;
        }

        obj = new SimpleObjectProperty<>(initialObj);
        return obj;

    }

    /**
     * Gets object of obj property.
     *
     * @return text
     */
    public final Person getObj() {
        if (obj == null) {
            return initialObj;
        }

        return obj.get();
    }

    /**
     * Sets object of obj property.
     *
     * @param val person
     * @throws NullPointerException if val is null
     */
    public final void setObj(Person val) {
        Objects.requireNonNull(val, "val must not be null");
        if (obj == null) {
            obj = new SimpleObjectProperty<>(val);
        } else {
            obj.set(val);
        }
    }
}

/**
 * This class is for example.
 */
class Person {
    private String name;
    private int age;

    /**
     * Constructs {@link Person} object.
     *
     * @param name name
     * @param age  age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Gets name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
