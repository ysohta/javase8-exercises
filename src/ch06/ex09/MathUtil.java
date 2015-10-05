package ch06.ex09;

import java.util.Arrays;

/**
 * This class consists of utility methods for math calculation.
 * Created by yukiohta on 2015/10/05.
 */
public class MathUtil {

    /**
     * Returns fibonacci number.
     *
     * @param n index
     * @return fibonacci number
     * @throws IllegalArgumentException if n is negative
     */
    public static int fibonacciInParallel(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n must not be negative");

        if (n == 0)
            return 0;

        Matrix f = new Matrix(new int[][]{{1, 1}, {1, 0}});
        Matrix[] mats = new Matrix[n];
        Arrays.parallelSetAll(mats, (i) -> f);
        Arrays.parallelPrefix(mats, Matrix::multiply);

        return mats[n - 1].get(0, 0);
    }
}
