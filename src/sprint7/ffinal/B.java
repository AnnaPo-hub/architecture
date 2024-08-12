package sprint7.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B {
    //храним набор половины суммы
    static short[][] dp;

    //Одним из параметров динамики будет половина суммы массива, которую будем набирать
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int gameQuantity = Integer.parseInt(reader.readLine());

//числа из массива с номерами от 0 до i
            int[] scoreList = readList(reader);
            int sum = Arrays.stream(scoreList).sum();
            if (sum % 2 != 0) {
                System.out.println("False");
            } else {
                int half = Arrays.stream(scoreList).sum() / 2;


                B b = new B();
                dp = new short[gameQuantity + 1][half + 1];
                for (int i = 0; i < gameQuantity; i++) {
                    for (int k = 0; k <= half; k++) {

                        //предыдущий максимум
                        int prevMax = i - 1 < 0 ? 0 : dp[i - 1][k];

                        // текущий элемент массива
                        int currElement = scoreList[i];

                        int x = k - currElement;
                        if (x < 0) {

                            dp[i][k] = (short) prevMax;
                        } else if (x == 0) {
                            dp[i][k] = (short) currElement;
                        } else {
                            //то, что поместится в оставшемся  свободном месте   в рюкзаке
                            int y = k - currElement < 0 || i - 1 <= 0 ? 0 : b.dp[i - 1][k - currElement];
                            dp[i][k] = (short) Integer.max(prevMax, currElement + y);
                        }
                    }
                }
                System.out.println(dp[gameQuantity - 1][half] == half ? "True" : "False");
            }
        }
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }
}
