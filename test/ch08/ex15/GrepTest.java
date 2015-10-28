package ch08.ex15;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * This is test class for {@link ch08.ex15.Grep}.
 * Created by yukiohta on 2015/10/28.
 */
public class GrepTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testExec() throws Exception {
        Path path = folder.newFile().toPath();
        Files.write(path, Arrays.asList("This is cat.", "This is rat.", "This is can.", "This is CAT."));

        String actual = Grep.exec("[cr]at", Arrays.asList(path));

        String expected = path + ": This is cat.\n" + path + ": This is rat.\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void testExecInMultiplePaths() throws Exception {
        List<Path> paths = new ArrayList<>();
        paths.add(folder.newFile("file2.txt").toPath());
        paths.add(folder.newFile("file3.txt").toPath());
        paths.add(folder.newFile("file1.txt").toPath());

        int index = 0;
        Files.write(paths.get(index++), Arrays.asList("I lost my car!", "I lost my bike!"));
        Files.write(paths.get(index++), Arrays.asList("She lost her car!", "I lost my cell phone"));
        Files.write(paths.get(index++), Arrays.asList("I lost my marbles!", "I lost my marbles"));

        String actual = Grep.exec("I lost my (wallet|car|cell phone|marbles)!?", paths);

        String expected = paths.get(2) + ": I lost my marbles!\n"
                + paths.get(2) + ": I lost my marbles\n"
                + paths.get(0) + ": I lost my car!\n"
                + paths.get(1) + ": I lost my cell phone\n";
        assertThat("sorted by the paths", actual, is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void testExecNullRegex() throws Exception {
        Grep.exec(null, Arrays.asList(folder.newFile().toPath()));
    }

    @Test(expected = NullPointerException.class)
    public void testExecNullPaths() throws Exception {
        Grep.exec("\\w+", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecEmptyPaths() throws Exception {
        Grep.exec("\\w+", Collections.emptyList());
    }

    @Test
    public void testExecInvalidPaths() throws Exception {
        try {
            Grep.exec("\\w+", Arrays.asList(folder.getRoot().toPath().resolve("NonExist.txt")));
            fail();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test(expected = PatternSyntaxException.class)
    public void testExecInvalidRegex() throws Exception {
        Grep.exec("[0-9\\]", Arrays.asList(folder.newFile().toPath()));
    }
}