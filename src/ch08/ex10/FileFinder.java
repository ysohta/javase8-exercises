package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class consists of utility methods to find files by the condition.
 * Created by yukiohta on 2015/10/21.
 */
public class FileFinder {
    private FileFinder() {
    }

    public static void main(String... args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java ch08.ex10.FileFinder path word [word ...]");
            System.exit(1);
        }

        Path start = Paths.get(args[0]);
        List<String> words = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));
        filesContaining(start, words).forEach(System.out::println);
    }

    /**
     * Finds the files containing the words in the path. The files are searched recursively.
     *
     * @param start path of directory or file
     * @param words words to be searched
     * @return listed files containing one of the words at least
     * @throws IOException              an I/O error is thrown when accessing the starting file
     * @throws NullPointerException     if start or words is null
     * @throws IllegalArgumentException if words is empty
     */
    public static List<Path> filesContaining(Path start, List<String> words) throws IOException {
        Objects.requireNonNull(start, "start must not be null");
        Objects.requireNonNull(words, "words must not be null");
        if (words.isEmpty())
            throw new IllegalArgumentException("words must not be empty");

        return Files.walk(start)
                .filter(path -> {
                    if (Files.isDirectory(path))
                        return false;

                    boolean match = false;
                    try {
                        match = Files.lines(path).anyMatch(line -> containsAnyWord(line, words));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return match;
                }).collect(Collectors.toList());
    }

    private static boolean containsAnyWord(String str, List<String> words) {
        return words.stream().anyMatch(w -> str.contains(w));
    }
}
