package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class F {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<Integer> input = readList(reader);
            //финишная ступенька
            final Integer finish = input.get(0);
            //макс количество ступенек за прыжок
            final Integer k = input.get(1);


            final int n = Integer.parseInt(reader.readLine()) + 1;
            int base = 1000000007;


            int[] numbers = new int[n];
            numbers[0] = 0;
            numbers[1] = 0;
            numbers[2] = 1;
            for (int i = 3; i < finish; i++) {
                numbers[i] = (numbers[i - 1] + numbers[i - 2]) % base;

            }
            System.out.println(numbers[n - 1]);

        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
