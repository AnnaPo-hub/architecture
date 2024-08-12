package sprint7.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    //максимальная масса, которую можно забрать с собой
    int[][] dp;

    public A(int goldBarQuantity, int k) {
        this.dp = new int[goldBarQuantity + 1][k + 1];
        for (int i = 0; i <= goldBarQuantity; i++) {
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<String> firstLine = readList(reader);
            int firstLineSize = firstLine.size();
            final List<String> secondLine = readList(reader);
            int secondLineSize = secondLine.size();

            short counter = 0;
            for (int i = 0; i < firstLineSize; i++) {
                if ((i + 1 < firstLineSize) &&
                        (firstLine.get(i + 1).equals(secondLine.get(i))) &&
                        (firstLineSize > secondLineSize)) {
                    firstLine.remove(i);
                    firstLineSize -= 1;
                    ++counter;
                } else if ((i + 1 < secondLineSize) &&
                        (firstLine.get(i).equals(secondLine.get(i + 1))) &&
                        (firstLineSize < secondLineSize)) {
                    secondLine.remove(i);
                    secondLineSize -= 1;
                    ++counter;
                } else if (!firstLine.get(i).equals(secondLine.get(i))) {
                    firstLine.set(i, secondLine.get(i));
                    ++counter;
                }
            }

            System.out.println(counter);

        }

    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .collect(Collectors.toList());
    }
}
