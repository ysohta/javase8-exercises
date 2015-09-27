package ch05.ex04;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex04.Cal}.
 * Created by yukiohta on 2015/09/06.
 */
public class CalTest {

    @Test(expected = NullPointerException.class)
    public void testCal() {
        new Cal(null);
    }

    @Test
    public void testToString() throws Exception {
        Cal target = new Cal(LocalDate.of(2013, 3, 1));

        StringBuilder expected = new StringBuilder()
                .append("             1  2  3 \n")
                .append(" 4  5  6  7  8  9 10 \n")
                .append("11 12 13 14 15 16 17 \n")
                .append("18 19 20 21 22 23 24 \n")
                .append("25 26 27 28 29 30 31 \n");

        assertThat(target.toString(), is(expected.toString()));
    }

    @Test
    public void testToStringInCaseOfStartFromMonday() throws Exception {
        Cal target = new Cal(LocalDate.of(2015, 6, 1));

        StringBuilder expected = new StringBuilder()
                .append(" 1  2  3  4  5  6  7 \n")
                .append(" 8  9 10 11 12 13 14 \n")
                .append("15 16 17 18 19 20 21 \n")
                .append("22 23 24 25 26 27 28 \n")
                .append("29 30 \n");

        assertThat(target.toString(), is(expected.toString()));
    }

    @Test
    public void testToStringInCaseOfStartFromSunday() throws Exception {
        Cal target = new Cal(LocalDate.of(2015, 3, 1));

        StringBuilder expected = new StringBuilder()
                .append("                   1 \n")
                .append(" 2  3  4  5  6  7  8 \n")
                .append(" 9 10 11 12 13 14 15 \n")
                .append("16 17 18 19 20 21 22 \n")
                .append("23 24 25 26 27 28 29 \n")
                .append("30 31 \n");

        assertThat(target.toString(), is(expected.toString()));
    }

    @Test
    public void testToStringInCaseOfEndWithMonday() throws Exception {
        Cal target = new Cal(LocalDate.of(2015, 11, 1));

        StringBuilder expected = new StringBuilder()
                .append("                   1 \n")
                .append(" 2  3  4  5  6  7  8 \n")
                .append(" 9 10 11 12 13 14 15 \n")
                .append("16 17 18 19 20 21 22 \n")
                .append("23 24 25 26 27 28 29 \n")
                .append("30 \n");

        assertThat(target.toString(), is(expected.toString()));
    }

    @Test
    public void testToStringInCaseOfEndWithSunday() throws Exception {
        Cal target = new Cal(LocalDate.of(2015, 5, 1));

        StringBuilder expected = new StringBuilder()
                .append("             1  2  3 \n")
                .append(" 4  5  6  7  8  9 10 \n")
                .append("11 12 13 14 15 16 17 \n")
                .append("18 19 20 21 22 23 24 \n")
                .append("25 26 27 28 29 30 31 \n");

        assertThat(target.toString(), is(expected.toString()));
    }

    @Test
    public void testGetDaysOfLastMonth() {
        int actual;
        actual = new Cal(LocalDate.of(2013, 3, 1)).getDaysOfLastMonth();
        assertThat(actual, is(4));

        // start from Monday
        actual = new Cal(LocalDate.of(2015, 6, 1)).getDaysOfLastMonth();
        assertThat(actual, is(0));

        // start from Sunday
        actual = new Cal(LocalDate.of(2015, 3, 1)).getDaysOfLastMonth();
        assertThat(actual, is(6));
    }
}