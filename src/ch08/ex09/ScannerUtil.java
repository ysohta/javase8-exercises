package ch08.ex09;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class consists of utility methods to create {@link Stream} for {@link Scanner}.
 * Created by yukiohta on 2015/10/20.
 */
public class ScannerUtil {
    public static Stream<String> lines(Scanner scanner) {
        Objects.requireNonNull(scanner, "scanner must not be null");

        return createStream(new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        });
    }

    public static Stream<String> words(Scanner scanner) {
        Objects.requireNonNull(scanner, "scanner must not be null");

        Scanner wordScanner = scanner.useDelimiter("[\\p{Punct}\\s]+");

        return createStream(new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return wordScanner.hasNext();
            }

            @Override
            public String next() {
                return wordScanner.next();
            }
        });
    }

    public static Stream<Integer> intValues(Scanner scanner) {
        Objects.requireNonNull(scanner, "scanner must not be null");

        return createStream(new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextInt();
            }

            @Override
            public Integer next() {
                return scanner.nextInt();
            }
        });
    }

    public static Stream<Double> doubleValues(Scanner scanner) {
        Objects.requireNonNull(scanner, "scanner must not be null");

        return createStream(new Iterator<Double>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextDouble();
            }

            @Override
            public Double next() {
                return scanner.nextDouble();
            }
        });
    }

    private static <T> Stream<T> createStream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }
}
