package ch01.ex03;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class consists of utility method to list files with the specified
 * extension.
 * 
 * @author yukiohta
 *
 */
public class FileNameFilter {

	/**
	 * Returns list of files with the extension in the directory.
	 * 
	 * @param dir
	 *            directory to be searched
	 * @param extension
	 *            file extension
	 * @return list of files
	 * @throws NullPointerException
	 *             if dir or extension is null
	 */
	public static List<File> extractFilesWith(File dir, String extension) {
		Objects.requireNonNull(dir, "dir must not be non null");
		Objects.requireNonNull(extension, "extension must not be non null");
		
		// extension: captured variable in enclosing scope
		return Arrays.asList(dir.listFiles((d, n) -> n.endsWith("." + extension)));
	}
}
