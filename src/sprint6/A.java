package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            int[] edgesQuantityFromVector = new int[vectorQuantity + 1];

            for (int i = 1; i < vectorQuantity + 1; i++) {
                edgesQuantityFromVector[i] = 0;
            }
            ArrayList<Integer>[] vectors = new ArrayList[vectorQuantity + 1];
            for (int i = 1; i < vectors.length; i++) {
                vectors[i] = new ArrayList<>();
            }

            for (int i = 0; i < edgesQuantity; i++) {
                final List<Integer> currentLine = readList(reader);
                final Integer firstVector = currentLine.get(0);
                final Integer secondVector = currentLine.get(1);

                edgesQuantityFromVector[firstVector] += 1;
                vectors[firstVector].add(secondVector);
            }


            //печатаем результат
            for (int i = 1; i < edgesQuantityFromVector.length; i++) {
                Collections.sort(vectors[i]);
                String collect = "";
                if (!vectors[i].isEmpty()) {
                    collect = vectors[i].stream()
                            .map(Objects::toString)
                            .collect(Collectors.joining(" "));
                }
                System.out.println(edgesQuantityFromVector[i] + " " + (!vectors[i].isEmpty() ? collect : ""));
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
