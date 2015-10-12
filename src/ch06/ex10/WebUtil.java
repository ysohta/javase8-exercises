package ch06.ex10;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class consists of utility method for the Web.
 * Created by yukiohta on 2015/10/06.
 */
public class WebUtil {

    /**
     * Find links in the URL and print asynchronously.
     *
     * @param url URL
     * @return new CompletableFuture
     * @throws NullPointerException if url is null
     */
    public static CompletableFuture<Void> findLinks(URL url) {
        Objects.requireNonNull(url, "url must not be null");

        return CompletableFuture.supplyAsync(() -> readPage(url))
                .thenApply(WebUtil::getLinks)
                .thenAccept(System.out::println);
    }

    static String readPage(URL url) {
        try {
            File file = new File(url.toURI());
            return Files.lines(file.toPath()).collect(Collectors.joining());
        } catch (URISyntaxException | IOException e) {
            return null;
        }
    }

    static List<URL> getLinks(String page) {
        List<URL> links = new ArrayList<>();
        Pattern p = Pattern.compile("<a\\shref=['\"]([^'\"]+)['\"]>");
        Matcher m = p.matcher(page);
        while (m.find()) {
            try {
                URL url = new URL(m.group(1));
                links.add(url);
            } catch (MalformedURLException e) {
                return null;
            }
        }

        return links;
    }
}