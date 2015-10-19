package ch08.ex08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents an example for the usage of CheckedQueue.
 * Created by yukiohta on 2015/10/19.
 */
@SuppressWarnings("unchecked")
public class CheckedQueueExample {
    public static void main(String... args) {
        // CheckedQueue could be used for API which does not have Generics.
        Queue q = getQueueFromJava1_4();
        Queue<String> checked = Collections.checkedQueue(q, String.class);

        try {
            getMoreWork(checked);
        } catch (ClassCastException e) {
            // can detect if invalid type is inserted
            System.out.println("ClassCastException is caught.");
            e.printStackTrace();
        }
    }

    public static Queue getQueueFromJava1_4() {
        LinkedList queue = new LinkedList();
        queue.add("foo");
        queue.add("bar");
        return queue;
    }

    public static void getMoreWork(Queue q) {
        q.add(new Object());
    }
}
