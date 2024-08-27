package sprint8.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : TreeSet - для хранения алфавита
Складываем все строчки из инпута в TreeSet в обратном порядке,  получаем наш "алфавит".

Далее в цикле проходим по каждому слову из алфавита, находим индекс каждого его вхождения в текст.
Затем по списку полученных индексов заменяем в тексте слово из алфавита на "".

Если после прохождения всего алфавита осталась пустая строка, то значит из данного алфавита можно сложить
требуемую строку.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

--ID успешной посылки--
 */
public class B {
    private static Set<String> alphabet;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            int alphabetQuantity = Integer.parseInt(reader.readLine());

            alphabet = new TreeSet<>(Collections.reverseOrder());

            for (int i = 0; i < alphabetQuantity; i++) {
                String currWord = reader.readLine();
                alphabet.add(currWord);
            }

            String replace = input;
            for (String currWord : alphabet) {
                List<Integer> search = search(currWord, replace);
                String candidate = replace(replace, "", search, 0, currWord);

            }
            System.out.println(replace.length() == 0 ? "YES" : "NO");
        }
    }

    public static List<Integer> search(String p, String text) {
        // Функция возвращает все позиции вхождения шаблона в тексте.
        List<Integer> result = new ArrayList<>();
        String s = p + "#" + text;
        int[] π = new int[p.length()];  // Массив длины |p|.
        Arrays.fill(π, 0);
        int π_prev = 0;
        for (int i = 1; i < s.length(); i++) {
            int k = π_prev;
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = π[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            // Запоминаем только первые |p| значений π-функции.
            if (i < p.length()) {
                π[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            π_prev = k;
            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == p.length()) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                //  - чтобы «переместиться» на начало найденного шаблона,
                //  - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * p.length());
            }
        }
        return result;
    }

    private static String replace(String string, String patternToInsert, List<Integer> search, int counter, String pattern) {
        StringBuilder builder = new StringBuilder(string);
        for (var entry : search) {
            builder.replace(entry + counter, entry + counter + pattern.length(), patternToInsert);
            counter += patternToInsert.length() - pattern.length();
        }
        return builder.toString();
    }
}
