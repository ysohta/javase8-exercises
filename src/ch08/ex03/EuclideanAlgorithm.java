package ch08.ex03;

/**
 * This class represents Euclidean Algorithm to calculate the greatest common divisor.
 * Created by yukiohta on 2015/10/18.
 */
public abstract class EuclideanAlgorithm {

    /**
     * Returns object to calculate the greatest common divisor.
     * {@code %} is used for the calculation of remainder.
     *
     * @return algorithm
     */
    public static EuclideanAlgorithm createByRemainder() {
        return new EuclideanAlgorithm() {
            @Override
            protected int rem(int a, int b) {
                return a % b;
            }
        };
    }

    /**
     * Returns object to calculate the greatest common divisor.
     * {@link Math#floorMod(int, int)} is used for the calculation of remainder.
     *
     * @return algorithm
     */
    public static EuclideanAlgorithm createByFloorMod() {
        return new EuclideanAlgorithm() {
            @Override
            protected int rem(int a, int b) {
                return Math.floorMod(a, b);
            }
        };
    }

    /**
     * Returns object to calculate the greatest common divisor.
     * Arithmetic calculation is used for the remainder.
     *
     * @return algorithm
     */
    public static EuclideanAlgorithm createByAlithmeticRemainder() {
        return new EuclideanAlgorithm() {
            @Override
            protected int rem(int a, int b) {
                int x = Math.abs(a);
                int y = Math.abs(b);

                return x - (x / y) * y;
            }
        };
    }

    /**
     * Returns value of the greatest common divisor.
     *
     * @param a first
     * @param b second
     * @return greatest common divisor
     */
    public int gcd(int a, int b) {
        if (b == 0)
            return a;

        // implementation of rem methods is depending on sub-class
        return gcd(b, rem(a, b));
    }

    /**
     * Returns remainder.
     *
     * @param a dividend
     * @param b divisor
     * @return remainder
     */
    protected abstract int rem(int a, int b);
}
