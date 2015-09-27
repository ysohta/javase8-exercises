package ch05.ex12;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This class is implementation of ScheduleNotifier.
 * Created by yukiohta on 2015/09/20.
 */
public class SchedulerRunner implements ScheduleNotifier {
    private Scheduler scheduler;

    /**
     * Constructs SchedulerRunner object.
     */
    public SchedulerRunner() {
        this.scheduler = new Scheduler(this);
    }

    /**
     * Notifies schedule event.
     *
     * @param event schedule event
     */
    @Override
    public void eventNotified(ScheduleEvent event) {
        System.out.println("Notification for " + event.getNotificationZonedTime());
    }

    /**
     * Entry point to run
     *
     * @param args arguments to create schedule
     */
    public static void main(String... args) {
        if (args.length < 3) {
            printUsage();
            System.exit(1);
        }

        try {
            LocalDateTime start = LocalDateTime.parse(args[0]);
            LocalDateTime end = LocalDateTime.parse(args[1]);
            ZoneId timezone = ZoneId.of(args[2]);
            run(start, end, timezone);
        } catch (Exception e) {
            System.out.println("Failed to parse. " + e.getMessage());
            printUsage();
            System.exit(1);
        }
    }

    private static void run(LocalDateTime start, LocalDateTime end, ZoneId timezone) throws IOException {
        SchedulerRunner runner = new SchedulerRunner();
        runner.scheduler.register(
                new ScheduleEvent.Builder()
                        .setStart(start)
                        .setEnd(end)
                        .setTimezone(timezone)
                        .create());

        runner.scheduler.start();

        System.out.println("\nInput something to stop");
        System.in.read();

        runner.scheduler.stop();
    }

    private static void printUsage() {
        System.out.println("Usage:      SchedulerRunner <start date time> <end date time> <zone ID>");
        System.out.println("Example:    SchedulerRunner 2015-09-20T21:00:00 2015-09-21T22:00:00 GMT+9");
    }
}
