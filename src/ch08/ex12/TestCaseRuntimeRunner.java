package ch08.ex12;

import java.lang.reflect.Method;

/**
 * This class is test runner for Runtime version of {@link ch08.ex12.TestCases}.
 * Created by yukiohta on 2015/10/23.
 */
public class TestCaseRuntimeRunner {
    public static void main(String[] args) throws NoSuchMethodException {
        Method m = MyMath.class.getMethod("factorial", int.class);
        for (TestCase testCase : m.getAnnotationsByType(TestCase.class)) {
            int n = Integer.parseInt(testCase.params());
            long expected = Long.parseLong(testCase.expected());

            long actual = MyMath.factorial(n);

            String result = actual == expected ? "SUCCESS" : "FAILED";
            System.out.printf("TestCase[params=%s expected=%s] %s%n", testCase.params(), testCase.expected(), result);
            System.out.println("expected = " + expected + " actual = " + actual);
        }
    }
}
