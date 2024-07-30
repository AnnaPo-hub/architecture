package sprint7;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            boolean isTimeToBuy = true;
            final int daysQuantity = Integer.parseInt(reader.readLine());

            final List<Integer> prices = readList(reader);


            final double max = calculateMaximum(prices);
            final int indexOfMax = prices.indexOf((int) max);

            final double min = calculateMinimum(prices);
            final int indexOfMin = prices.indexOf((int) min);

            final double avg = calculateAverage(prices);

            int income = 0;
            int assetThatIHave = 0;
            for (Integer price : prices) {
                final int currIndex = prices.indexOf(price);
                //покупаю
                if ((isTimeToBuy && price == min) || (isTimeToBuy && price < avg && indexOfMax < currIndex)) {
                    assetThatIHave += price;
                    isTimeToBuy = false;
                    //продаю
                } else if (!isTimeToBuy && price == max || (!isTimeToBuy && price > avg && indexOfMax < currIndex)) {
                    income += price - assetThatIHave;
                    assetThatIHave = 0;
                    isTimeToBuy = true;
                }
            }
            System.out.println(income);
        }
    }

    private static double calculateAverage(List<Integer> prices) {
        return prices.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    private static double calculateMinimum(List<Integer> prices) {
        return prices.stream()
                .mapToInt(v -> v)
                .min()
                .orElseThrow(NoSuchElementException::new);
    }

    private static double calculateMaximum(List<Integer> prices) {
        return prices.stream()
                .mapToInt(v -> v)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
