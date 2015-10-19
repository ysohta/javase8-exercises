package ch08.ex07;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

/**
 * This class shows comparison for the methods of {@link Comparator}.
 * Created by yukiohta on 2015/10/19.
 */
public class ComparatorComparison {

    public static void main(String... args) {
        String[] source = {"dog", "Dogs", null, "HotDog"};
        List<String> list = Arrays.asList(source);

        // with reversed
        Collections.sort(list, (Comparator<String>) nullsFirst(naturalOrder()).reversed());
        System.out.println("with reversed:");
        System.out.println(list);   // [dog, HotDog, Dogs, null]


        // without reversed
        list = Arrays.asList(source);
        Collections.sort(list, nullsLast(reverseOrder()));
        System.out.println("without reversed:");
        System.out.println(list);   //  [dog, HotDog, Dogs, null]
    }
}
