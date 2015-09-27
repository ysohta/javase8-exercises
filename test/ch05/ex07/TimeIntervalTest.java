package ch05.ex07;

import org.junit.Test;

import java.time.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex07.TimeInterval}.
 * Created by yukiohta on 2015/09/08.
 */
public class TimeIntervalTest {
    ZoneId zone = ZoneId.of("Asia/Tokyo");

    @Test
    public void testIsOverlapped() throws Exception {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        ZonedDateTime time2 = ZonedDateTime.of(date, LocalTime.of(11, 0), zone);
        TimeInterval time10to11 = TimeInterval.of(time1, Duration.ofHours(1));
        TimeInterval time10to12 = TimeInterval.of(time1, Duration.ofHours(2));
        TimeInterval time11to12 = TimeInterval.of(time2, Duration.ofHours(1));

        assertThat(time10to11.isOverlapped(time10to12), is(true));
        assertThat(time10to11.isOverlapped(time11to12), is(false));
        assertThat(time10to11.isOverlapped(time10to11), is(true));

        assertThat(time10to12.isOverlapped(time10to11), is(true));
        assertThat(time10to12.isOverlapped(time11to12), is(true));
        assertThat(time10to12.isOverlapped(time10to12), is(true));

        assertThat(time11to12.isOverlapped(time10to11), is(false));
        assertThat(time11to12.isOverlapped(time10to12), is(true));
        assertThat(time11to12.isOverlapped(time11to12), is(true));
    }

    @Test
    public void testIsOverlappedWithDifferentTimeZone() throws Exception {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZoneId zoneJst = ZoneId.of("JST", ZoneId.SHORT_IDS);
        ZoneId zonePst = ZoneId.of("PST", ZoneId.SHORT_IDS);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(9, 0), zoneJst);
        ZonedDateTime time2 = ZonedDateTime.of(date.minus(Period.ofDays(1)), LocalTime.of(17, 0), zonePst);
        TimeInterval interval1 = TimeInterval.of(time1, Duration.ofHours(2));
        TimeInterval interval2 = TimeInterval.of(time2, Duration.ofHours(2));

        assertThat(interval1.isOverlapped(interval2), is(true));
        assertThat(interval2.isOverlapped(interval1), is(true));
    }


    @Test
    public void testIsOverlappedWithSummerTime() throws Exception {
        // in CET (Central Europe Time)
        // 29 March 2015, 02:00:00 clocks were turned forward 1 hour
        LocalDate date = LocalDate.of(2015, 3, 29);
        ZoneId zoneCet = ZoneId.of("CET", ZoneId.SHORT_IDS);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(1, 0), zoneCet);
        ZonedDateTime time2 = ZonedDateTime.of(date, LocalTime.of(2, 0), zoneCet);
        ZonedDateTime time3 = ZonedDateTime.of(date, LocalTime.of(3, 0), zoneCet);
        ZonedDateTime time4 = ZonedDateTime.of(date, LocalTime.of(4, 0), zoneCet);
        TimeInterval time1to3 = TimeInterval.of(time1, time3);
        TimeInterval time2to4 = TimeInterval.of(time2, time4);

        assertThat(time1to3.isOverlapped(time2to4), is(false));
        assertThat(time2to4.isOverlapped(time1to3), is(false));
    }

    @Test
    public void testOf() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);

        TimeInterval actual = TimeInterval.of(time1, Duration.ofHours(1));

        assertThat(actual.getStartTime(), is(time1));
        assertThat(actual.getEndTime(), is(time1.plus(Duration.ofHours(1))));
    }

    @Test
    public void testOfTimes() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        ZonedDateTime time2 = ZonedDateTime.of(date, LocalTime.of(11, 0), zone);

        TimeInterval actual = TimeInterval.of(time1, time2);

        assertThat(actual.getStartTime(), is(time1));
        assertThat(actual.getEndTime(), is(time2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfNegativeDuration() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        TimeInterval.of(time1, Duration.ofHours(-1));
    }

    @Test(expected = NullPointerException.class)
    public void testOfNullStart() {
        TimeInterval.of(null, Duration.ofHours(1));
    }

    @Test(expected = NullPointerException.class)
    public void testOfNullDuration() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        Duration duration = null;
        TimeInterval.of(time1, duration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfTimesStartIsAfterEnd() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        ZonedDateTime time2 = ZonedDateTime.of(date, LocalTime.of(9, 0), zone);
        TimeInterval.of(time1, time2);
    }

    @Test(expected = NullPointerException.class)
    public void testOfTimesNullStart() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        TimeInterval.of(null, time1);
    }

    @Test(expected = NullPointerException.class)
    public void testOfTimesNullEnd() {
        LocalDate date = LocalDate.of(2015, 9, 8);
        ZonedDateTime time1 = ZonedDateTime.of(date, LocalTime.of(10, 0), zone);
        ZonedDateTime time2 = null;
        TimeInterval.of(time1, time2);
    }
}