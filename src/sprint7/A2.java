package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class A2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            boolean isTimeToBuy = true;
            final int daysQuantity = Integer.parseInt(reader.readLine());

            final List<Integer> prices = readList(reader);

            final double max = calculateMaximum(prices);
            final int indexOfMax = prices.indexOf((int) max);
            final double min = calculateMinimum(prices);
            final double avg = calculateAverage(prices);

            int income = 0;
            int assetThatIHave = 0;
            for (Integer price : prices) {
                final int currIndex = prices.indexOf(price);
                //покупаю
                if ((isTimeToBuy && currIndex == 0 && price != max) || (isTimeToBuy && price == min) || (isTimeToBuy && (price < avg || prices.get(currIndex + 1) > price))) {
                    assetThatIHave += price;
                    isTimeToBuy = false;
                    //  System.out.println(" покупаю " + price);
                    //продаю
                } else if (!isTimeToBuy && price > assetThatIHave && currIndex != daysQuantity && (currIndex < daysQuantity - 1 && prices.get(currIndex + 1) <= price) || !isTimeToBuy && currIndex + 1 == daysQuantity || !isTimeToBuy && price == max || (!isTimeToBuy && price > avg && indexOfMax < currIndex)) {
                    income += price - assetThatIHave;
                    assetThatIHave = 0;
                    isTimeToBuy = true;
                    //  System.out.println(" продаю " + price);
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
