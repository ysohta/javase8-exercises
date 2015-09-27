package ch05.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.Objects;

/**
 * This class represents calendar of specified date.
 * Created by yukiohta on 2015/09/06.
 */
public class Cal {
    private static final String FormatDay = "%2d ";
    private static final String FormatSpace = "   ";
    private final LocalDate firstDayOfMonth;
    private final LocalDate lastDayOfMonth;
    private DayOfWeek endOfWeek = DayOfWeek.SUNDAY;

    /**
     * Constructs {@link Cal} object.
     *
     * @param date date for calendar
     * @throws NullPointerException if cal is null
     */
    public Cal(LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
        this.firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        this.lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static void main(String... args) {
        LocalDate date = LocalDate.now();
        if (args.length > 1) {
            int month = Integer.valueOf(args[0]);
            int year = Integer.valueOf(args[1]);
            date = LocalDate.of(year, month, 1);
        }

        Cal cal = new Cal(date);
        System.out.println(cal);
    }

    /**
     * Returns formatted calendar string. The week starts on Monday.
     *
     * @return calendar string
     */
    @Override
    public String toString() {
        LinkedList<String> formattedDays = getFormattedDays();
        int daysOfLastMonth = getDaysOfLastMonth();

        StringBuilder builder = new StringBuilder();

        // append space for last month
        for (int i = 0; i < daysOfLastMonth; i++) {
            formattedDays.addFirst(FormatSpace);
        }

        // append days for the month of calendar
        for (int i = 0; i < formattedDays.size(); i++) {
            builder.append(formattedDays.get(i));
            if (i % 7 == 6) {
                builder.append("\n");
            }
        }

        // append line separator if needed
        if (builder.lastIndexOf("\n") != builder.length() - 1) {
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * Returns the number of days on last month in the calendar.
     *
     * @return number of days
     */
    int getDaysOfLastMonth() {
        return (6 + firstDayOfMonth.getDayOfWeek().getValue() - endOfWeek.getValue()) % 7;
    }

    private LinkedList<String> getFormattedDays() {
        LinkedList<String> formattedDays = new LinkedList<>();

        int lastDay = lastDayOfMonth.getDayOfMonth();

        for (int day = 1; day <= lastDay; day++) {
            formattedDays.add(String.format(FormatDay, day));
        }

        return formattedDays;
    }
}
