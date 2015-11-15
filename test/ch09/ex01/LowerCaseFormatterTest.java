package ch09.ex01;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * This is test class of {@link ch09.ex01.LowerCaseFormatter}.
 * Created by yukiohta on 2015/11/08.
 */
public class LowerCaseFormatterTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    Path rootPath;
    Path in;
    Path out;


    @Before
    public void setup() throws IOException {
        rootPath = folder.getRoot().toPath();
        in = folder.getRoot().toPath().resolve("in.txt").toAbsolutePath();
        Files.write(in, Arrays.asList("small BIG CamelCase 123"));

        out = rootPath.resolve("out.txt").toAbsolutePath();

    }

    @Test
    public void testWrite() throws Exception {
        LowerCaseFormatter.write(in.toString(), out.toString());

        List<String> lines = Files.readAllLines(out);
        assertThat(lines, hasItems("small", "big", "camelcase", "123"));
    }

    @Test
    public void testWriteFailedScannerConstructor() throws Exception {

        try {
            LowerCaseFormatter.write(null, out.toString());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        assertThat(Files.exists(out), is(false));
    }

    @Test
    public void testWriteFailedPrintWriterConstructor() throws Exception {
        try {
            LowerCaseFormatter.write(in.toString(), null);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}