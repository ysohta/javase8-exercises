package ch08.ex13;

public class TestRunner {
    public static void main(String... args) {
        long expected, actual;
        int param;
        expected = 24;
        param = 4;
        actual = MyMathTest.testFactorial(param);
        System.out.print("expected=" + expected + " actual=" + actual);
        System.out.println(actual == expected ? "[SUCCESS]" : "[FAILED]");

        expected = 1;
        param = 0;
        actual = MyMathTest.testFactorial(param);
        System.out.print("expected=" + expected + " actual=" + actual);
        System.out.println(actual == expected ? "[SUCCESS]" : "[FAILED]");

    }
}
