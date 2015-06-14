package ch01.ex02;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DirectoryCrawlerTest {
	File testDir;
	List<File> subDirs = new ArrayList<>();
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setup() throws IOException {
		testDir = folder.newFolder("testdir");
		subDirs.add(folder.newFolder("testdir/child"));
		subDirs.add(folder.newFolder("testdir/child/grandchild1"));
		subDirs.add(folder.newFolder("testdir/child/grandchild2"));
		folder.newFile("testdir/sample.txt");
	}

	@Test
	public void testGetSubDirsUsingLambda() {
		List<File> actuals = DirectoryCrawler.getSubDirsUsingLambda(testDir);
		assertThat(actuals, hasItems(subDirs.toArray(new File[0])));
	}

	@Test(expected=NullPointerException.class)
	public void testGetSubDirsUsingLambdaThrowsNullPointerException(){
		DirectoryCrawler.getSubDirsUsingLambda(null);
	}

	@Test
	public void testGetSubDirsUsingMethodRef() {
		List<File> actuals = DirectoryCrawler.getSubDirsUsingMethodRef(testDir);
		assertThat(actuals, hasItems(subDirs.toArray(new File[0])));
	}

	@Test(expected=NullPointerException.class)
	public void testGetSubDirsUsingMethodRefThrowsNullPointerException(){
		DirectoryCrawler.getSubDirsUsingMethodRef(null);
	}
}
