package ch08.ex11;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

/**
 * This class represents client for basic authentication.
 * Created by yukiohta on 2015/10/22.
 */
public class BasicAuthenticationClient {
    private BasicAuthenticationClient() {
    }

    public static void main(String... args) {
        if (args.length != 3) {
            printUsage();
            System.exit(1);
        }

        try {
            URL url = new URL(args[0]);
            System.out.println(getContent(url, args[1], args[2]));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            printUsage();
            System.exit(1);
        }
    }

    /**
     * Returns content in the URL with basic authentication.
     *
     * @param url      URL
     * @param username user name for basic authentication
     * @param password password for basic authentication
     * @return content
     * @throws IOException if an I/O exception occurs.
     */
    public static String getContent(URL url, String username, String password) throws IOException {
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(username, "username must not be null");
        Objects.requireNonNull(password, "password must not be null");

        URLConnection connection = url.openConnection();
        String original = username + ":" + password;
        String encodedString = Base64.getEncoder().encodeToString(original.getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encodedString);

        try (InputStream in = connection.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int i;
            while ((i = in.read()) != -1) {
                out.write(i);
            }

            return new String(out.toByteArray());
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java ch08.ex11.BasicAuthenticationClient url username password");
    }
}
