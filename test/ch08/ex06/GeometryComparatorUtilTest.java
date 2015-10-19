package ch08.ex06;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch08.ex06.GeometryComparatorUtil}.
 * Created by yukiohta on 2015/10/19.
 */
public class GeometryComparatorUtilTest {

    @Test
    public void testCreateForPoint2D() throws Exception {
        Point2D p1 = new Point2D(1.0, 0.0);
        Point2D p2 = new Point2D(0.0, 1.0);
        Point2D p3 = new Point2D(0.0, 0.0);
        Point2D p4 = new Point2D(1.0, 1.0);
        Point2D[] points = {p1, p2, p3, p4};

        Arrays.sort(points, GeometryComparatorUtil.createForPoint2D());

        int index = 0;
        assertThat(points[index++], is(p3));
        assertThat(points[index++], is(p2));
        assertThat(points[index++], is(p1));
        assertThat(points[index++], is(p4));
    }

    @Test
    public void testCreateForRectangle2D() throws Exception {
        Rectangle2D r1 = new Rectangle2D(2.0, 0.0, 4.0, 3.0);
        Rectangle2D r2 = new Rectangle2D(0.0, 2.0, 4.0, 3.0);
        Rectangle2D r3 = new Rectangle2D(0.0, 2.0, 4.0, 5.0);
        Rectangle2D r4 = new Rectangle2D(0.0, 0.0, 2.0, 5.0);
        Rectangle2D r5 = new Rectangle2D(0.0, 0.0, 4.0, 5.0);
        Rectangle2D r6 = new Rectangle2D(0.0, 0.0, 4.0, 3.0);
        Rectangle2D[] rectangles = {r1, r2, r3, r4, r5, r6};

        Arrays.sort(rectangles, GeometryComparatorUtil.createForRectangle2D());

        int index = 0;
        assertThat(rectangles[index++], is(r4));
        assertThat(rectangles[index++], is(r6));
        assertThat(rectangles[index++], is(r5));
        assertThat(rectangles[index++], is(r2));
        assertThat(rectangles[index++], is(r3));
        assertThat(rectangles[index++], is(r1));
    }
}