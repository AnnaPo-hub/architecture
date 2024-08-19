package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            int i = reviseString(line1).compareTo(reviseString(line2));
            int result = Integer.compare(i, 0);
            System.out.println(result);
        }
    }

    private static String reviseString(String input) {
        StringBuilder builder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (isEven(aChar)) {
                builder.append(aChar);
            }
        }
        //   System.out.println("верну " + builder);
        return builder.toString();
    }

    private static boolean isEven(char x) {
        return (int) x % 2 == 0;
    }
}
