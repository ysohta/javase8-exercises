package ch06.ex06;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex06.WordManager}.
 * Created by yukiohta on 2015/10/05.
 */
public class WordManagerTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testReadInParallel() throws Exception {
        List<File> files = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            File file = folder.newFile("file" + i);
            Files.copy(Paths.get("res/pg2600.txt"), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            files.add(file);
        }

        WordManager target = new WordManager();
        target.readInParallel(files);

        Set<File> actuals;
        actuals = target.getFilesWith("Tolstoy");
        assertThat(actuals.size(), is(4));
    }

    @Test(expected = NullPointerException.class)
    public void testReadInParallelNullFiles() throws Exception {
        WordManager target = new WordManager();
        target.readInParallel(null);
    }

    @Test
    public void testReadSingleFile() throws Exception {
        File file = folder.newFile("file1.txt");
        Files.write(file.toPath(), Arrays.asList("dog wood", "cat dog"));

        WordManager target = new WordManager();
        target.read(file);

        Set<File> actuals;
        actuals = target.getFilesWith("dog");
        assertThat(actuals.size(), is(1));
        assertThat(actuals.contains(file), is(true));

        actuals = target.getFilesWith("wood");
        assertThat(actuals.size(), is(1));
        assertThat(actuals.contains(file), is(true));

        actuals = target.getFilesWith("cat");
        assertThat(actuals.size(), is(1));
        assertThat(actuals.contains(file), is(true));

        actuals = target.getFilesWith("InvalidWord!!!");
        assertThat(actuals.size(), is(0));
    }

    @Test
    public void testReadMultipleFile() throws Exception {
        File file1 = folder.newFile("file1.txt");
        Files.write(file1.toPath(), Arrays.asList("dog wood", "cat dog"));

        File file2 = folder.newFile("file2.txt");
        Files.write(file2.toPath(), Arrays.asList("wood tree", "dog"));

        WordManager target = new WordManager();
        target.read(file1);
        target.read(file2);

        Set<File> actuals;
        actuals = target.getFilesWith("dog");
        assertThat(actuals.size(), is(2));
        assertThat(actuals.contains(file1), is(true));
        assertThat(actuals.contains(file2), is(true));

        actuals = target.getFilesWith("wood");
        assertThat(actuals.size(), is(2));
        assertThat(actuals.contains(file1), is(true));
        assertThat(actuals.contains(file2), is(true));

        actuals = target.getFilesWith("cat");
        assertThat(actuals.size(), is(1));
        assertThat(actuals.contains(file1), is(true));

        actuals = target.getFilesWith("tree");
        assertThat(actuals.size(), is(1));
        assertThat(actuals.contains(file2), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testReadNullFile() throws Exception {
        WordManager target = new WordManager();
        target.read(null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetFilesWith() throws Exception {
        WordManager target = new WordManager();
        target.getFilesWith(null);
    }
}