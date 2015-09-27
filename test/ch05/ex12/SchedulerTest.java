package ch05.ex12;

import org.junit.*;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * This is test class of {@link ch05.ex12.Scheduler}.
 * Created by yukiohta on 2015/09/15.
 */
public class SchedulerTest {

    ScheduleNotifier mock;
    Scheduler target;


    @BeforeClass
    public static void setupClass() {
        Scheduler.setTimeUnit(TimeUnit.MICROSECONDS);
    }

    @AfterClass
    public static void tearsdownClass() {
        Scheduler.setTimeUnit(TimeUnit.SECONDS);
    }

    @Before
    public void setup() {
        mock = Mockito.mock(ScheduleNotifier.class);
        target = new Scheduler(mock);
    }

    @After
    public void tearsdown() {
        target.stop();
    }


    @Test
    public void testStartNotificationNeeded() throws Exception {
        // preparation
        ScheduleEvent event = new ScheduleEvent.Builder()
                .setStart(LocalDateTime.now().minus(Duration.ofHours(2)))
                .create();
        target.register(event);

        // execution
        target.start();

        // confirmation
        Thread.sleep(1);
        Mockito.verify(mock, Mockito.atLeastOnce()).eventNotified(event);
    }

    @Test
    public void testStartNotificationNotNeeded() throws Exception {
        // preparation
        ScheduleEvent event = new ScheduleEvent.Builder()
                .setStart(LocalDateTime.now().plus(Duration.ofHours(2)))
                .setEnd(LocalDateTime.now().plus(Duration.ofHours(3)))
                .create();
        target.register(event);

        // execution
        target.start();

        // confirmation
        Thread.sleep(1);
        Mockito.verify(mock, Mockito.never()).eventNotified(event);
    }

    @Test(expected = IllegalStateException.class)
    public void testStartNotificationAlreadyStarted() throws Exception {
        // preparation
        ScheduleEvent event = new ScheduleEvent.Builder()
                .setStart(LocalDateTime.now().plus(Duration.ofHours(2)))
                .setEnd(LocalDateTime.now().plus(Duration.ofHours(3)))
                .create();
        target.register(event);

        // execution
        target.start();
        target.start();
    }

    @Test
    public void testRegister() throws Exception {
        // preparation
        ScheduleEvent event = new ScheduleEvent.Builder()
                .setStart(LocalDateTime.now().plus(Duration.ofHours(2)))
                .setEnd(LocalDateTime.now().plus(Duration.ofHours(3)))
                .create();
        target.register(event);
    }

    @Test
    public void testClear() throws Exception {
        // preparation
        ScheduleEvent event = new ScheduleEvent.Builder()
                .setStart(LocalDateTime.now().minus(Duration.ofHours(2)))
                .create();
        target.start();

        // execution
        target.clear();

        target.register(event);

        // confirmation
        Thread.sleep(1);
        Mockito.verify(mock, Mockito.atLeastOnce()).eventNotified(event);
    }
}