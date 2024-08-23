package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int linesQuantity = Integer.parseInt(reader.readLine());

            String[] strings = new String[linesQuantity];
            for (int i = 0; i < linesQuantity; i++) {
                String string = reader.readLine();
                strings[i] = string;
            }
            System.out.println(getMaxPrefix(strings));
        }
    }


    private static int getMaxPrefix(String[] strings) {
        if (strings.length == 0) {
            return 0;
        } else if (strings.length == 1) {
            return strings[0].length();
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

            return longestPrefix.length();
        }
    }
}
