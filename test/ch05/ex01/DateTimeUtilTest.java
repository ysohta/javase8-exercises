package ch05.ex01;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex01.DateTimeUtil}.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtilTest {

    @Test
    public void testGetProgrammersDayOf() {
        LocalDate actual;
        LocalDate expected;

        for (int year = 2010; year < 2020; year++) {
            actual = DateTimeUtil.getProgrammersDayOf(year);
            expected = LocalDate.of(year, 1, 1).plusDays(255);
            assertThat(actual, is(expected));
        }
    }

    @Test
    public void testGetProgrammersDayOfWithinRange() {
        DateTimeUtil.getProgrammersDayOf(Year.MIN_VALUE);
        DateTimeUtil.getProgrammersDayOf(Year.MAX_VALUE);
    }

    @Test(expected = DateTimeException.class)
    public void testGetProgrammersDayOfBelowMin() {
        DateTimeUtil.getProgrammersDayOf(Year.MIN_VALUE - 1);
    }

    @Test(expected = DateTimeException.class)
    public void testGetProgrammersDayOfAboveMax() {
        DateTimeUtil.getProgrammersDayOf(Year.MAX_VALUE + 1);
    }
}