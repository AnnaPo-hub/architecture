package sprint3.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L {
    //переделать чтобы возвращал что-то, а печаталось уже в main
    public static void findDays(int counter, int start, int bikePrice, List<Integer> moneyPerDays, int daysQuantity) {
        if (counter > 2) {
            return;
        }
        for (int i = start; i < daysQuantity; i++) {
            if (moneyPerDays.get(i) >= bikePrice) {
                System.out.print((i + 1) + " ");
                findDays(++counter, i + 1, bikePrice * 2, moneyPerDays, daysQuantity);
                return;
            }
        }
        System.out.print("-1" + " ");
        if (counter == 1) {
            System.out.print("-1");
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int daysQuantity = Integer.parseInt(reader.readLine());
            final List<Integer> moneyPerDays = readList(reader);
            final int bikePrice = Integer.parseInt(reader.readLine());

            findDays(1, 0, bikePrice, moneyPerDays, daysQuantity);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
