package sprint7.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    static short[][] dp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<String> firstLine = readList(reader);
            int firstLineSize = firstLine.size();
            final List<String> secondLine = readList(reader);
            int secondLineSize = secondLine.size();

            dp = new short[firstLineSize + 1][secondLineSize + 1];

            for (int i = 0; i <= firstLineSize; i++) {
                for (int j = 0; j <= secondLineSize; j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 0;
                    } else if (i > 0 && j == 0) {
                        dp[i][j] = (short) i;
                    } else if (j > 0 && i == 0) {
                        dp[i][j] = (short) j;
                    } else {
                        int m = firstLine.get(i - 1).equals(secondLine.get(j - 1)) ? 0 : 1;
                        dp[i][j] = (short) Integer.min(dp[i - 1][j - 1] + m, Integer.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
                    }
                }
            }
            System.out.println(dp[firstLineSize][secondLineSize]);
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .collect(Collectors.toList());
    }
}
