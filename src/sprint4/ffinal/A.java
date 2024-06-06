package sprint4.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            final HashMap<String, List<Integer>> vocabulary = new HashMap<>();

            final int documentQuantity = Integer.parseInt(reader.readLine());

            for (int i = 0; i <= documentQuantity; i++) {
                final List<String> document = readListOfString(reader);
                for (String word : document) {

                  //  final int hash = word.hashCode();
                    if (vocabulary.containsKey(word)) {
                        vocabulary.get(word).add(i);
                    } else {
                        ArrayList<Integer> objects = new ArrayList<>();
                        objects.add(i);
                        vocabulary.put(word, objects);
                    }
                }
            }
            System.out.println(vocabulary.entrySet());
        }
    }

    private static List<String> readListOfString(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }
}
