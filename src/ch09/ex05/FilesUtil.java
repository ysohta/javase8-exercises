package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * This class consists of utility methods for {@link Files}.
 * Created by yukiohta on 2015/10/30.
 */
public class FilesUtil {
    private FilesUtil() {
    }

    /**
     * Writes the characters reversely.
     *
     * @param in  input file
     * @param out output file
     * @throws IOException          if an I/O error occurs.
     * @throws NullPointerException if in or out is null.
     */
    public static void writeReversedChar(Path in, Path out) throws IOException {
        Objects.requireNonNull(in, "in must not be null");
        Objects.requireNonNull(out, "out must not be null");

        byte[] bytes = Files.readAllBytes(in);

        byte[] copied = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            copied[copied.length - i - 1] = bytes[i];
        }

        Files.write(out, copied);
    }
}
