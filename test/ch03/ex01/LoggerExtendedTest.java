package ch03.ex01;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class LoggerExtendedTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private Path pathLog;
	private LoggerExtended target;

	@Before
	public void setup() throws IOException {
		pathLog = folder.newFile().toPath();
		FileHandler handler = new FileHandler(pathLog.toString());
		handler.setFormatter(new SimpleFormatter());

		Logger logger = Logger.getLogger(getClass().getName());
		logger.addHandler(handler);
		target = new LoggerExtended(logger);
	}

	@After
	public void teardown() throws IOException {
		Files.deleteIfExists(pathLog);
	}

	@Test
	public void testLogIf() throws IOException {
		int val1 = 3;
		target.logIf(Level.INFO, () -> val1 % 2 == 0, () -> "val=" + val1);

		int val2 = 6;
		target.logIf(Level.INFO, () -> val2 % 2 == 0, () -> "val=" + val2);

		List<String> lines = Files.readAllLines(pathLog);
		assertThat(lines.size(), is(2));
		int index = 0;
		assertThat("Method name calling logIf()", lines.get(index++),
				containsString("ch03.ex01.LoggerExtendedTest testLogIf"));
		assertThat(lines.get(index++), is("INFO: val=6"));
	}

	@SuppressWarnings("null")
	@Test
	public void testLogIfDeferedExecution() {
		String nullStrng = null;

		try {
			target.logIf(Level.SEVERE, () -> true, () -> nullStrng.toString());
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}

		target.logIf(Level.SEVERE, () -> false, () -> nullStrng.toString());
	}

	@Test(expected = NullPointerException.class)
	public void testLogIfNullPointerExceptionLevel() {
		target.logIf(null, () -> true, () -> "");
	}

	@Test(expected = NullPointerException.class)
	public void testLogIfNullPointerExceptionCondition() {
		target.logIf(Level.SEVERE, null, () -> "");
	}

	@Test(expected = NullPointerException.class)
	public void testLogIfNullPointerExceptionMsg() {
		target.logIf(Level.SEVERE, () -> true, null);
	}
}
