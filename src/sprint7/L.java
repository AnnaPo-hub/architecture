package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class L {
    //максимальная масса, которую можно забрать с собой
    int[][] dp;

    public L(int goldBarQuantity, int k) {
        this.dp = new int[goldBarQuantity + 1][k + 1];
        for (int i = 0; i <= goldBarQuantity; i++) {
        }
    }

    //Одним из параметров динамики будет вместимость рюкзака
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<Integer> firstLine = readList(reader);
            final Integer goldBarQuantity = firstLine.get(0);
            //вместимость рюкзака
            final Integer j = firstLine.get(1);
//слитки с номерами от 0 до i
            final List<Integer> barWeights = readList(reader);
            Collections.sort(barWeights);
            final L l = new L(goldBarQuantity, j);
            for (int i = 0; i < goldBarQuantity; i++) {

                for (int k = 0; k <= j; k++) {

                    //предыдущий максимум
                    int prevMax = i - 1 < 0 ? 0 : l.dp[i - 1][k];

                    // вес текущего элемента
                    int currWeight = barWeights.get(i);

                    int x = k - currWeight;
                    if (x < 0) {

                        l.dp[i][k] = prevMax;
                    } else if (x == 0) {
                        l.dp[i][k] = currWeight;
                    } else {
                        //то, что поместится в оставшемся  свободном месте   в рюкзаке
                        int b = k - currWeight < 0 || i - 1 <= 0 ? 0 : l.dp[i - 1][k - currWeight];
                        l.dp[i][k] = Integer.max(prevMax, currWeight + b);
                    }
                }
            }
            System.out.println(l.dp[goldBarQuantity - 1][j]);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
