package ch05.ex07;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * This class represents time interval between two specific times.
 * Created by yukiohta on 2015/09/08.
 */
public class TimeInterval {
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;

    private TimeInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns interval with duration.
     *
     * @param startTime start time of interval
     * @param duration  duration
     * @return time interval
     * @throws IllegalArgumentException if duration is negative
     * @throws NullPointerException     if startTime or duration is null
     */
    public static TimeInterval of(ZonedDateTime startTime, Duration duration) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(duration, "duration must not be null");

        if (duration.isNegative())
            throw new IllegalArgumentException("duration must not be negative");

        return new TimeInterval(startTime, startTime.plus(duration));
    }

    /**
     * Returns time interval specified start and end time.
     *
     * @param startTime start time of interval
     * @param endTime   end time of interval
     * @return time interval
     * @throws IllegalArgumentException if startTime is after endTime
     * @throws NullPointerException     if startTime or endTime is null
     */
    public static TimeInterval of(ZonedDateTime startTime, ZonedDateTime endTime) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(endTime, "endTime must not be null");

        if (startTime.isAfter(endTime))
            throw new IllegalArgumentException("startTime must be before endTime");

        return new TimeInterval(startTime, endTime);
    }

    /**
     * Returns true if intervals are overlapped.
     * It recognizes no overlapping if end time of the interval and start time of the other interval are the same,
     * and vice versa.
     *
     * @param interval other interval
     * @return true if overlapped
     * @throws NullPointerException if interval is null
     */
    public boolean isOverlapped(TimeInterval interval) {
        Objects.requireNonNull(interval, "interval must not be null");

        if (endTime.isEqual(interval.startTime) || endTime.isBefore(interval.startTime)) {
            return false;
        } else if (startTime.isEqual(interval.endTime) || startTime.isAfter(interval.endTime)) {
            return false;
        }

        return true;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }
}
