package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class E {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String ritaLine = reader.readLine();
            final int giftLinesNumber = Integer.parseInt(reader.readLine());

            final Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < giftLinesNumber; i++) {
                final List<String> currLine = readList(reader);
                int index = Integer.parseInt(currLine.get(1));
                String stringToInsert = currLine.get(0);
                map.put(index, stringToInsert);
            }

            for (var entry : map.entrySet()) {
                ritaLine = insert(ritaLine, entry.getKey(), entry.getValue());
            }
            System.out.println(ritaLine);
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }


    // Вставить строку substring в строку string после позиции index.
    public static String insert(String string, int index, String substring) {
        int length = string.length();
        int shift = substring.length();
        if (index > length) {
            // index == length - край строки
            throw new IllegalArgumentException("Нет такой позиции");
        }
        string = String.format("%-" + (length + shift) + "s", string);
        if (length > 0) {
            // Если length == 0, делать сдвиг нет смысла.
            // Кроме того, не следует в вычислениях писать (length - 1),
            // не проверив, что индекс не ноль.
            // В некоторых языках длина представляется беззнаковым целым числом,
            // в таком случае (length - 1) будет равен не -1, а числу MAX_INT,
            // и цикл станет некорректным. Мы этого избегаем.
            for (int i = length - 1; i >= index; i--) {
                final String substring1 = string.substring(0, i + shift);
                final char c = string.charAt(i);
                final String substring2 = string.substring(i + shift + 1);

                string = substring1 + c + substring2;
            }
        }
        for (int i = 0; i < shift; i++) {
            final String substring1 = string.substring(0, index + i);
            final String substring2 = string.substring(index + i + 1);
            final char c = substring.charAt(i);
            string = substring1 + c + substring2;
        }
        return string;
    }
}

