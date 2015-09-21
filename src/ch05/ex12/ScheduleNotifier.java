package ch05.ex12;

/**
 * This is interface to be notified with ScheduleEvent.
 * Created by yukiohta on 2015/09/13.
 */
public interface ScheduleNotifier {

    /**
     * Notifies schedule event.
     *
     * @param event schedule event
     */
    void eventNotified(ScheduleEvent event);
}
