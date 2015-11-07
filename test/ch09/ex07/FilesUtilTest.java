package ch09.ex07;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * This is test class of {@link ch09.ex07.FilesUtil}.
 * Created by yukiohta on 2015/11/08.
 */
public class FilesUtilTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Must have internet connection.
     *
     * @throws Exception
     */
    @Test(timeout = 10_000)
    public void testWriteWebContent() throws Exception {
        URL url = new URL("http://horstmann.com");
        Path out = folder.getRoot().toPath().resolve("out.html");

        FilesUtil.writeWebContent(url, out);

        assertTrue(Files.exists(out));
    }

    @Test
    public void testWriteWebContentLocalFile() throws Exception {
        Path in = folder.newFile().toPath();
        String content = "<html><a href=\"https://github.com\"></html>";
        Files.write(in, content.getBytes());
        Path out = folder.getRoot().toPath().resolve("out.html");

        FilesUtil.writeWebContent(in.toUri().toURL(), out);

        String actual = new String(Files.readAllBytes(out));
        assertThat(actual, is(content));
    }

    @Test(expected = IOException.class)
    public void testWriteWebContentExistingOutFile() throws Exception {
        Path out = folder.newFile().toPath();
        FilesUtil.writeWebContent(new URL("http://horstmann.com"), out);
    }

    @Test(expected = NullPointerException.class)
    public void testWriteWebContentNullIn() throws Exception {
        FilesUtil.writeWebContent(null, Paths.get("out.txt"));
    }

    @Test(expected = NullPointerException.class)
    public void testWriteWebContentNullOut() throws Exception {
        FilesUtil.writeWebContent(new URL("http://horstmann.com"), null);
    }
}