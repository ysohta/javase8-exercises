package ch03.ex16;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ExceptionHandlerTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	Path path;

	@Before
	public void setup() throws IOException {
		path = folder.newFile().toPath();
		List<String> lines = Arrays.asList("A long time ago in a galaxy far,", "far away....");
		Files.write(path, lines);
	}

	@Test
	public void testDoInOrderAsync() throws InterruptedException, IOException {
		// thread-safe character sequence
		StringBuffer buffer = new StringBuffer();

		ExceptionHandler.doInOrderAsync(() -> ExceptionHandler.unchecked(() -> Files.readAllLines(path)).get(),
				(t, u) -> {
					if (u != null) {
						buffer.append("Exception caught: ").append(u.getMessage());
					} else {
						t.forEach((s) -> buffer.append(s).append('\n'));
					}
				});

		Thread.sleep(10);

		assertThat(buffer.toString(), is("A long time ago in a galaxy far,\nfar away....\n"));
	}

	@Test
	public void testDoInOrderAsyncExceptionThrown() throws InterruptedException, IOException {
		// delete file to occur IOException
		Files.delete(path);

		// thread-safe character sequence
		StringBuffer buffer = new StringBuffer();

		ExceptionHandler.doInOrderAsync(() -> ExceptionHandler.unchecked(() -> Files.readAllLines(path)).get(),
				(t, u) -> {
					if (u != null) {
						buffer.append("Exception caught: ").append(u.getMessage());
					} else {
						t.forEach((s) -> buffer.append(s).append('\n'));
					}
				});

		Thread.sleep(10);

		assertThat(buffer.toString(), startsWith("Exception caught: "));

	}

	@Test(expected = NullPointerException.class)
	public void testDoInOrderAsyncNullPointerExceptionFirst() {
		ExceptionHandler.doInOrderAsync(null, (t, u) -> {
		});
	}

	@Test(expected = NullPointerException.class)
	public void testDoInOrderAsyncNullPointerExceptionSecond() {
		ExceptionHandler.doInOrderAsync(() -> 0, null);
	}

	@Test
	public void testUnchecked() {
		int actual = ExceptionHandler.unchecked(() -> {
			return 3;
		}).get();
		assertThat(actual, is(3));

		// throws RuntimeException
		Supplier<Object> sup = ExceptionHandler.unchecked(() -> {
			throw new Exception();
		});
		try {
			sup.get();
			fail();
		} catch (RuntimeException e) {
			assertTrue(e.getMessage(), true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testUncheckedNullPointerException() {
		ExceptionHandler.unchecked(null);
	}

}
