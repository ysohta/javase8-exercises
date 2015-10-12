package ch06.ex08;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * This class is a benchmark to compare {@link Arrays#sort(int[])} and {@link Arrays#parallelSort(int[])}.
 * Created by yukiohta on 2015/10/05.
 */
public class ArraysSortComparison {
    public static void main(String... args) {
        Random random = new Random();
        for (int size = 1; size < Math.pow(10, 8); size *= 10) {
            System.out.println(size + " elements");

            // create random data
            IntStream randoms = random.ints(size);
            int[] data1 = randoms.toArray();
            int[] data2 = Arrays.copyOf(data1, data1.length);

            // measure speed
            long startTime, elapsedTime;
            startTime = System.nanoTime();
            Arrays.sort(data1);
            elapsedTime = System.nanoTime() - startTime;
            System.out.printf("%15s:%10d msec%n", "sort", elapsedTime / 1000L);

            startTime = System.nanoTime();
            Arrays.parallelSort(data2);
            elapsedTime = System.nanoTime() - startTime;
            System.out.printf("%15s:%10d msec%n%n", "parallelSort", elapsedTime / 1000L);
        }
    }
}
