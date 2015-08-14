package ch03.ex21;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.BeforeClass;
import org.junit.Test;

public class FutureUtilTest {

	private static ExecutorService pool;

	@BeforeClass
	public static void setupClass() {
		pool = Executors.newCachedThreadPool();
	}

	@Test
	public void testMap() throws InterruptedException, ExecutionException {
		Future<String> future = pool.submit(() -> {
			return "3";
		});
		Future<Integer> actual = FutureUtil.map(future, Integer::parseInt);
		assertThat(actual.get(), is(3));
	}

	@Test(expected = ExecutionException.class)
	public void testMapExceptionThrown() throws InterruptedException, ExecutionException {
		Future<String> future = pool.submit(() -> {
			throw new Exception("in call method");
		});
		Future<Integer> actual = FutureUtil.map(future, Integer::parseInt);
		actual.get();
	}

	@Test(expected = NullPointerException.class)
	public void testMapNullPointerExceptionFuture() {
		FutureUtil.map(null, (t) -> "");
	}

	@Test(expected = NullPointerException.class)
	public void testMapNullPointerExceptionF() {
		Future<String> future = pool.submit(() -> "");
		FutureUtil.map(future, null);
	}
}
