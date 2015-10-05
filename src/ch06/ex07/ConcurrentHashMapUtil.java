package ch06.ex07;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class consists of utility methods to handle {@link ConcurrentHashMap}.
 * Created by yukiohta on 2015/10/05.
 */
public class ConcurrentHashMapUtil {
    /**
     * Use maximum number of threads for calculation.
     */
    private static final long Threshold = 1;

    private ConcurrentHashMapUtil() {
    }

    /**
     * Returns key with the largest value. If multiple maximum values exist, one of the key is returned.
     *
     * @param map map
     * @return key with the largest value
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map is empty
     */
    public static String findKeyWithLargestValue(ConcurrentHashMap<String, Long> map) {
        Objects.requireNonNull(map, "map must not be null");

        if (map.isEmpty())
            throw new IllegalArgumentException("map must not be empty");

        return map.reduceEntries(Threshold, (e1, e2) -> (e1.getValue() > e2.getValue()) ? e1 : e2).getKey();
    }
}
