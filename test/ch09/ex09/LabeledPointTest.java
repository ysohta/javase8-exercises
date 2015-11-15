package ch09.ex09;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * This is test class of {@link LabeledPoint}.
 * Created by yukiohta on 2015/11/15.
 */
public class LabeledPointTest {
    @Test
    public void testEquals() throws Exception {
        LabeledPoint p1 = new LabeledPoint("label", 1, 2);
        LabeledPoint p2 = new LabeledPoint("label", 1, 2);
        assertNotSame(p1, p2);
        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));

        assertFalse(p1.equals(null));
    }

    @Test
    public void testEqualsEachField() throws Exception {
        LabeledPoint p1 = new LabeledPoint("label", 1, 2);
        LabeledPoint p2 = new LabeledPoint("LABEL", 1, 2);
        LabeledPoint p3 = new LabeledPoint("label", 1, 0);
        LabeledPoint p4 = new LabeledPoint("label", 0, 2);

        assertFalse(p1.equals(p2));
        assertFalse(p2.equals(p1));

        assertFalse(p1.equals(p3));
        assertFalse(p3.equals(p1));

        assertFalse(p1.equals(p4));
        assertFalse(p4.equals(p1));
    }

    @Test
    public void testHashCode() throws Exception {
        LabeledPoint p1 = new LabeledPoint("label", 1, 2);
        LabeledPoint p2 = new LabeledPoint("label", 1, 2);

        assertThat(p1.hashCode(), is(p2.hashCode()));
    }
}