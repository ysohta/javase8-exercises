package ch05.ex06;


import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;
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
     * Returns list of the dates filtered by the predicate between start and end.
     *
     * @param startDateInclusive the start inclusive date
     * @param endDateExclusive   the end exclusive date
     * @param predicate          predicate to filter date
     * @return list of dates
     * @throws NullPointerException if startDateInclusive, endDateExclusive or predicate is null
     */
    public static List<LocalDate> listDates(LocalDate startDateInclusive, LocalDate endDateExclusive,
                                            Predicate<LocalDate> predicate) {
        Objects.requireNonNull(startDateInclusive, "startDateInclusive must not be null");
        Objects.requireNonNull(endDateExclusive, "endDateExclusive must not be null");
        Objects.requireNonNull(predicate, "predicate must not be null");

        List<LocalDate> list = new ArrayList<>();
        LocalDate current = startDateInclusive.minusDays(1).with(next(predicate));

        while (current.isBefore(endDateExclusive)) {
            list.add(current);
            current = current.with(next(predicate));
        }

        return list;
    }

    /**
     * Returns {@link TemporalAdjuster} object to provide the next day which the specified predicate meets qualifier.
     * This method is copied from {@link ch05.ex03.DateTimeUtil}.
     *
     * @param predicate predicate to provide next day.
     * @return temporal object
     * @throws java.time.DateTimeException if {@link LocalDate} is out of range
     * @throws NullPointerException        if predicate is null
     * @see ch05.ex03.DateTimeUtil#next(Predicate)
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
