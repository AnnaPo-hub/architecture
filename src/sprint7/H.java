package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class H {
    int[][] dp;

    public H(int n, int m) {
        this.dp = new int[n + 1][m + 1];
    }

    //заполняем поле цветочками
    //+добавляем один дополнительный ряд снизу и один ряд слева в качестве каемочки
    private void initializeMatrix(BufferedReader reader, int n, int m) throws IOException {

        for (int i = 0; i < n; i++) {

            final List<Integer> currLine = readLine(reader);

            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    dp[i][j] = Integer.MIN_VALUE;
                } else {
                    dp[i][j] = currLine.get(j - 1);
                }
            }
        }

        for (int j = 0; j <= m; j++) {
            dp[n][j] = Integer.MIN_VALUE;
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<Integer> matrixSize = readList(reader);
            final Integer n = matrixSize.get(0);
            final Integer m = matrixSize.get(1);

            H h = new H(n, m);
            h.initializeMatrix(reader, n, m);

            System.out.println("matrix is ready ");

        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<Integer> readLine(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
