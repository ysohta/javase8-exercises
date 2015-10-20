package ch08.ex09;

import org.junit.Test;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

/**
 * This is test class of {@link ch08.ex09.ScannerUtil}.
 * Created by yukiohta on 2015/10/20.
 */
public class ScannerUtilTest {

    @Test
    public void testLines() throws Exception {
        try (Scanner scanner = new Scanner("This is first line.\nThis is second line!!\n")) {
            Stream<String> actual = ScannerUtil.lines(scanner);

            String[] actuals = actual.toArray(String[]::new);
            assertArrayEquals(new String[]{"This is first line.", "This is second line!!",}, actuals);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testLinesNullScanner() throws Exception {
        ScannerUtil.lines(null);
    }

    @Test
    public void testWords() throws Exception {
        try (Scanner scanner = new Scanner("This is first line.\nThis is second line!!\n")) {
            Stream<String> actual = ScannerUtil.words(scanner);

            String[] actuals = actual.toArray(String[]::new);
            assertArrayEquals(new String[]{"This", "is", "first", "line", "This", "is", "second", "line",}, actuals);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testWordsNullScanner() throws Exception {
        ScannerUtil.words(null);
    }

    @Test
    public void testIntValues() throws Exception {
        try (Scanner scanner = new Scanner("1 2 3 -4")) {
            Stream<Integer> actual = ScannerUtil.intValues(scanner);

            Integer[] actuals = actual.toArray(Integer[]::new);
            assertArrayEquals(new Integer[]{1, 2, 3, -4,}, actuals);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testIntValuesNullScanner() throws Exception {
        ScannerUtil.intValues(null);
    }

    @Test
    public void testDoubleValues() throws Exception {
        try (Scanner scanner = new Scanner("1.0 2.0 3.0 -4.0")) {
            Stream<Double> actual = ScannerUtil.doubleValues(scanner);

            Double[] actuals = actual.toArray(Double[]::new);
            assertArrayEquals(new Double[]{1.0, 2.0, 3.0, -4.0,}, actuals);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testDoubleValuesNullScanner() throws Exception {
        ScannerUtil.doubleValues(null);
    }
}