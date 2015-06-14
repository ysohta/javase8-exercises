package ch02.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class consists of utility method to count strings in parallel.
 * 
 * @author yukiohta
 *
 */
public class ParallelCounter {
	private ParallelCounter() {
	}

	/**
	 * Returns counts of words over the string length.
	 * 
	 * @param words
	 *            list of words
	 * @param length
	 *            length of string
	 * @return counts of words
	 * @throws NullPointerException
	 *             if words is null
	 * @throws IllegalArgumentException
	 *             if words is empty or length is smaller than 0
	 */
	public static int countStringsOverLength(List<String> words, int length) {
		Objects.requireNonNull(words);
		if (words.size() == 0) {
			throw new IllegalArgumentException("words is empty");
		}
		if (length < 0) {
			throw new IllegalArgumentException("length must be over 0");
		}

		AtomicInteger count = new AtomicInteger();
		int cores = Runtime.getRuntime().availableProcessors();
		List<Integer> indice = getIndice(words.size(), cores);

		int parties = words.size() < cores ? words.size() : cores;
		CyclicBarrier barrier = new CyclicBarrier(parties + 1);
		ExecutorService service = Executors.newFixedThreadPool(parties);

		for (int i = 0; i < parties; i++) {
			int from = indice.get(i);
			int to = indice.get(i + 1);
			List<String> segmented = words.subList(from, to);

			service.execute(() -> {
				for (String w : segmented) {
					if (w.length() > length) {
						// increment after operation
						count.incrementAndGet();
					}
				}

				try {
					barrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

		return count.get();
	}

	/**
	 * Returns list of boundary indices divided into the number of groups. For
	 * example, it returns {0, 2, 4} if total is 5 and groups is 2,
	 * 
	 * @param total
	 *            total number of elements
	 * @param groups
	 *            number of groups
	 * @return list of indices
	 */
	private static List<Integer> getIndice(int total, int groups) {
		// divide numbers of words into groups
		List<Integer> nWords = new ArrayList<>();
		int nBase = total / groups;
		for (int i = 0; i < groups; i++) {
			if (i < total % groups) {
				nWords.add(nBase + 1);
			} else {
				nWords.add(nBase);
			}
		}

		// get listed index
		List<Integer> indexes = new ArrayList<>();
		indexes.add(0);
		for (int i = 0; i < nWords.size(); i++) {
			indexes.add(indexes.get(i) + nWords.get(i));
		}

		return indexes;
	}
}
