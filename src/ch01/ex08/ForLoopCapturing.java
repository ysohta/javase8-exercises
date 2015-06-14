package ch01.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class consists of methods to test value capturing in for loop.
 * 
 * @author yukiohta
 *
 */
public class ForLoopCapturing {

	private ForLoopCapturing() {
	}

	/**
	 * This method returns list of Runnable objects which can capture values in
	 * extended for loop.
	 * 
	 * @return list of Runnable objects
	 */
	public static List<Runnable> getRunnersInExtendedForLoop() {
		String[] names = { "Peter", "Paul", "Mary" };

		List<Runnable> runners = new ArrayList<>();
		for (String name : names) {
			runners.add(() -> System.out.println(name));
		}

		return runners;
	}

	public static List<Runnable> getRunnersInForLoop() {
		String[] names = { "Peter", "Paul", "Mary" };

		List<Runnable> runners = new ArrayList<>();
		for (int i = 0; i < names.length; i++) {
			/*
			 * compile error at i
			 * "Local variable i defined in an enclosing scope must be final or effectively final"
			 */
			// runners.add(() -> System.out.println(names[i]));
		}

		return runners;
	}

	/**
	 * Main method for the entry point.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Runnable> runners = getRunnersInExtendedForLoop();
		for (Runnable r : runners) {
			threadPool.execute(r);
		}
	}
}
