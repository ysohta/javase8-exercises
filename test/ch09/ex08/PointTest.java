package ch09.ex08;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * This is test class of {@link ch09.ex08.Point}.
 * Created by yukiohta on 2015/11/15.
 */
public class PointTest {

    @Test
    public void testCompareTo() throws Exception {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(0, 1);

        assertThat(p1.compareTo(p1), is(0));

        // different x
        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p1) > 0);

        // same x, different y
        assertTrue(p1.compareTo(p3) < 0);
        assertTrue(p3.compareTo(p1) > 0);
    }

    @Test
    public void testCompareToLargeNumber() throws Exception {
        Point p1 = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point p2 = new Point(Integer.MIN_VALUE, Integer.MAX_VALUE);
        Point p3 = new Point(Integer.MAX_VALUE, Integer.MIN_VALUE);
        Point p4 = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

        Point[] points = new Point[]{p1, p2, p3, p4};
        Arrays.sort(points);

        assertArrayEquals(points, new Point[]{p4, p2, p3, p1});
    }

    @Test(expected = NullPointerException.class)
    public void testCompareNull() throws Exception {
        new Point(0, 0).compareTo(null);
    }
}