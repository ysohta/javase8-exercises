package ch09.ex10;

import java.util.Objects;

/**
 * This class represents geometric point with text.
 * Created by yukiohta on 2015/11/15.
 */
public class LabeledPoint implements Comparable<LabeledPoint> {
    private String label;
    private int x;
    private int y;

    /**
     * Constructs labeled point.
     *
     * @param label text for the label
     * @param x     x
     * @param y     y
     * @throws NullPointerException if label is null.
     */
    public LabeledPoint(String label, int x, int y) {
        Objects.requireNonNull(label, "label must not be null");

        this.label = label;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns label
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets label.
     *
     * @param label label
     */
    public void setLabel(String label) {
        Objects.requireNonNull(label, "label must not be null");

        this.label = label;
    }

    /**
     * Returns x value.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x value.
     *
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns y value.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y value
     *
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns {@code true} if other object has same values.
     *
     * @param o other object
     * @return true if other object has same values.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabeledPoint that = (LabeledPoint) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return !(label != null ? !label.equals(that.label) : that.label != null);
    }

    /**
     * Returns hash value.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    /**
     * Compares other point object.
     *
     * @param o other point
     * @return 0 if other point is the same.
     * Positive value if this object is larger.
     * Negative value if other object is larger.
     * @throws NullPointerException if other is null
     */
    @Override
    public int compareTo(LabeledPoint o) {
        Objects.requireNonNull(o, "o must not be null");

        int diff;

        diff = label.compareTo(o.label);
        if (diff != 0) {
            return diff;
        }

        diff = Integer.compare(x, o.x);
        if (diff != 0) {
            return diff;
        }

        return Integer.compare(y, o.y);
    }
}
