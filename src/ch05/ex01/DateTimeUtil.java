package ch05.ex01;

import java.time.LocalDate;
import java.time.Year;

/**
 * This class consists of utility methods to handle java.time API.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Returns programmer's day of the year.
     *
     * @param year the year to represent, from MIN_YEAR to MAX_YEAR
     * @return programmers date of the year
     * @throws java.time.DateTimeException if year is out of range.
     */
    public static LocalDate getProgrammersDayOf(int year) {
        int day = Year.isLeap(year) ? 12 : 13;
        return LocalDate.of(year, 9, day);
    }
}
