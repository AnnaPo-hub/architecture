package sprint8.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lineQuantity = Integer.parseInt(reader.readLine());

            String[] strings = new String[lineQuantity];
            for (int i = 0; i < lineQuantity; i++) {
                Character[] chars = readList(reader);
                Queue<Character> queue = new LinkedList<>();

                for (int j = 0; j < chars.length; j++) {
                    queue.add(chars[j]);
                }
                String s = parseLine(queue);
                System.out.println(s);
                strings[i] = s;
            }
            System.out.println(getMaxPrefix(strings));
        }
    }

    private static String parseLine(Queue<Character> queue) {
        int q = queue.size();
        StringBuilder builder = new StringBuilder();
        int temp = 0;
        StringBuilder tempBuilder = new StringBuilder();

        for (int i = 0; i < q; i++) {
            if (!queue.isEmpty()) {
                Character currElement = queue.poll();
                if (Character.isDigit(currElement)) {
                    System.out.println("Попал на цифру " + currElement);
                    builder.append(tempBuilder);
                    System.out.println("builder сейчас : " + builder.toString());
                    tempBuilder = new StringBuilder();
                    temp = Integer.parseInt(String.valueOf(currElement));
                } else if (Character.isLetter(currElement)) {
                    System.out.println("Попал на букву " + currElement);
                    tempBuilder.append(currElement);
                } else if (currElement == ('[')) {
                    System.out.println("Попал на открывающую скобку  ");
                    String s = parseLine(queue);
                    for (int j = 0; j < temp; j++) {
                        tempBuilder.append(s);
                    }
                } else if (currElement == ']') {
                    System.out.println("Попал на закрывающую скобку  ");
                    return tempBuilder.toString();
                }
            }
        }
        return builder.append(tempBuilder).toString();
    }

    private static Character[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);

    }

    private static String getMaxPrefix(String[] strings) {
        if (strings.length == 0) {
            return "";
        } else if (strings.length == 1) {
            return strings[0];
        } else {

            //берём первую строку массива и принимаем её за самый длинный общий префикс
            String longestPrefix = strings[0];

            //перебираем элементы массива strings
            for (int i = 1; i < strings.length; i++) {
                String currString = strings[i];
                // указатель показывает  на индекс последнего символа, который совпал с нашим общим префиксом
                int ind1 = 0;
                //указатель перемещается по строкам посимвольно
                int ind2 = 0;

                while (ind2 < longestPrefix.length() && ind2 < currString.length()) {
                    if (longestPrefix.charAt(ind2) == currString.charAt(ind2)) {
                        ++ind1;
                    } else {
                        break;
                    }
                    ++ind2;
                }
                longestPrefix = longestPrefix.substring(0, ind1);
            }

            return longestPrefix;
        }
    }

//    private static char[] readList(BufferedReader reader) throws IOException {
//        return reader.readLine().toCharArray();
//    }

//    private static List<String> readList(BufferedReader reader) throws IOException {
//        return Arrays.stream(reader.readLine().split(""))
//                .collect(Collectors.toList());
//    }
}
