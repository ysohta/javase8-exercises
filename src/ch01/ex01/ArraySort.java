package ch01.ex01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArraySort {

	/**
	 * Returns all thread IDs in method.
	 * @return set of thread IDs.
	 */
	public static Set<Long> getThreadIds(){
		Set<Long> ids = new HashSet<>();
		Integer[] data = { 2, 1, 7 };
		
		Arrays.sort(data, (o1, o2) ->{
			ids.add(Thread.currentThread().getId());
			return 0;
		});
		
		ids.add(Thread.currentThread().getId());
		
		return ids;
	}
}
