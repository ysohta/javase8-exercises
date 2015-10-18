package ch08.ex01;

import org.junit.Test;

import static ch08.ex01.UnsignedIntCalculator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch08.ex01.UnsignedIntCalculator}.
 * Created by yukiohta on 2015/10/18.
 */
public class UnsignedIntCalculatorTest {

    @Test
    public void testAdd() throws Exception {
        assertThat(add(1, 2), is(3));
        assertThat(add(0, 0), is(0));
        assertThat(add(Integer.MAX_VALUE, 1), is(Integer.parseUnsignedInt("80000000", 16)));
        assertThat(add(Integer.parseUnsignedInt("FFFFFFFF", 16), 1), is(0));
    }

    @Test
    public void testSubtract() throws Exception {
        assertThat(subtract(3, 1), is(2));
        assertThat(subtract(0, 0), is(0));
        assertThat(subtract(Integer.parseUnsignedInt("80000000", 16), 1),
                is(Integer.MAX_VALUE));
        assertThat(subtract(Integer.parseUnsignedInt("FFFFFFFF", 16), Integer.MAX_VALUE),
                is(Integer.parseUnsignedInt("80000000", 16)));
    }

    @Test
    public void testDivide() throws Exception {
        assertThat(divide(6, 2), is(3));
        assertThat(divide(Integer.parseUnsignedInt("FFFFFFFF", 16), 2), is(Integer.parseUnsignedInt("7FFFFFFF", 16)));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideZeroDivisor() throws Exception {
        divide(3, 0);
    }

    @Test
    public void testSignedIntDivide() throws Exception {
        // different from results of UnsignedIntCalculator.divide
        assertThat(Integer.parseUnsignedInt("FFFFFFFF", 16) / 2, is(0));
    }

    @Test
    public void testCompare() throws Exception {
        assertThat(compare(3, 1) > 0, is(true));
        assertThat(compare(Integer.parseUnsignedInt("FFFFFFFF", 16), 0) > 0, is(true));
        assertThat(compare(0, Integer.parseUnsignedInt("FFFFFFFF", 16)) < 0, is(true));
    }

    @Test
    public void testSignedIntCompare() throws Exception {
        // different from results of UnsignedIntCalculator.compare
        assertThat(Integer.compare(Integer.parseUnsignedInt("FFFFFFFF", 16), 0) > 0, is(false));
        assertThat(Integer.compare(0, Integer.parseUnsignedInt("FFFFFFFF", 16)) < 0, is(false));
    }
}