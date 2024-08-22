package sprint8.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lineQuantity = Integer.parseInt(reader.readLine());

            for (int i = 0; i < lineQuantity; i++) {
                char[] chars = readList(reader);
                String s = parseLine(chars, 0);
                System.out.println(s);

            }
        }
    }

    private static String parseLine(char[] chars, int index) {
        StringBuilder builder = new StringBuilder();
        int temp = 0;
        StringBuilder tempBuilder = new StringBuilder();

        for (int i = index; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                temp = Integer.parseInt(String.valueOf(chars[i]));
            } else if (Character.isLetter(chars[i])) {
                tempBuilder.append(chars[i]);
            } else if (chars[i] == ('[')) {

                String s = parseLine(chars, i + 1);
                for (int j = 0; j < temp; j++) {
                    builder.append(s);
                }

            } else if (chars[i] == ']') {
                return tempBuilder.toString();
            }
        }
        return builder.append(tempBuilder).toString();
    }

//    private static Character[] readList(BufferedReader reader) throws IOException {
//        return reader.readLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);
//
//    }

    private static char[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().toCharArray();
    }
}
