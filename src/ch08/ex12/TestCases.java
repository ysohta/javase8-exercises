package ch08.ex12;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is repeating annotation for {@link ch08.ex12.TestCase}.
 * Created by yukiohta on 2015/10/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TestCases {
    /**
     * Returns array of {@link ch08.ex12.TestCase}.
     *
     * @return test cases
     */
    TestCase[] value();
}