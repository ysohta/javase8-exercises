package ch03.ex02;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ReentrantLockUtilTest {
	/** 
	 * object to be accessed from an enclosing scope
	 */
	volatile int cnt;

	@Test
	public void testWithLock() throws InterruptedException, ExecutionException {
		ReentrantLock lock = new ReentrantLock();
		int nThreads = 100;
		int nLoop = 1000;
		ExecutorService service = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(nThreads);
		List<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < nThreads; i++) {
			tasks.add(() -> {
				// wait until all the threads get ready
				barrier.await();
				
				// operation with lock
				return ReentrantLockUtil.withLock(lock, () -> {
					for (int j = 0; j < nLoop; j++) {
						cnt++;
					}

					return cnt;
				});
			});
		}

		Collection<Future<Integer>> futures = service.invokeAll(tasks);
		for (Future<Integer> f : futures) {
			assertThat(f.get() % nLoop, is(0));
		}

		assertThat(cnt, is(nLoop * nThreads));
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithLockNullPointerExceptionLock() {
		ReentrantLockUtil.withLock(null, ()->"");
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithLockNullPointerExceptionSupplier() {
		ReentrantLock lock = new ReentrantLock();
		ReentrantLockUtil.withLock(lock, null);
	}
}
