package ch09.ex08;


import java.util.Objects;

/**
 * This class represents geometric point.
 * Created by yukiohta on 2015/11/15.
 */
public class Point implements Comparable<Point> {
    private int x;
    private int y;

    /**
     * Constructs point.
     *
     * @param x x
     * @param y y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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
     * Compares other point object.
     *
     * @param other other point
     * @return 0 if other point is the same.
     * Positive value if this object is larger.
     * Negative value if other object is larger.
     * @throws NullPointerException if other is null
     */
    @Override
    public int compareTo(Point other) {
        Objects.requireNonNull(other, "other must not be null");

        int diff = compareInt(x, other.x);

        if (diff != 0) {
            return diff;
        }

        return compareInt(y, other.y);
    }

    private static int compareInt(int val1, int val2) {
        if (val1 > val2)
            return 1;
        if (val1 < val2)
            return -1;

        return 0;
    }
}
