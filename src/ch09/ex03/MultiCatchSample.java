package ch09.ex03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by yukiohta on 2015/11/15.
 */
public class MultiCatchSample {
    private MultiCatchSample() {
    }

    // Throws super class of exceptions.
    public void process() throws IOException {
        try {
            Random random = new Random();
            if (random.nextInt() % 2 == 0) {
                throw new FileNotFoundException();
            } else {
                throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException ex) {
            throw ex;
        }
    }

    // Throws both of exceptions.
    public void process2() throws FileNotFoundException, UnknownHostException {
        try {
            Random random = new Random();
            if (random.nextInt() % 2 == 0) {
                throw new FileNotFoundException();
            } else {
                throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException ex) {
            throw ex;
        }
    }
}
