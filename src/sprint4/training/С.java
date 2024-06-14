package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class С {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            System.out.println(compareStrangeWords(firstLine, secondLine) ? "YES" : "NO");
        }
    }

    private static boolean compareStrangeWords(String firstLine, String secondLine) {

        if (firstLine.length() != secondLine.length()) {
            return false;
        } else {
            final ArrayList<Character> existingValues = new ArrayList<>();
            Map<Character, Character> wordMap = new TreeMap<>();

            for (int i = 0; i < firstLine.length(); i++) {

                if (!wordMap.containsKey(firstLine.charAt(i))) {
                    if (existingValues.contains(secondLine.charAt(i))) {
                        return false;
                    } else {
                        wordMap.put(firstLine.charAt(i), secondLine.charAt(i));
                        existingValues.add(secondLine.charAt(i));
                    }
                } else {

                    final Character existingValue = wordMap.get(firstLine.charAt(i));
                    if (!existingValue.equals(secondLine.charAt(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}