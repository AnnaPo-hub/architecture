package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class B {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            int[][] matrix = new int[vectorQuantity + 1][vectorQuantity + 1];

            for (int i = 1; i < vectorQuantity + 1; i++) {
                for (int j = 1; j < vectorQuantity + 1; j++) {
                    matrix[i][j] = 0;
                }
            }

            for (int i = 1; i <= edgesQuantity; i++) {
                final List<Integer> currentLine = readList(reader);
                final Integer firstVector = currentLine.get(0);
                final Integer secondVector = currentLine.get(1);
                matrix[firstVector][secondVector] = 1;
            }

            //печатаем результат
            for (int i = 1; i < vectorQuantity + 1; i++) {
                for (int j = 1; j < vectorQuantity + 1; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
