package ch01.ex02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class consists of utility method to list sub-directories.
 * 
 * @author yukiohta
 *
 */
public class DirectoryCrawler {

	private DirectoryCrawler() {
	}

	/**
	 * Returns a list of sub-directory under the directory. Lambda expression is
	 * used in this method.
	 * 
	 * @param dir
	 *            directory for searching
	 * @return list of sub-directory
	 * @throws NullPointerException
	 *             if dir is null
	 */
	public static List<File> getSubDirsUsingLambda(File dir) {
		Objects.requireNonNull(dir, "dir must not be non null");

		List<File> dirs = new ArrayList<>();

		for (File d : dir.listFiles(f -> f.isDirectory())) { // lambda
			dirs.add(d);
			dirs.addAll(getSubDirsUsingLambda(d));
		}

		return dirs;
	}

	/**
	 * Returns a list of sub-directory under the directory. Method reference is
	 * used in this method.
	 * 
	 * @param dir
	 *            directory for searching
	 * @return list of sub-directory
	 * @throws NullPointerException
	 *             if dir is null
	 */
	public static List<File> getSubDirsUsingMethodRef(File dir) {
		Objects.requireNonNull(dir, "dir must not be non null");

		List<File> dirs = new ArrayList<>();

		for (File d : dir.listFiles(File::isDirectory)) { // method ref
			dirs.add(d);
			dirs.addAll(getSubDirsUsingLambda(d));
		}

		return dirs;
	}
}
