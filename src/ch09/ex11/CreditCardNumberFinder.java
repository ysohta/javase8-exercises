package ch09.ex11;

import java.io.File;
import java.io.IOException;

/**
 * This class consists of utility methods to find credit card numbers.
 * Created by yukiohta on 2015/11/16.
 */
public class CreditCardNumberFinder {
    private static final String visaExpression = "\\b[0-9]\\{4\\}-[0-9]\\{4\\}-[0-9]\\{4\\}-[0-9]\\{4\\}\\b";
    private static final String amexExpression = "\\b[0-9]\\{4\\}-[0-9]\\{6\\}-[0-9]\\{5\\}\\b";
    private static final String dinersExpression = "\\b[0-9]\\{4\\}-[0-9]\\{6\\}-[0-9]\\{4\\}\\b";

    public static void find(File directory) {
        String pattern = visaExpression + "\\|" + amexExpression + "\\|" + dinersExpression;
        ProcessBuilder builder = new ProcessBuilder("grep", "-re", pattern, ".");
        builder.directory(directory);
        builder.inheritIO();
        try {
            builder.start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        if (args.length < 1) {
            System.out.println("Usage: java ch09.ex11.CreditCardNumberFinder [path]");
            System.exit(1);
        }

        find(new File(args[0]));
    }
}
