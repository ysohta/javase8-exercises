package ch01.ex04;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class consists of utility method to sort files.
 * 
 * @author yukiohta
 *
 */
public class FileSorter {

	private FileSorter() {
	}

	/**
	 * Sorts files based on directory and file. Directories are prior to files
	 * and alphabetical order is applied for each kind.
	 * 
	 * @param files
	 *            sorted files
	 * @throws NullPointerException
	 *             if files is null
	 */
	public static void sort(File[] files) {
		Objects.requireNonNull(files, "files must not be non null");

		Arrays.sort(files, (o1, o2) -> {
			// directory is prior to file
				if (o1.isDirectory() && !o2.isDirectory())
					return -1;
				else if (!o1.isDirectory() && o2.isDirectory())
					return 1;

				return o1.compareTo(o2);
			});
	}
}
