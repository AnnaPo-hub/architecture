package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String string1 = reader.readLine();
            String string2 = reader.readLine();

            System.out.println(checkString(string1, string2) ? "OK" : "FAIL");
        }
    }

    private static boolean isLengthTooLong(String s1, String s2) {
        return Math.abs(s1.length() - s2.length()) >= 2;
    }

    private static boolean isLengthTheSame(String s1, String s2) {
        return s1.length() == s2.length();
    }

    private static boolean checkString(String s1, String s2) {
        boolean counter = false;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        //отличаются на 2 или более символов
        if (isLengthTooLong(s1, s2)) {
            return false;
        } else if (isLengthTheSame(s1, s2)) {
            for (int i = 0; i < s1.length(); i++) {
                if (chars1[i] != chars2[i] && counter) {
                    counter = false;
                    return false;
                } else if (chars1[i] != chars2[i]) {
                    counter = true;
                }
            }
        } else {
            // длина отличается на 1
            //взять более короткий
            int lengthMin = Integer.min(s1.length(), s2.length());
            String shortestString;
            String longestString;
            if (s2.length() == lengthMin) {
                shortestString = s2;
                longestString = s1;
            } else {
                shortestString = s1;
                longestString = s2;
            }
            for (int i = 0; i < shortestString.length(); i++) {

                if (chars1[i] != chars2[i]) {
                    if (!isRestTheSame(s1, s2, i))
                        return false;
                }
            }
        }
        return true;
    }

    private static boolean isRestTheSame(String s1, String s2, int i) {
        String substring = s1.substring(i, s1.length());
        String substring2 = s2.substring(i + 1, s2.length());
        String substring3 = s1.substring(i + 1, s1.length());
        String substring4 = s2.substring(i, s2.length());
        return substring2.equals(substring) || substring3.equals(substring4);
    }
}


