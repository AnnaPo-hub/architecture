package sprint3.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());
            genBinary(0, 0, n, "");
        }
    }

    static void genBinary(int openCounter, int closeCounter, int n, String prefix) {
        if (openCounter == n && closeCounter == n) {
            System.out.println(prefix);
        } else {
            if (openCounter <= n) {
                genBinary(openCounter + 1, closeCounter, n, prefix + "(");
            }
            if (closeCounter < openCounter) {
                genBinary(openCounter, closeCounter + 1, n, prefix + ")");
            }
        }
    }
}
