package ch06.ex01;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This represents manager to find string with the longest length.
 * Created by yukiohta on 2015/09/21.
 */
public class LongestStringManager {
    private AtomicReference<String> longest = new AtomicReference<>("");

    /**
     * Adds candidate string.
     *
     * @param string candidate string
     * @throws NullPointerException if string is null
     */
    public void add(String string) {
        Objects.requireNonNull(string, "string must not be null");

        longest.updateAndGet(s -> string.length() > s.length() ? string : s);
    }

    /**
     * Returns the longest string.
     *
     * @return the longest string
     */
    public String getLongest() {
        return longest.get();
    }

}
