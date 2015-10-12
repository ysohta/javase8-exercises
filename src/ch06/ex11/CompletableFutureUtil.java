package ch06.ex11;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This class consists of utility methods to handle CompletableFuture.
 * Created by yukiohta on 2015/10/10.
 */
public class CompletableFutureUtil {
    private CompletableFutureUtil() {
    }

    /**
     * Returns new CompletableFuture to repeat the action until predicate meets condition.
     *
     * @param action action to repeat
     * @param until  predicate to stop repeated action
     * @param <T>    the type of input for repeated action
     * @return new CompletableFuture
     * @throws NullPointerException if action or until is null
     */
    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        Objects.requireNonNull(action, "action must not be null");
        Objects.requireNonNull(until, "until must not be null");

        return CompletableFuture.supplyAsync(action)
                .thenComposeAsync((t) -> until.test(t) ? CompletableFuture.completedFuture(t) : repeat(action, until));
    }
}
