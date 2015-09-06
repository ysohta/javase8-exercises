package ch05.ex02;

import java.time.LocalDate;

/**
 * This class consists of utility methods to handle java.time API.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Returns a copy of {@code LocalDate} in years added repeatedly.
     *
     * @param date       a {@code LocalDate} to add
     * @param yearsToAdd the years to add, may be negative
     * @param repeat     the repeat times to add years
     * @return a {@code LocalDate} with the years added in repeat times
     * @throws java.time.DateTimeException if {@link LocalDate} is out of range
     * @throws IllegalArgumentException    if repeat is negative
     */
    public static LocalDate plusYearsRepeatedly(LocalDate date, long yearsToAdd, int repeat) {
        if (repeat < 0) {
            throw new IllegalArgumentException("repeat must be larger or equals to 0");
        }

        LocalDate newDate = date;
        if (yearsToAdd == 0) {
            return newDate;
        }

        for (int i = 0; i < repeat; i++) {
            newDate = newDate.plusYears(yearsToAdd);
        }

        return newDate;
    }
}
