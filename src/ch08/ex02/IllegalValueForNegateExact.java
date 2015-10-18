package ch08.ex02;

/**
 * This class finds illegal value for {@link Math#negateExact(long)}.
 * Created by yukiohta on 2015/10/18.
 */
public class IllegalValueForNegateExact {
    public static void main(String... arg) {
        try {
            // overflow if Long.MIN_VALUE(-2^63)
            Math.negateExact(Long.MIN_VALUE);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
