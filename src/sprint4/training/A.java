package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final LinkedHashMap<String, Integer> hobbiesMap = new LinkedHashMap<>();

            for (int i = 0; i < n; i++) {
                final String hobby = reader.readLine();
                if (!hobbiesMap.containsKey(hobby)) {
                    hobbiesMap.put(hobby, 1);
                } else {
                    Integer integer = hobbiesMap.get(hobby);
                    hobbiesMap.put(hobby, ++integer);
                }

            }

            hobbiesMap.keySet().forEach(System.out::println);
        }
    }
}

