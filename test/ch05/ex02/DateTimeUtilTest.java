package ch05.ex02;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex02.DateTimeUtil}.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtilTest {

    @Test
    public void testPlusYearRepeatedly1Year1Time() throws Exception {
        LocalDate actual = DateTimeUtil.plusYearsRepeatedly(LocalDate.of(2000, 2, 29), 1, 1);
        assertThat(actual, is(LocalDate.of(2001, 2, 28)));
    }

    @Test
    public void testPlusYearRepeatedly4Years1Time() throws Exception {
        LocalDate actual = DateTimeUtil.plusYearsRepeatedly(LocalDate.of(2000, 2, 29), 4, 1);
        assertThat(actual, is(LocalDate.of(2004, 2, 29)));
    }

    @Test
    public void testPlusYearRepeatedly1Year4Times() throws Exception {
        LocalDate actual = DateTimeUtil.plusYearsRepeatedly(LocalDate.of(2000, 2, 29), 1, 4);
        assertThat(actual, is(LocalDate.of(2004, 2, 28)));
    }


    @Test
    public void testPlusYearRepeatedlyValidCases() throws Exception {
        LocalDate actual;

        actual = DateTimeUtil.plusYearsRepeatedly(LocalDate.of(2015, 9, 6), 0, 100);
        assertThat(actual, is(LocalDate.of(2015, 9, 6)));

        actual = DateTimeUtil.plusYearsRepeatedly(LocalDate.of(2015, 9, 6), -1, 1);
        assertThat(actual, is(LocalDate.of(2014, 9, 6)));
    }

    @Test(expected = DateTimeException.class)
    public void testPlusYearRepeatedlyAboveMax() throws Exception {
        DateTimeUtil.plusYearsRepeatedly(LocalDate.MAX, 1, 1);
    }

    @Test(expected = DateTimeException.class)
    public void testPlusYearRepeatedlyBelowMin() throws Exception {
        DateTimeUtil.plusYearsRepeatedly(LocalDate.MIN, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlusYearRepeatedlyBelowMinRepeat() throws Exception {
        DateTimeUtil.plusYearsRepeatedly(LocalDate.now(), 1, -1);
    }
}