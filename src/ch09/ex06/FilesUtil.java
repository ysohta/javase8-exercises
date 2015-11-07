package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class consists of utility methods for {@link Files}.
 * Created by yukiohta on 2015/11/08.
 */
public class FilesUtil {
    private FilesUtil() {
    }

    /**
     * Writes the lines reversely.
     *
     * @param in  input file
     * @param out output file
     * @throws IOException          if an I/O error occurs.
     * @throws NullPointerException if in or out is null.
     */
    public static void writeReversedLine(Path in, Path out) throws IOException {
        Objects.requireNonNull(in, "in must not be null");
        Objects.requireNonNull(out, "out must not be null");

        List<String> lines = Files.readAllLines(in);

        Collections.reverse(lines);

        Files.write(out, lines);
    }
}
