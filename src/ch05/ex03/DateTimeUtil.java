package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * This class consists of utility methods to handle java.time API.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Returns {@link TemporalAdjuster} object to provide the next day which the specified predicate meets qualifier.
     *
     * @param predicate predicate to provide next day.
     * @return temporal object
     * @throws java.time.DateTimeException if {@link LocalDate} is out of range
     * @throws NullPointerException        if predicate is null
     */
    public static TemporalAdjuster next(Predicate<LocalDate> predicate) {
        Objects.requireNonNull(predicate, "predicate must not be null");

        return t -> {
            LocalDate result = (LocalDate) t;
            do {
                result = result.plusDays(1);
            } while (!predicate.test(result));
            return result;
        };
    }
}
