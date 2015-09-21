package ch05.ex12;


import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * This class represents Scheduler to manage schedule notices.
 * Created by yukiohta on 2015/09/13.
 */
public class Scheduler {
    private static int initialDelay = 1;
    private static int delay = 1;
    private static TimeUnit timeUnit = TimeUnit.SECONDS;
    private final ScheduleNotifier notifier;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private BlockingQueue<ScheduleEvent> notifications = new PriorityBlockingQueue<>();
    private BlockingQueue<ScheduleEvent> archives = new LinkedBlockingQueue<>();
    private boolean isStarted = false;

    /**
     * Constructs Scheduler object.
     *
     * @param notifier an object to be notified
     * @throws NullPointerException if notifier is null
     */
    public Scheduler(ScheduleNotifier notifier) {
        Objects.requireNonNull(notifier, "notifier must not be null");
        this.notifier = notifier;
    }

    /**
     * Starts notification.
     *
     * @throws IllegalStateException if the object already started
     */
    public void start() {
        if (isStarted)
            throw new IllegalStateException("already started");

        executorService.scheduleWithFixedDelay(() -> {
            ZonedDateTime now = ZonedDateTime.now();

            ScheduleEvent event;
            while ((event = notifications.peek()) != null) {
                if (event.getNotificationZonedTime().isAfter(now))
                    break;

                notifier.eventNotified(event);
                archives.add(notifications.poll());
            }
        }, initialDelay, delay, timeUnit);

        isStarted = true;
    }

    /**
     * Stops notifications.
     */
    public void stop() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isStarted = false;
    }

    /**
     * Registers event to the scheduler.
     *
     * @param event schedule event
     */
    public void register(ScheduleEvent event) {
        notifications.offer(event);
    }

    public void clear() {
        notifications.clear();
        archives.clear();
    }

    /**
     * Returns initial delay for checking interval for notification.
     * This method can be used only for testing.
     *
     * @return initial delay
     */
    static int getInitialDelay() {
        return initialDelay;
    }

    /**
     * Sets initial delay for checking interval for notification.
     * This method can be used only for testing.
     *
     * @param initialDelay initial delay
     */
    static void setInitialDelay(int initialDelay) {
        Scheduler.initialDelay = initialDelay;
    }

    /**
     * Returns delay for checking interval for notification.
     * This method can be used only for testing.
     *
     * @return delay delay
     */
    static int getDelay() {
        return delay;
    }

    /**
     * Sets delay for checking interval for notification.
     * This method can be used only for testing.
     *
     * @param delay delay
     */
    static void setDelay(int delay) {
        Scheduler.delay = delay;
    }

    /**
     * Returns time unit for checking interval for notification.
     * This method can be used only for testing.
     *
     * @return time unit
     */
    static TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Sets time unit for checking interval for notification.
     * This method can be used only for testing.
     *
     * @param timeUnit time unit
     */
    static void setTimeUnit(TimeUnit timeUnit) {
        Scheduler.timeUnit = timeUnit;
    }
}
