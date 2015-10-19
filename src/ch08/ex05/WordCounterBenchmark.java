package ch08.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is a benchmark to compare {@link Stream#filter(Predicate)} and {@link java.util.Collection#removeIf(Predicate)}.
 * Created by yukiohta on 2015/10/19.
 */
public class WordCounterBenchmark {
    public static void main(String... args) throws IOException {
        long startTime, elapsedTime, cnt;

        List<String> words = Files.readAllLines(Paths.get("res/pg2600.txt")).stream()
                .map((s) -> s.split("[\\p{Punct}\\s]+")).flatMap(Arrays::stream).collect(Collectors.toList());

        // Stream
        startTime = System.nanoTime();
        cnt = words.stream().filter(s -> s.length() > 12).count();
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Stream: cnt=" + cnt);
        System.out.printf("%10d nsec%n", elapsedTime);

        // Collection
        startTime = System.nanoTime();
        words.removeIf(s -> s.length() <= 12);
        cnt = words.size();
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Collection: cnt=" + cnt);
        System.out.printf("%10d nsec%n", elapsedTime);
    }
}
