package ch08.ex01;

/**
 * This class consists of utility methods to calculate unsigned integer.
 * Created by yukiohta on 2015/10/18.
 */
public class UnsignedIntCalculator {
    private UnsignedIntCalculator() {
    }

    /**
     * Adds two values.
     *
     * @param x first
     * @param y second
     * @return added value
     */
    public static int add(int x, int y) {
        return Integer.sum(x, y);
    }

    /**
     * Subtracts a value from the other.
     *
     * @param x first
     * @param y second
     * @return subtracted value
     */
    public static int subtract(int x, int y) {
        return Integer.sum(x, -y);
    }

    /**
     * Divides a value from the other.
     *
     * @param dividend dividend
     * @param divisor  divisor
     * @return divided value
     * @throws ArithmeticException if divisor is zero
     */
    public static int divide(int dividend, int divisor) {
        return Integer.divideUnsigned(dividend, divisor);
    }

    /**
     * Compares two values as unsigned integer.
     *
     * @param x first
     * @param y second
     * @return {@code 0} if {@code x == y};
     * a value less than {@code 0} if {@code x < y} as unsigned values;
     * and a value greater than {@code 0} if {@code x > y} as unsigned values
     */
    public static int compare(int x, int y) {
        return Integer.compareUnsigned(x, y);
    }
}
