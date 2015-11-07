package ch09.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * This class consists of utility methods for {@link Files}.
 * Created by yukiohta on 2015/11/08.
 */
public class FilesUtil {
    private FilesUtil() {
    }

    /**
     * Writes content of url to the file.
     *
     * @param url URL to read content
     * @param out output file
     * @throws IOException          if an I/O error occurs.
     * @throws NullPointerException if url or out is null
     */
    public static void writeWebContent(URL url, Path out) throws IOException {
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(out, "out must not be null");

        Files.copy(url.openStream(), out);
    }
}
