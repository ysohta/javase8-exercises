package ch08.ex13;

/**
 * This is sample class for using {@link TestCases}.
 * Created by yukiohta on 2015/10/26.
 */
public class MyMath {
    public static long factorial(int n) {
        if (n == 0)
            return 1;

        return factorial(n - 1) * n;
    }
}
