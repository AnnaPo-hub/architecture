package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class I {
    int[][] flowers;
    int[][] dp;

    public I(int n, int m) {
        this.flowers = new int[n + 1][m + 1];
        this.dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == 0 || i == 0) {
                    dp[i][j] = -1;
                }
            }

        }
    }

    //заполняем поле цветочками
    //+добавляем один дополнительный ряд снизу и один ряд слева в качестве каемочки
    private void initializeMatrixMirror(BufferedReader reader, int n, int m) throws IOException {

        for (int i = n; i > 0; i--) {
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
            flowers[0][j] = Integer.MIN_VALUE;
        }
    }

    private void countFlowersMax(int n, int m) {
        dp[1][1] = flowers[1][1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //базовый случай
                //на первом шаге мы возьмём столько фишек, сколько есть в стартовой ячейке
                if (i > 1 || j > 1) {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]) + flowers[i][j];
                }
            }
        }
    }

    private String findWay(int n, int m) {
        final StringBuilder kondratinaWay = new StringBuilder();
        int i = n;
        int j = m;


        while (i > 0 && j > 0) {
            if (i == 1 && j == 1)
                break;
            int down = dp[i - 1][j];
            int left = dp[i][j - 1];
            if (down > left) {
                kondratinaWay.append("U");
                i -= 1;
            } else if (down < left) {
                j -= 1;
                kondratinaWay.append("R");
            } else {
                kondratinaWay.append("R");
                j -= 1;
            }
        }
        if (j == 1) {
            while (i != 1) {
                kondratinaWay.append("U");
                i -= 1;
            }

        }
        if (i == 1) {
            while (i != 1) {
                kondratinaWay.append("U");
                i -= 1;
            }

        }
        return kondratinaWay.reverse().toString();
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<Integer> matrixSize = readList(reader);
            final Integer n = matrixSize.get(0);
            final Integer m = matrixSize.get(1);

            I h = new I(n, m);
            h.initializeMatrixMirror(reader, n, m);

            h.countFlowersMax(n, m);

            System.out.println(h.dp[n][m]);
            System.out.println(h.findWay(n, m));

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
