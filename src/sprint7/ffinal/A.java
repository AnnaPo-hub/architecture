package sprint7.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : массив
При сравнении нам нужны  только текущая и предыдущая строки,  поэтому используем 2 массива - dp_previous и dp_current.
В цикле после заполнения dp_current, меняем указатель на dp_previous, а для dp_current заводим новый массив.

Базовый случай: если одна  из строк пустая ( i = 0 или j = 0), то расстояние Левенштейна  равно длине непустой строки.

Во вложенном цикле используем индексы для доступа к символам строк из инпута.
Заполняем массив, сравнивая символы из строк. То есть, сначала вторая строка состоит из 1 элемента
и мы сравниваем  все элементы первой строки с единственным элементом второй строки,
затем вторая строка удлиняется на 1 элемент и сравниваем все элементы первой строки с вторым элементом второй строки и т д.
При сравнении выбираем минимальное значение из трех ячеек вокруг  текущей:  1) ячейка слева от текущей +1 2) сверху от текущей +1
3)  слева сверху по диагонали  от текущей + 1, если символы не равны или +0, если символы равны.
(1 в данном случае - это стоимость удаления/вставки/замены символа).

Получаем ответ в конце массива dp_previous.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
В решении реализован метод Вагнера-Фишера, который был ранее доказан.


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(N*M) - где N - количество символов в первой строке, а M - во второй.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N) - где N - количество символов во второй строке.

--ID успешной посылки--
https://contest.yandex.ru/contest/25597/run-report/116869336/
 */
public class A {
    static short[] dp_previous;
    static short[] dp_current;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<String> firstLine = readList(reader);
            int firstLineSize = firstLine.size();
            final List<String> secondLine = readList(reader);
            int secondLineSize = secondLine.size();

            dp_previous = new short[secondLineSize + 1];
            dp_current = new short[secondLineSize + 1];

            for (int i = 0; i <= firstLineSize; i++) {
                for (int j = 0; j <= secondLineSize; j++) {
                    if (i == 0 && j == 0) {
                        dp_current[j] = 0;
                    } else if (i > 0 && j == 0) {
                        dp_current[j] = (short) i;
                    } else if (j > 0 && i == 0) {
                        dp_current[j] = (short) j;
                    } else {
                        int m = firstLine.get(i - 1).equals(secondLine.get(j - 1)) ? 0 : 1;
                        dp_current[j] = (short) Integer.min(dp_previous[j - 1] + m, Integer.min(dp_current[j - 1] + 1, dp_previous[j] + 1));
                    }
                }
                dp_previous = dp_current;
                dp_current = new short[secondLineSize + 1];
            }
            System.out.println(dp_previous[secondLineSize]);
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .collect(Collectors.toList());
    }
}
