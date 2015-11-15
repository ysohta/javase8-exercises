package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class consists of utility methods to create a file formatted in lower case.
 * Created by yukiohta on 2015/11/08.
 */
public class LowerCaseFormatter {
    LowerCaseFormatter() {
    }

    /**
     * Writes file in lower case
     *
     * @param inFile  input file
     * @param outFile output file
     * @throws IOException if an I/O error occurs.
     */
    public static void write(String inFile, String outFile) throws IOException {
        Scanner in = null;
        PrintWriter out = null;

        try {
            in = new Scanner(Paths.get(inFile));
            out = new PrintWriter(outFile);

            while (in.hasNext())
                out.println(in.next().toLowerCase());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } finally {
                    in.close();
                }
            } else {
                if (in != null) {
                    in.close();
                }
            }
        }
    }
}
