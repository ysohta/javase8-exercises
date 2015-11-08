package ch09.ex05;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * This is test class of {@link ch09.ex05.FilesUtil}
 * Created by yukiohta on 2015/11/07.
 */
public class FilesUtilTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private Path in;

    @Before
    public void setup() throws IOException {
        in = folder.getRoot().toPath().resolve("in.txt");
        Files.write(in, Arrays.asList("foo bar", "baz"));
    }

    @Test
    public void testWriteReversedChar() throws Exception {
        Path out = folder.getRoot().toPath().resolve("out.txt");

        FilesUtil.writeReversedChar(in, out);

        byte[] actuals = Files.readAllBytes(out);
        String expected = System.lineSeparator() + "zab" + System.lineSeparator() + "rab oof";
        assertThat(expected, is(new String(actuals)));
    }

    @Test
    public void testWriteReversedCharExistingOutFile() throws Exception {
        Path out = folder.newFile().toPath();
        try {
            FilesUtil.writeReversedChar(in, out);
            fail();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testWriteReversedCharNullIn() throws Exception {
        FilesUtil.writeReversedChar(null, Paths.get("out.txt"));
    }

    @Test(expected = NullPointerException.class)
    public void testWriteReversedCharNullOut() throws Exception {
        FilesUtil.writeReversedChar(Paths.get("in.txt"), null);
    }
}