package ch06.ex09;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex09.Matrix}.
 * Created by yukiohta on 2015/10/05.
 */
public class MatrixTest {

    @Test(expected = NullPointerException.class)
    public void testMatrixNullData() {
        new Matrix(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixEmptyData() {
        new Matrix(new int[][]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixTriangular() {
        new Matrix(new int[][]{{1, 2}, {3}});
    }

    @Test
    public void testGet() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}, {3, 4}});
        assertThat(target.get(0, 0), is(1));
        assertThat(target.get(0, 1), is(2));
        assertThat(target.get(1, 0), is(3));
        assertThat(target.get(1, 1), is(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalid1() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}, {3, 4}});
        target.get(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalid2() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}, {3, 4}});
        target.get(2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalid3() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}, {3, 4}});
        target.get(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalid4() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}, {3, 4}});
        target.get(0, 2);
    }

    @Test
    public void testGetRow() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}});
        assertThat(target.getRow(), is(1));
    }

    @Test
    public void testGetCol() throws Exception {
        Matrix target = new Matrix(new int[][]{{1, 2}});
        assertThat(target.getCol(), is(2));
    }

    @Test
    public void testMultiply() throws Exception {
        Matrix a = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new int[][]{{2, 0}, {1, 2}});

        Matrix actual = a.multiply(b);
        assertThat(actual.get(0, 0), is(4));
        assertThat(actual.get(0, 1), is(4));
        assertThat(actual.get(1, 0), is(10));
        assertThat(actual.get(1, 1), is(8));
    }

    @Test(expected = NullPointerException.class)
    public void testMultiplyNull() throws Exception {
        Matrix a = new Matrix(new int[][]{{1, 2}, {3, 4}});

        a.multiply(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyInvalidMatrix() throws Exception {
        Matrix a = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new int[][]{{2, 0}});

        a.multiply(b);
    }
}