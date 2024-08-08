package sprint3.training;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final Integer quantity = Integer.parseInt(reader.readLine());
            if (quantity > 0) {
                final List<Integer> clother = readList(reader);
                partition(clother, 0, clother.size() - 1);
                for (int i = 0; i < quantity; i++) {
                    writer.write(clother.get(i) + " ");
                }
            }
        }
    }

    public static void swap(List<Integer> array, int leftIndex, int rightIndex) {
        Integer temp = array.get(leftIndex);
        array.set(leftIndex, array.get(rightIndex));
        array.set(rightIndex, temp);
    }

    public static int partition(List<Integer> array, int leftIndex, int rightIndex) {
//опорный элемент
        int pivot = 1;

        int equal = leftIndex;
        while (equal <= rightIndex) {
            if (array.get(equal) < pivot)
                swap(array, equal++, leftIndex++);
            else if (array.get(equal) > pivot)
                swap(array, equal, rightIndex--);
            else
                ++equal;

        }
        return rightIndex;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}