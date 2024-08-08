package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    //https://contest.yandex.ru/contest/22450/run-report/112246509/
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int pressQuantity = Integer.parseInt(reader.readLine()) * 2;
            int[] digitNumbers = new int[10];
            int score = 0;

            for (int i = 0; i < 4; i++) {
                final String currLine = reader.readLine().trim();
                final String[] split = currLine.split("");
                for (String s : split) {
                    if (!s.equals(".")) {
                        final int currDigit = Integer.parseInt(s);
                        digitNumbers[currDigit] += 1;
                    }
                }
            }
            for (int i = 1; i < digitNumbers.length; i++) {
                if (digitNumbers[i] > 0 && digitNumbers[i] <= pressQuantity) {
                    ++score;
                }
            }
            System.out.print(score);
        }
    }
}