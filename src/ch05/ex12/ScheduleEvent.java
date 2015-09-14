package ch05.ex12;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * This class represents schedule event with notification.
 * The object is sorted by the chronological order of notification time.
 * Created by yukiohta on 2015/09/13.
 */
public class ScheduleEvent implements Comparable<ScheduleEvent> {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Duration notification;
    private final ZoneId timezone;

    private ScheduleEvent(LocalDateTime start, LocalDateTime end, Duration notification, ZoneId timezone) {
        this.start = start;
        this.end = end;
        this.notification = notification;
        this.timezone = timezone;
    }

    /**
     * Returns start time.
     *
     * @return start time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Returns end time
     *
     * @return end time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns notification duration before start time. The value is equals to 0 or larger.
     *
     * @return notification duration
     */
    public Duration getNotification() {
        return notification;
    }

    /**
     * Returns time zone.
     *
     * @return time zone
     */
    public ZoneId getTimezone() {
        return timezone;
    }

    /**
     * Returns notification zoned time.
     *
     * @return notification zoned time
     */
    public ZonedDateTime getNotificationZonedTime() {
        return ZonedDateTime.of(start.minus(notification), timezone);
    }

    /**
     * Compares this ScheduleEvent object with the other ScheduleEvent object
     * for the chronological order of notification time.
     *
     * @param o the other object
     * @return a negative integer, zero, or a positive integer as this notification time is less than, equal to,
     * or greater than the other notification time.
     */
    @Override
    public int compareTo(ScheduleEvent o) {
        return getNotificationZonedTime().compareTo(o.getNotificationZonedTime());
    }

    /**
     * This class is builder class of ScheduleEvent.
     */
    public static class Builder {
        private LocalDateTime start;
        private LocalDateTime end;
        private Duration notification = Duration.ofHours(1);
        private ZoneId timezone = ZoneId.systemDefault();

        /**
         * Constructs Builder object.
         */
        public Builder() {
            this.start = LocalDateTime.now();
            this.end = LocalDateTime.now().plusHours(1);
        }

        /**
         * Set start time.
         *
         * @param start start time
         * @return the builder object
         */
        public Builder setStart(LocalDateTime start) {
            Objects.requireNonNull(start, "start must not be null");
            this.start = start;
            return this;
        }

        /**
         * Sets end time.
         *
         * @param end end time
         * @return the builder object
         */
        public Builder setEnd(LocalDateTime end) {
            Objects.requireNonNull(end, "end must not be null");
            this.end = end;
            return this;
        }

        /**
         * Sets notification duration.
         *
         * @param notification notification duration
         * @return the builder object
         */
        public Builder setNotification(Duration notification) {
            Objects.requireNonNull(notification, "notification must not be null");
            if (notification.isNegative())
                throw new IllegalArgumentException("notification must not be negative");

            this.notification = notification;
            return this;
        }

        /**
         * Sets time zone.
         *
         * @param timezone time zone
         * @return the builder object
         */
        public Builder setTimezone(ZoneId timezone) {
            Objects.requireNonNull(timezone, "timezone must not be null");
            this.timezone = timezone;
            return this;
        }

        /**
         * Creates an ScheduleEvent object with the arguments supplied to this builder.
         *
         * @return an ScheduleEvent object
         */
        public ScheduleEvent create() {
            if (!start.isBefore(end))
                throw new IllegalStateException("stat must be before end");

            return new ScheduleEvent(start, end, notification, timezone);
        }
    }
}
