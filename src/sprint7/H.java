package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class H {
    int[][] flowers;
    int[][] dp;

    public H(int n, int m) {
        this.flowers = new int[n + 1][m + 1];
        this.dp = new int[n + 1][m + 1];
    }

    //заполняем поле цветочками
    //+добавляем один дополнительный ряд снизу и один ряд слева в качестве каемочки
    private void initializeMatrix(BufferedReader reader, int n, int m) throws IOException {

        for (int i = 0; i < n; i++) {

            final List<Integer> currLine = readLine(reader);

            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    flowers[i][j] = Integer.MIN_VALUE;
                } else {
                    flowers[i][j] = currLine.get(j - 1);
                }
            }
        }

        for (int j = 0; j <= m; j++) {
            flowers[n][j] = Integer.MIN_VALUE;
        }
    }

    //заполняем поле цветочками
    //+добавляем один дополнительный ряд снизу и один ряд слева в качестве каемочки
    private void initializeMatrixMirror(BufferedReader reader, int n, int m) throws IOException {

        for (int i = n - 1; i >= 0; i--) {

            final List<Integer> currLine = readLine(reader);

            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    flowers[i][j] = Integer.MIN_VALUE;
                } else {
                    flowers[i][j] = currLine.get(Math.abs(m - j));
                }
            }
        }

        for (int j = 0; j <= m; j++) {
            flowers[n][j] = Integer.MIN_VALUE;
        }
    }

    private void countFlowersMax(int n, int m) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= m; j++) {
                //базовый случай
                //на первом шаге мы возьмём столько фишек, сколько есть в стартовой ячейке
                // dp[i][j] = flowers[i][j];
                dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1] + flowers[i][j]);
            }
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

            h.countFlowersMax(n, m);

            System.out.println(h.dp[n - 1][m - 1]);

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
