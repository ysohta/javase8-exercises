package ch05.ex06;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex06.DateTimeUtil}.
 * Created by yukiohta on 2015/09/07.
 */
public class DateTimeUtilTest {

    @Test
    public void testListDatesFriday13thIn20th() throws Exception {
        List<LocalDate> actuals = DateTimeUtil.listDates(LocalDate.of(1901, 1, 1), LocalDate.of(2001, 1, 1),
                w -> w.getDayOfMonth() == 13 && w.getDayOfWeek() == DayOfWeek.FRIDAY);
        assertThat(actuals.size(), is(171));
    }

    @Test
    public void testListDatesChristmasIn20th() throws Exception {
        List<LocalDate> actuals = DateTimeUtil.listDates(LocalDate.of(1901, 1, 1), LocalDate.of(2001, 1, 1),
                w -> w.getMonth() == Month.DECEMBER && w.getDayOfMonth() == 25);
        assertThat(actuals.size(), is(100));
        for (int i = 0; i < 100; i++) {
            int year = 1901 + i;
            assertThat(actuals.get(i).getYear(), is(year));
            assertThat(actuals.get(i).getMonth(), is(Month.DECEMBER));
            assertThat(actuals.get(i).getDayOfMonth(), is(25));
        }
    }

    @Test
    public void testListDatesEvenDays() throws Exception {
        List<LocalDate> actuals = DateTimeUtil.listDates(LocalDate.of(2015, 9, 5), LocalDate.of(2015, 9, 10),
                w -> w.getDayOfMonth() % 2 == 0);
        assertThat(actuals.size(), is(2));
        assertThat(actuals.get(0), is(LocalDate.of(2015, 9, 6)));
        assertThat(actuals.get(1), is(LocalDate.of(2015, 9, 8)));
    }

    @Test
    public void testListDatesCheckRange() throws Exception {
        List<LocalDate> actuals = DateTimeUtil.listDates(LocalDate.of(2015, 9, 5), LocalDate.of(2015, 9, 7),
                w -> true);
        assertThat(actuals.size(), is(2));
        assertThat("startDateInclusive must be inclusive", actuals.get(0), is(LocalDate.of(2015, 9, 5)));
        assertThat("endDateExclusive must be exclusive", actuals.get(1), is(LocalDate.of(2015, 9, 6)));
    }

    @Test
    public void testListDatesSameDate() throws Exception {
        List<LocalDate> actuals = DateTimeUtil.listDates(LocalDate.of(2015, 9, 5), LocalDate.of(2015, 9, 5),
                w -> true);
        assertThat(actuals.size(), is(0));
    }

    @Test
    public void testListDatesPreviousDate() throws Exception {
        List<LocalDate> actuals = DateTimeUtil.listDates(LocalDate.of(2015, 9, 5), LocalDate.of(2015, 9, 4),
                w -> true);
        assertThat(actuals.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void testListDatesNullStart() {
        DateTimeUtil.listDates(null, LocalDate.now(), w -> true);
    }

    @Test(expected = NullPointerException.class)
    public void testListDatesNullEnd() {
        DateTimeUtil.listDates(LocalDate.now(), null, w -> true);
    }

    @Test(expected = NullPointerException.class)
    public void testListDatesNullPredicate() {
        DateTimeUtil.listDates(LocalDate.now(), LocalDate.now(), null);
    }
}