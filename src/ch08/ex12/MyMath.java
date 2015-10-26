package ch08.ex12;

/**
 * This is sample class for using {@link ch08.ex12.TestCases}.
 * Created by yukiohta on 2015/10/26.
 */
public class MyMath {
    @TestCase(params = "4", expected = "24")
    @TestCase(params = "0", expected = "1")
    public static long factorial(int n) {
        if (n == 0)
            return 1;

        return factorial(n - 1) * n;
    }
}
