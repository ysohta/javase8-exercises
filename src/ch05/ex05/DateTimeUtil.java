package ch05.ex05;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * This class consists of utility methods to handle java.time API.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Returns the number of days between two specified dates. It could be negative.
     *
     * @param startDateInclusive the start inclusive date
     * @param endDateExclusive   the end exclusive date
     * @return days between start and end
     * @throws NullPointerException if startDateInclusive or endDateExclusive is null
     */
    public static long daysBetween(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        Objects.requireNonNull(startDateInclusive, "startDateInclusive must not be null");
        Objects.requireNonNull(endDateExclusive, "endDateExclusive must not be null");

        if (startDateInclusive.getYear() == endDateExclusive.getYear()) {
            return endDateExclusive.getDayOfYear() - startDateInclusive.getDayOfYear();
        }

        boolean isNegative = Period.between(startDateInclusive, endDateExclusive).isNegative();

        // swap dates if period is negative
        LocalDate start = isNegative ? endDateExclusive : startDateInclusive;
        LocalDate end = isNegative ? startDateInclusive : endDateExclusive;

        long sum = start.lengthOfYear() - start.getDayOfYear();
        LocalDate current = start.plusYears(1);
        while (current.getYear() < end.getYear()) {
            sum += current.lengthOfYear();
            current = current.plusYears(1);
        }

        sum += end.getDayOfYear();

        return isNegative ? -sum : sum;
    }

    public static void main(String... args) {
        if (args.length == 0) {
            printHelp();
            System.exit(-1);
        }

        LocalDate date = null;

        try {
            date = LocalDate.parse(args[0]);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            printHelp();
            System.exit(-1);
        }

        long days = daysBetween(date, LocalDate.now());
        System.out.println(days + " days passed from " + date);
    }

    private static void printHelp() {
        System.out.println("usage: java ch05.ex05.DateTimeUtil [date]\n");
        System.out.println("example: java ch05.ex05.DateTimeUtil 1903-06-14");
    }
}
