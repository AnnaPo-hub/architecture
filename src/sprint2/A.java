package sprint2;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int lineQuantity = readInt(reader);
            final int columnQuantity = readInt(reader);

            int[][] result = new int[columnQuantity][lineQuantity];


            for (int i = 0; i < lineQuantity; i++) {
                final List<Integer> integers = readList(reader);
                for (int k = 0; k < columnQuantity; k++) {
                    result[k][i] = integers.get(k);
                }
            }

            for (int i = 0; i < columnQuantity; i++) {
                for (int j = 0; j < lineQuantity; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
