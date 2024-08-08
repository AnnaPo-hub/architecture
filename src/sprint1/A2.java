package sprint1;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A2 {

    private static List<Integer> factorize(int quant, List<Integer> source) {
        Integer[] result = new Integer[quant];

        Integer steps = null;

        for (int i = 0; i < quant; ++i) {
            if (source.get(i) == 0) {
                steps = 0;
            }
            if (steps != null)
                result[i] = steps++;
        }
        steps = null;
        for (int i = quant - 1; i >= 0; --i) {
            if (result[i] != null && result[i] == 0) {
                steps = 0;
            }
            if (steps != null) {
                result[i] = Math.min(result[i] == null ? Integer.MAX_VALUE : result[i], steps++);
            }
        }
        return Arrays.asList(result);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> factorize = factorize(readInt(reader), readList(reader));
            for (int i = 0; i < factorize.size(); i++) {
                writer.write(factorize.get(i) + "");
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