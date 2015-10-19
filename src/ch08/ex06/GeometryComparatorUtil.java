package ch08.ex06;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.Comparator;

/**
 * This class consists of utility methods to create {@link Comparator} for {@link javafx.geometry} package.
 * Created by yukiohta on 2015/10/19.
 */
public class GeometryComparatorUtil {
    private GeometryComparatorUtil() {
    }

    /**
     * Creates {@link java.util.Comparator} for {@link Point2D}. Priority for ordering is followings;
     * {@link Point2D#getX()} and {@link Point2D#getY()}.
     *
     * @return comparator
     */
    public static Comparator<Point2D> createForPoint2D() {
        return Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY);
    }

    /**
     * Creates {@link java.util.Comparator} for {@link Rectangle2D}. Priority for ordering is followings;
     * {@link Rectangle2D#getMinX()}, {@link Rectangle2D#getMinY()}, {@link Rectangle2D#getWidth()} and
     * {@link Rectangle2D#getHeight()}.
     *
     * @return comparator
     */
    public static Comparator<Rectangle2D> createForRectangle2D() {
        return Comparator.comparing(Rectangle2D::getMinX)
                .thenComparing(Rectangle2D::getMinY)
                .thenComparing(Rectangle2D::getWidth)
                .thenComparing(Rectangle2D::getHeight);
    }
}
