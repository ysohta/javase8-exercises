package ch08.ex14;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * This is test class of {@link ch08.ex14.ObjectsExtended}.
 * Created by yukiohta on 2015/10/27.
 */
public class ObjectsExtendedTest {

    public static void func1(String str) {
        ObjectsExtended.requireNonNullParams(str);
    }

    public void func2(String str, Object obj) {
        ObjectsExtended.requireNonNullParams(str, obj);
    }

    public void funcOverLoaded(String str, List<String> list) {
        ObjectsExtended.requireNonNullParams(str, list);
    }

    public void funcOverLoaded(String str, String str2) {
        ObjectsExtended.requireNonNullParams(str, str2);
    }

    @Test
    public void testRequireNonNullParams1ParamNonNull() throws Exception {
        func1("hoge");
    }

    @Test
    public void testRequireNonNullParams1ParamNull() throws Exception {
        try {
            func1(null);
            fail();
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("[arg0] must not be null"));
        }
    }

    @Test
    public void testRequireNonNullParams2ParamNonNull() throws Exception {
        func2("hoge", "hoge");
    }

    @Test
    public void testRequireNonNullParams2ParamBothNull() throws Exception {
        try {
            func2(null, null);
            fail();
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("[arg0, arg1] must not be null"));
        }
    }

    @Test
    public void testRequireNonNullParams2ParamFirstNull() throws Exception {
        try {
            func2(null, "hoge");
            fail();
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("[arg0] must not be null"));
        }
    }

    @Test
    public void testRequireNonNullParams2ParamSecondNull() throws Exception {
        try {
            func2("hoge", null);
            fail();
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("[arg1] must not be null"));
        }
    }

    @Test
    public void testRequireNonNullParamsOverloaded() throws Exception {
        String str = null;
        try {
            funcOverLoaded("hoge", str);
            fail();
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is("parameter of funcOverLoaded method must not be null"));
        }
    }
}