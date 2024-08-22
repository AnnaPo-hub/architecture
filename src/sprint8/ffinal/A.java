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

            for (int i = 0; i < lineQuantity; i++) {
                Character[] chars = readList(reader);
                Queue<Character> queue = new LinkedList<>();

                for (int j = 0; j < chars.length; j++) {
                    queue.add(chars[j]);
                }
                String s = parseLine(queue);
                System.out.println(s);

            }
        }
    }

    private static String parseLine(Queue<Character> queue) {
        StringBuilder builder = new StringBuilder();
        int temp = 0;
        StringBuilder tempBuilder = new StringBuilder();

        for (int i = 0; i < queue.size(); i++) {
            Character currElement = queue.poll();
            if (Character.isDigit(currElement)) {
                temp = Integer.parseInt(String.valueOf(currElement));
            } else if (Character.isLetter(currElement)) {
                tempBuilder.append(currElement);
            } else if (currElement == ('[')) {
                String s = parseLine(queue);
                for (int j = 0; j < temp; j++) {
                    tempBuilder.append(s);
                }
                builder.append(tempBuilder);
                tempBuilder = new StringBuilder();

            } else if (currElement == ']') {
                return tempBuilder.toString();
            }
        }
        return builder.append(tempBuilder).toString();
    }

    private static Character[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);

    }

//    private static char[] readList(BufferedReader reader) throws IOException {
//        return reader.readLine().toCharArray();
//    }

//    private static List<String> readList(BufferedReader reader) throws IOException {
//        return Arrays.stream(reader.readLine().split(""))
//                .collect(Collectors.toList());
//    }
}
