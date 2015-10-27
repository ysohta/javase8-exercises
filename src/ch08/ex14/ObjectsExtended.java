package ch08.ex14;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class consists of utility methods for {@link Objects}.
 * Created by yukiohta on 2015/10/27.
 */
public class ObjectsExtended {
    private static final int targetStackTrace = 2;

    /**
     * Checks if the parameters of a method contain {@code null}.<br>
     * This method must be used for null-check of parameters. Thrown {@link NullPointerException} could have a message
     * containing the name of parameters which is null. It does not work when calling method has overloaded method.
     *
     * @param params parameters of a method
     * @throws NullPointerException if objects contain null
     */
    public static void requireNonNullParams(Object... params) {
        List<Integer> nullIndex = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                nullIndex.add(i);
            }
        }

        if (nullIndex.isEmpty())
            return;

        List<Method> methods = getCallingMethods(Thread.currentThread().getStackTrace()[targetStackTrace]);

        boolean hasOverload = methods.size() > 1;
        Method m = methods.get(0);
        Parameter[] parameters = m.getParameters();

        StringBuilder sb = new StringBuilder();
        if (!hasOverload) {
            List<String> nullParams = new ArrayList<>();
            nullIndex.forEach(index -> nullParams.add(parameters[index].getName()));
            sb.append(nullParams.toString());
        } else {
            sb.append("parameter of ");
            sb.append(m.getName());
            sb.append(" method");
        }
        sb.append(" must not be null");

        throw new NullPointerException(sb.toString());
    }

    public static void main(String... args) {
        // must compile with option "-parameters" to print actual parameter names

        // throw nothing
        func("str", Collections.emptyList(), new Object());

        try {
            System.out.println("str is null");
            func(null, Collections.emptyList(), new Object());
        } catch (Exception e) {
            // print "[str] must not be null"
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("str and obj are null");
            func(null, Collections.emptyList(), null);
        } catch (Exception e) {
            // print "[str, obj] must not be null"
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("str, list and obj are null");
            func(null, null, null);
        } catch (Exception e) {
            // print "[str, list, obj] must not be null"
            System.out.println(e.getMessage());
        }
    }

    public static void func(String str, List<Integer> list, Object obj) {
        ObjectsExtended.requireNonNullParams(str, list, obj);
        // do something
    }

    /**
     * Returns list of calling methods searched by the name of method described in stack trace.
     *
     * @return list of calling methods
     */
    private static List<Method> getCallingMethods(StackTraceElement stackTraceTarget) {
        String methodName = stackTraceTarget.getMethodName();
        String className = stackTraceTarget.getClassName();

        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Stream.of(clazz.getMethods())
                .filter(m -> m.getName().equals(methodName))
                .collect(Collectors.toList());
    }
}
