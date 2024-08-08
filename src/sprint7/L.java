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
            for (int j = 0; j <= k; j++) {
                if (j == 0 || i == 0 || (j == 0 && i == 0)) {
                    dp[i][j] = -1;
                }
            }

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
            for (int i = 1; i <= goldBarQuantity; i++) {
                for (int k = 1; k <= j; k++) {
                    l.dp[0][0] = 0;
                    // максимум из (предыдущий максимум или  вес текущего элемента   + оставшийся свободный  вес в рюкзаке)
                    // вес текущего элемента
                    int a = barWeights.get(i - 1);
                    System.out.println("вес текущего элемента " + a);
                    //то, что поместится в оставшемся  свободном месте   в рюкзаке
                    int b = k - barWeights.get(i - 1) <= 0 ? 0 : a + l.dp[i - 1][k - barWeights.get(i - 1)];
                    System.out.println("b = " + b);

                    System.out.println("запишу как  текущий максимум " + Integer.max(l.dp[i - 1][k], a + b));
                    l.dp[i][k] = Integer.max(l.dp[i - 1][k], b);
                }
            }
            System.out.println(l.dp[goldBarQuantity + 1][j + 1]);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
