package ch03.ex18;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ExceptionHandlerTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testUnchecked() throws IOException {
		Path p1 = folder.newFile().toPath();
		Path p2 = folder.newFile().toPath();
		Path p3 = folder.newFile().toPath();
		Files.write(p1, Arrays.asList("A long time ago "));
		Files.write(p2, Arrays.asList("in a galaxy ", "far, "));
		Files.write(p3, Arrays.asList("far away...."));

		// uncheck IOException
		Function<Path, Stream<String>> f = ExceptionHandler.unchecked((p) -> Files.lines(p));

		String actual = Stream.of(p1, p2, p3).flatMap(f).collect(Collectors.joining());
		assertThat(actual, is("A long time ago in a galaxy far, far away...."));
	}

	@Test
	public void testUncheckedThrowsException() {
		// throws RuntimeException
		Function<Path, Stream<String>> f = ExceptionHandler.unchecked((p) -> Files.lines(p));
		
		try {
			f.apply(Paths.get("INVALID_PATH"));
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getCause(), instanceOf(IOException.class));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testUncheckedNullPointerException() {
		ExceptionHandler.unchecked(null);
	}
}
