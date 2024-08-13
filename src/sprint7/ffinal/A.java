package sprint7.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : двумерный массив, число строк равно длине первой строки, число столбцов длине второй строки.
В  матрице dp  будем хранить расстояние по Левенштейну.
Базовый случай: если одна  из строк пустая ( i = 0 или j = 0), то расстояние Левенштейна  равно длине непустой строки.

Используем индексы  строк и столбцов для доступа к символам строк из инпута.
Построчно заполняем матрицу, сравнивая символы из строк. То есть, сначала вторая строка состоит из 1 элемента
и мы сравниваем  все элементы первой строки с единственным элементом второй строки,
затем вторая строка удлиняется на 1 элемент и сравниваем все элементы первой строки с вторым элементом второй строки и т д.
При сравнении выбираем минимальное значение из трех ячеек вокруг  текущей:  1) ячейка слева от текущей +1 2) сверху от текущей +1
3)  слева сверху по диагонали  от текущей + 1, если символы не равны или +0, если символы равны.
(1 в данном случае - это стоимость удаления/вставки/замены символа).

Получаем ответ в нижней правой ячейке.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
В решении реализован метод Вагнера-Фишера, который был ранее доказан.


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(N*M) - где N - количество символов в первой строке, а M - во второй.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N*M) - где N - количество символов в первой строке, а M - во второй.

--ID успешной посылки--
https://contest.yandex.ru/contest/25597/run-report/116815511/
 */
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
