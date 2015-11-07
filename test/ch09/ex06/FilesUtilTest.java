package ch09.ex06;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link FilesUtil}
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
    public void testWriteReversedLine() throws Exception {
        Path out = folder.getRoot().toPath().resolve("out.txt");

        FilesUtil.writeReversedLine(in, out);

        List<String> actuals = Files.readAllLines(out);
        assertThat(actuals.size(), is(0));
        assertThat(actuals.get(0), is("baz"));
        assertThat(actuals.get(1), is("foo bar"));
    }

    @Test(expected = IOException.class)
    public void testWriteReversedLineExistingOutFile() throws Exception {
        Path out = folder.newFile().toPath();
        FilesUtil.writeReversedLine(in, out);
    }

    @Test(expected = NullPointerException.class)
    public void testWriteReversedLineNullIn() throws Exception {
        FilesUtil.writeReversedLine(null, Paths.get("out.txt"));
    }

    @Test(expected = NullPointerException.class)
    public void testWriteReversedLineNullOut() throws Exception {
        FilesUtil.writeReversedLine(Paths.get("in.txt"), null);
    }
}