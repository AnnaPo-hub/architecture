package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class G {
    static int counter;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfOccurrence = Integer.parseInt(reader.readLine());

            final List<Integer> listOfTemperatures = readList(reader);

            int patternLength = Integer.parseInt(reader.readLine());

            final List<Integer> pattern = readList(reader);


            List<Integer> result = findAll(listOfTemperatures, pattern);
            for (Integer currResult : result) {
                System.out.print(currResult + " ");
            }
        }
    }

    public static List<Integer> findAll(List<Integer> listOfTemperatures, List<Integer> pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int start = 0; // Начнём поиск с начала строки.
        // Найдём первое вхождение, если оно есть.
        while (true) {
            int pos = find(listOfTemperatures, start, pattern);
            if (pos == -1) {
                break;
            }
            occurrences.add(pos + 1); // Сохраним вхождение в список.
            start = pos + 1;
            // И продолжим поиск, начиная с позиции,
            // следующей за только что найденной.
        }
        return occurrences;
    }

    public static int find(List<Integer> listOfTemperatures, int start, List<Integer> pattern) {
        if (listOfTemperatures.size() < pattern.size()) {
            return -1;  // Длинный шаблон не может содержаться в короткой строке.
        }
        for (int pos = start; pos <= listOfTemperatures.size() - pattern.size(); pos++) {
            // Проверяем, не совпадёт ли шаблон, сдвинутый на позицию pos,
            //   с соответствующим участком строки.
            boolean match = true;
            for (int offset = 0; offset < pattern.size() - 1; offset++) {
                int c = Math.abs(pattern.get(offset) - pattern.get(offset + 1));
                //разница   в числах в паттерне мб не одинаковой
                if (Math.abs(listOfTemperatures.get(pos + offset) - listOfTemperatures.get(pos + offset + 1)) != c) {
                    // Одного несовпадения достаточно, чтобы не проверять
                    //   дальше текущее расположение шаблона.
                    match = false;
                    break;
                }
            }
            // Как только нашлось совпадение шаблона, возвращаем его.
            // Это первое вхождение шаблона в строку.
            if (match == true) {
                return pos;
            }
            // Если совпадение не нашлось, цикл перейдёт к проверке следующей позиции.
        }
        // Числом -1 часто маркируют, что подстрока не была найдена,
        //   поскольку в строке нет позиции -1.
        // В качестве альтернативы можно возвращать null.
        return -1;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
