package ch01.ex04;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileSorterTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testSortDirPriorToFile() throws IOException {
		File[] actuals = { folder.newFile("abc.txt"), folder.newFolder("xyz"), };
		File[] expecteds = { new File(folder.getRoot(), "xyz"), new File(folder.getRoot(), "abc.txt"), };

		FileSorter.sort(actuals);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testSortAlphabeticalOrder() throws IOException {
		File[] actuals = { folder.newFile("0.data"), folder.newFile("abc.txt"), folder.newFile("aaa.txt"), };
		File[] expecteds = { new File(folder.getRoot(), "0.data"), new File(folder.getRoot(), "aaa.txt"),
				new File(folder.getRoot(), "abc.txt"), };

		FileSorter.sort(actuals);
		assertArrayEquals(expecteds, actuals);
	}

	@Test(expected = NullPointerException.class)
	public void testSortNullPointerExceptionExt() {
		FileSorter.sort(null);
	}

	@Test
	public void testSortEmpty() {
		File[] actuals = new File[0];
		FileSorter.sort(actuals);
		assertThat(actuals.length, is(0));
	}

	@Test
	public void testSortOneElement() throws IOException {
		File[] actuals = {folder.newFile("abc.txt") };
		File[] expecteds = { new File(folder.getRoot(), "abc.txt") };

		FileSorter.sort(actuals);
		assertArrayEquals(expecteds, actuals);
	}
}
