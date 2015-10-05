package ch06.ex09;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex09.MathUtil}.
 * Created by yukiohta on 2015/10/05.
 */
public class MathUtilTest {

    @Test
    public void testFibonacciInParallel() throws Exception {
        assertThat(MathUtil.fibonacciInParallel(0), is(0));
        assertThat(MathUtil.fibonacciInParallel(1), is(1));
        assertThat(MathUtil.fibonacciInParallel(2), is(2));
        assertThat(MathUtil.fibonacciInParallel(3), is(3));
        assertThat(MathUtil.fibonacciInParallel(4), is(5));
        assertThat(MathUtil.fibonacciInParallel(5), is(8));
        assertThat(MathUtil.fibonacciInParallel(6), is(13));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFibonacciInParallelNegative() throws Exception {
        MathUtil.fibonacciInParallel(-1);
    }
}