package ch08.ex10;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link FileFinder}.
 * Created by yukiohta on 2015/10/21.
 */
public class FileFinderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testFilesContaining() throws Exception {
        Path path1 = folder.newFile("volatile.txt").toPath();
        Files.write(path1, Arrays.asList("File has volatile."));
        Path path2 = folder.newFile("transient.txt").toPath();
        Files.write(path2, Arrays.asList("File has transient."));
        Path path3 = folder.newFile("both.txt").toPath();
        Files.write(path3, Arrays.asList("File has volatile and transient."));
        Path path4 = folder.newFile("none.txt").toPath();
        Files.write(path4, Arrays.asList("File has none of words."));

        List<Path> actual = FileFinder.filesContaining(folder.getRoot().toPath(),
                Arrays.asList("volatile", "transient"));

        assertThat(actual.size(), is(3));
        assertThat(actual, hasItems(path1, path2, path3));
    }

    @Test
    public void testFilesContainingSingleFile() throws Exception {
        Path path1 = folder.newFile("volatile.txt").toPath();
        Files.write(path1, Arrays.asList("File has volatile."));

        List<Path> actual = FileFinder.filesContaining(path1, Arrays.asList("volatile"));

        assertThat(actual.size(), is(1));
        assertThat(actual, hasItems(path1));
    }

    @Test
    public void testFilesContainingNoFile() throws Exception {
        List<Path> actual = FileFinder.filesContaining(folder.getRoot().toPath(), Arrays.asList("volatile"));

        assertThat(actual.size(), is(0));
    }

    @Test(expected = IOException.class)
    public void testFilesContainingNonExistPath() throws Exception {
        FileFinder.filesContaining(Paths.get("NonExistPath"), Arrays.asList("foo"));
    }

    @Test(expected = NullPointerException.class)
    public void testFilesContainingNullStart() throws Exception {
        FileFinder.filesContaining(null, Arrays.asList("foo"));
    }

    @Test(expected = NullPointerException.class)
    public void testFilesContainingNullWords() throws Exception {
        FileFinder.filesContaining(folder.getRoot().toPath(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilesContainingEmptyWords() throws Exception {
        FileFinder.filesContaining(folder.getRoot().toPath(), Collections.EMPTY_LIST);
    }
}
