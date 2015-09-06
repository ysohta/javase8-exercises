package ch05.ex05;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex05.DateTimeUtil}.
 * Created by yukiohta on 2015/09/06.
 */
public class DateTimeUtilTest {

    @Test
    public void testDaysBetween() throws Exception {
        long actual;
        actual = DateTimeUtil.daysBetween(LocalDate.of(2015, 9, 5), LocalDate.of(2015, 9, 6));
        assertThat(actual, is(1L));

        actual = DateTimeUtil.daysBetween(LocalDate.of(2014, 9, 6), LocalDate.of(2015, 9, 6));
        assertThat(actual, is(365L));
    }

    @Test
    public void testDaysBetweenNegative() throws Exception {
        long actual;
        actual = DateTimeUtil.daysBetween(LocalDate.of(2015, 9, 6), LocalDate.of(2015, 9, 5));
        assertThat(actual, is(-1L));

        actual = DateTimeUtil.daysBetween(LocalDate.of(2015, 9, 6), LocalDate.of(2014, 9, 6));
        assertThat(actual, is(-365L));
    }

    @Test
    public void testDaysBetweenLeapDay() throws Exception {
        long actual;
        actual = DateTimeUtil.daysBetween(LocalDate.of(2012, 2, 27), LocalDate.of(2012, 3, 1));
        assertThat(actual, is(3L));
    }

    @Test
    public void testDaysBetweenAlonzosBirthdayAndNow() throws Exception {
        long actual;
        actual = DateTimeUtil.daysBetween(LocalDate.of(1903, 6, 14), LocalDate.of(2015, 9, 6));
        assertThat(actual, is(40992L));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysBetweenNullStart() {
        DateTimeUtil.daysBetween(null, LocalDate.now());
    }

    @Test(expected = NullPointerException.class)
    public void testDaysBetweenNullEnd() {
        DateTimeUtil.daysBetween(LocalDate.now(), null);
    }
}