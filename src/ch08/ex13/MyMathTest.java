package ch08.ex13;

/**
 * This is test class for {@link ch08.ex13.MyMath}.
 * Created by yukiohta on 2015/10/26.
 */
public class MyMathTest {
    @TestCase(params = "4", expected = "24")
    @TestCase(params = "0", expected = "1")
    public static long testFactorial(int n) {
        return MyMath.factorial(n);
    }
}
