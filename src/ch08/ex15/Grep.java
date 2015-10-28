package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * This class is light version of UNIX grep command.
 * Created by yukiohta on 2015/10/28.
 */
public class Grep {

    /**
     * Returns all the matched line as String using grep.
     *
     * @param regex regular expression
     * @param paths list of file paths as target
     * @return matched line and the file path
     * @throws IOException              if an I/O error occurs
     * @throws NullPointerException     if regex or paths is null
     * @throws IllegalArgumentException if paths is empty
     */
    public static String exec(String regex, List<Path> paths) throws IOException {
        Objects.requireNonNull(regex, "regex must not be null");
        Objects.requireNonNull(paths, "paths must not be null");

        if (paths.size() == 0)
            throw new IllegalArgumentException("paths must not be empty.");

        Pattern pattern = Pattern.compile(regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (Path p : paths.stream().sorted().collect(Collectors.toList())) {
            Files.lines(p)
                    .filter(pattern.asPredicate())
                    .forEach(s -> stringBuilder.append(String.format("%s: %s%n", p, s)));
        }

        return stringBuilder.toString();
    }

    public static void main(String... args) {
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }

        // verify regex
        String regex = args[0];
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            printUsage();
            System.exit(1);
        }

        List<Path> paths = new ArrayList<>();
        for (int i = 1; i < args.length; i++)
            paths.add(Paths.get(args[i]));

        try {
            System.out.print(exec(regex, paths));
        } catch (IOException e) {
            e.printStackTrace();
            printUsage();
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java ch08.ex15.Grep regex path [path ...]");
    }
}
