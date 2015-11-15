package ch09.ex10;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is test class of {@link LabeledPoint}.
 * Created by yukiohta on 2015/11/15.
 */
public class LabeledPointTest {
    @Test
    public void testCompare() throws Exception {
        LabeledPoint p1 = new LabeledPoint("label", 0, 0);
        LabeledPoint p2 = new LabeledPoint("label", 0, 0);
        LabeledPoint p3 = new LabeledPoint("l", 0, 0);

        assertTrue(p1.compareTo(p1) == 0);

        assertTrue(p1.compareTo(p2) == 0);
        assertTrue(p2.compareTo(p1) == 0);

        assertTrue(p1.compareTo(p3) < 0);
        assertTrue(p3.compareTo(p1) > 0);
    }

    @Test
    public void testCompareToSort() throws Exception {
        LabeledPoint p1 = new LabeledPoint("label", 0, 0);
        LabeledPoint p2 = new LabeledPoint("lab", 0, 0);
        LabeledPoint p3 = new LabeledPoint("label", 1, 0);
        LabeledPoint p4 = new LabeledPoint("label", 0, 1);
        LabeledPoint p5 = new LabeledPoint("label", -1, 0);

        LabeledPoint[] points = new LabeledPoint[]{p1, p2, p3, p4, p5};
        Arrays.sort(points);

        assertArrayEquals(new LabeledPoint[]{p2, p5, p1, p3, p4}, points);
    }

    @Test(expected = NullPointerException.class)
    public void testCompareNull() throws Exception {
        new LabeledPoint("", 0, 0).compareTo(null);
    }
}