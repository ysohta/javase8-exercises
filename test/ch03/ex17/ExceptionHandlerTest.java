package ch03.ex17;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Test;

public class ExceptionHandlerTest {
	BlockingQueue<Integer> queue;

	@Before
	public void setup() {
		queue = new LinkedBlockingQueue<>(10);
	}

	@Test(timeout = 1000)
	public void testDoInParallelAsync() throws InterruptedException {
		// thread-safe character sequence
		StringBuffer buffer = new StringBuffer();

		ExceptionHandler.doInParallelAsync(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					queue.put(i);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}, () -> {
			try {
				for (int i = 0; i < 10; i++) {
					if (i != 0)
						buffer.append(", ");
					buffer.append(queue.take());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}, (t) -> {
			buffer.append("\nException caught: ").append(t.getMessage());
		});

		Thread.sleep(10);

		assertThat(buffer.toString(), is("0, 1, 2, 3, 4, 5, 6, 7, 8, 9"));
	}

	@Test(timeout = 1000)
	public void testDoInParallelAsyncExceptionOccuredFirst() throws InterruptedException {
		// thread-safe character sequence
		StringBuffer buffer = new StringBuffer();

		ExceptionHandler.doInParallelAsync(() -> {
			try {
				for (int i = 0; i < 21; i++) {
					queue.add(i); // Exception occurs when queue is full
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}, () -> {
		try {
			for (int i = 0; i < 10; i++) {
				if (i != 0)
					buffer.append(", ");
				buffer.append(queue.take());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}, (t) -> {
		buffer.append("\nException caught: ").append(t.getMessage());
	}	);

		Thread.sleep(10);

		assertThat(buffer.toString(), containsString("Exception caught: "));
	}

	@Test(timeout = 1000)
	public void testDoInParallelAsyncExceptionOccuredSecond() throws InterruptedException {
		// thread-safe character sequence
		StringBuffer buffer = new StringBuffer();

		ExceptionHandler.doInParallelAsync(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					queue.put(i);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}, () -> {
			try {
				for (int i = 0; i < 10; i++) {
					if (i != 0)
						buffer.append(", ");
					buffer.append(queue.take());
				}

				queue.remove(); // Exception occurs when queue is empty

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}, (t) -> {
			buffer.append("\nException caught: ").append(t.getMessage());
		});

		Thread.sleep(10);

		assertThat(buffer.toString(), containsString("Exception caught: "));
	}

	@Test(timeout = 1000)
	public void testDoInParallelAsyncExceptionOccuredBoth() throws InterruptedException {
		// thread-safe character sequence
		StringBuffer buffer = new StringBuffer();

		ExceptionHandler.doInParallelAsync(() -> {
			try {
				for (int i = 0; i < 11; i++) {
					queue.add(i); // Exception occurs when queue is full
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}, () -> {
		try {
			for (int i = 0; i < 11; i++) {
				queue.add(i); // Exception occurs when queue is full
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}, (t) -> {
		buffer.append("\nException caught: ").append(t.getMessage());
	}	);

		Thread.sleep(10);

		String actual = buffer.toString();
		assertThat("patterns found two times", actual.split("Exception caught: ").length, is(3));
	}

	@Test(expected = NullPointerException.class)
	public void testDoInParallelAsyncNullPointerExceptionFirst() {
		ExceptionHandler.doInParallelAsync(null, () -> {
		}, (t) -> {
		});
	}

	@Test(expected = NullPointerException.class)
	public void testDoInParallelAsyncNullPointerExceptionSecond() {
		ExceptionHandler.doInParallelAsync(() -> {
		}, null, (t) -> {
		});
	}

	@Test(expected = NullPointerException.class)
	public void testDoInParallelAsyncNullPointerExceptionHandler() {
		ExceptionHandler.doInParallelAsync(() -> {
		}, () -> {
		}, null);
	}
}
