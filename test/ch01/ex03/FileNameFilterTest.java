package ch01.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;



public class FileNameFilterTest {

	List<File> files = new ArrayList<>();

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setup() throws IOException {
		files.add(folder.newFile("sample.txt"));
		files.add(folder.newFile("sample2.txt"));
		files.add(folder.newFile("sample.html"));
		files.add(folder.newFile("sample.htm"));
	}

	@Test
	public void testGetSubDirsUsingLambda() {
		List<File> actuals = FileNameFilter.extractFilesWith(folder.getRoot(), "htm");
		assertThat(actuals, hasItems(files.get(3)));
	}

	@Test
	public void testExtractFilesWithMultiple() {
		List<File> actuals = FileNameFilter.extractFilesWith(folder.getRoot(), "txt");
		assertThat(actuals, hasItems(files.get(0), files.get(1)));
	}
	
	@Test
	public void testExtractFilesWithNonExisting() {
		List<File> actuals = FileNameFilter.extractFilesWith(folder.getRoot(), "xt");
		assertThat(actuals.isEmpty(), is(true));
	}
	
	@Test(expected=NullPointerException.class)
	public void testExtractFilesWithNullPointerExceptionDir(){
		FileNameFilter.extractFilesWith(null, "txt");
	}
	
	@Test(expected=NullPointerException.class)
	public void testExtractFilesWithNullPointerExceptionExt(){
		FileNameFilter.extractFilesWith(new File(""), null);
	}
}
