package ch06.ex11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * This class represents password authentication simulation input from console.
 * Created by yukiohta on 2015/10/10.
 */
public class PasswordChecker {

    public static void main(String... args) {
        CompletableFuture<PasswordAuthentication> future =
                CompletableFutureUtil.repeat(() -> readPassword(), (s) -> checkPassword(s));

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);

        System.out.println(future.isDone() ? "Authorized" : "Timeout");
    }

    private static PasswordAuthentication readPassword() {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input password:");
            String password = buf.readLine();
            return new PasswordAuthentication(null, password.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean checkPassword(PasswordAuthentication pass) {
        System.out.println("Authorizing...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean result = Arrays.equals(pass.getPassword(), "secret".toCharArray());
        System.out.println(result ? "SUCCESS" : "FAILED");
        return result;
    }
}
