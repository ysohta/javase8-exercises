package ch05.ex03;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex03.DateTimeUtil}.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtilTest {

    @Test
    public void testNextWeekDay() throws Exception {
        LocalDate date = LocalDate.of(2015, 9, 4);  // Friday
        LocalDate actual = date.with(DateTimeUtil.next(w -> w.getDayOfWeek().getValue() < 6));
        assertThat(actual, is(LocalDate.of(2015, 9, 7)));   // Monday
    }

    @Test
    public void testNextAlwaysTrue() throws Exception {
        LocalDate date = LocalDate.of(2015, 9, 6);
        LocalDate actual = date.with(DateTimeUtil.next(w -> true));
        assertThat(actual, is(LocalDate.of(2015, 9, 7)));
    }

    @Test
    public void testNextLeapDay() throws Exception {
        LocalDate date = LocalDate.of(2015, 9, 6);
        LocalDate actual = date.with(DateTimeUtil.next(w -> w.getMonth() == Month.FEBRUARY && w.getDayOfMonth() == 29));
        assertThat(actual, is(LocalDate.of(2016, 2, 29)));
    }

    @Test(expected = DateTimeException.class)
    public void testNextAboveMax() throws Exception {
        LocalDate.MAX.with(DateTimeUtil.next(w -> false));
    }

    @Test(expected = NullPointerException.class)
    public void testNextPredicateIsNull() throws Exception {
        LocalDate date = LocalDate.of(2015, 9, 6);
        date.with(DateTimeUtil.next(null));
    }
}