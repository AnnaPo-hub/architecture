package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class E {

    public static void main(String[] args) throws IOException {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String ritaLine = reader.readLine();
            final int giftLinesNumber = Integer.parseInt(reader.readLine());

            final Map<Integer, String> map = new TreeMap<>();
            for (int i = 0; i < giftLinesNumber; i++) {
                final List<String> currLine = readList(reader);
                int index = Integer.parseInt(currLine.get(1));
                String stringToInsert = currLine.get(0);
                map.put(index, stringToInsert);
            }
            StringBuilder builder = new StringBuilder();
            for (var entry : map.entrySet()) {
                int key = entry.getKey() - counter;
                builder.append(ritaLine, 0, key).append(entry.getValue());
                ritaLine = ritaLine.substring(key);
                counter += key;
            }
            builder.append(ritaLine);
            System.out.println(builder.toString());
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }
}

