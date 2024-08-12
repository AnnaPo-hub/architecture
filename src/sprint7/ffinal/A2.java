package sprint7.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A2 {
    //максимальная масса, которую можно забрать с собой
    static short[][] dp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<String> firstLine = readList(reader);
            int firstLineSize = firstLine.size();
            final List<String> secondLine = readList(reader);
            int secondLineSize = secondLine.size();

            int maxLine = Integer.max(firstLineSize, secondLineSize);

            dp = new short[firstLineSize + 1][secondLineSize + 1];

            for (int i = 0; i < firstLineSize; i++) {
                for (int j = 0; j < secondLineSize; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                    } else if (firstLine.get(i).equals(secondLine.get(j))) {
                        dp[i][j] = (short) (dp[i - 1][j - 1] + 1);
                    } else {
                        dp[i][j] = (short) Integer.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            //   System.out.println("что получилось в  нижней правой ячейке " + dp[firstLineSize-1][secondLineSize-1]);
            System.out.println(maxLine - dp[firstLineSize - 1][secondLineSize - 1]);
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .collect(Collectors.toList());
    }
}
