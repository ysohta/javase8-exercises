package ch06.ex06;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * This class represents word management to store the information of original file.
 * Created by yukiohta on 2015/10/05.
 */
public class WordManager {
    private ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

    /**
     * Reads files in parallel to collect words.
     *
     * @param files list of files
     * @throws InterruptedException if failed to terminate threads
     * @throws NullPointerException if files is null
     */
    public void readInParallel(List<File> files) throws InterruptedException {
        Objects.requireNonNull(files, "files must not be null");

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        for (int i = 0; i < cores; i++) {
            int fromIndex = files.size() / cores * i;
            int toIndex = files.size() / cores * (i + 1);

            if (fromIndex == toIndex)
                continue;

            service.submit(() -> {
                for (File f : files.subList(fromIndex, toIndex)) {
                    read(f);
                }

                // recognized as Callable
                return null;
            });
        }

        service.shutdown();
        service.awaitTermination(1L, TimeUnit.SECONDS);
    }

    /**
     * Reads single file to collect words.
     *
     * @param file single file
     * @throws IOException if file is null
     */
    public void read(File file) throws IOException {
        Objects.requireNonNull(file, "file must not be null");

        // computeIfAbsent method does not need remapping function
        Files.lines(file.toPath())
                .flatMap((s) -> Stream.of(s.split("[\\p{Punct}\\s]+")))
                .forEach((s) -> map.computeIfAbsent(s, (w) -> ConcurrentHashMap.newKeySet()).add(file));
    }

    /**
     * Returns set of files containing the specified word.
     *
     * @param word searching word
     * @return set of files
     * @throws NullPointerException if word is null
     */
    public Set<File> getFilesWith(String word) {
        Objects.requireNonNull(word, "word must not be null");

        return map.computeIfAbsent(word, (s) -> Collections.EMPTY_SET);
    }

    private static Set<File> initialSet(File file) {
        Set<File> set = ConcurrentHashMap.newKeySet();
        set.add(file);
        return set;
    }
}
