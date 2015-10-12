package ch06.ex10;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex10.WebUtil}.
 * Created by yukiohta on 2015/10/06.
 */
public class WebUtilTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Must have internet connection.
     *
     * @throws Exception
     */
    @Test(timeout = 10_000)
    public void testFindLinks() throws Exception {
        CompletableFuture<Void> actual = WebUtil.findLinks(new URL("http://horstmann.com"));
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);

        assertThat(actual.isDone(), is(true));
    }

    @Test(timeout = 10_000)
    public void testFindLinksLocalFile() throws Exception {
        File html = folder.newFile();
        Files.write(html.toPath(), Arrays.asList("<html><a href=\"https://github.com\"></html>"));

        CompletableFuture<Void> actual = WebUtil.findLinks(html.toPath().toUri().toURL());
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);

        assertThat(actual.isDone(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testFindLinksNull() throws Exception {
        WebUtil.findLinks(null);
    }

    @Test
    public void testReadPage() throws Exception {
        String content = "<html><a href=\"https://github.com\"></html>";
        File html = folder.newFile();
        Files.write(html.toPath(), Arrays.asList(content));

        String actual = WebUtil.readPage(html.toPath().toUri().toURL());

        assertThat(actual, is(content));
    }

    @Test
    public void testReadPageFailed() throws Exception {
        String actual = WebUtil.readPage(new URL("http://"));

        assertThat(actual, nullValue());
    }

    @Test
    public void testGetLinks() throws Exception {
        String content = "<a href=\"http://www.google.com\"><a href=\"http://www.yahoo.co.jp\">";
        List<URL> actuals = WebUtil.getLinks(content);

        assertThat(actuals.size(), is(2));
        assertThat(actuals.get(0).toString(), is("http://www.google.com"));
        assertThat(actuals.get(1).toString(), is("http://www.yahoo.co.jp"));
    }

    @Test
    public void testGetLinksInvalidUrl() throws Exception {
        String content = "<html><a href=\"NotLink\"></html>";
        List<URL> actuals = WebUtil.getLinks(content);

        assertThat(actuals, nullValue());
    }
}