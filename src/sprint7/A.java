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

            int income = 0;
            int assetThatIHave = 0;
            for (int i = 0; i < daysQuantity; i++) {
                int price = prices.get(i);
                //покупаю
                if (i + 1 < prices.size() && price < prices.get(i + 1) && isTimeToBuy) {
                    assetThatIHave += price;
                    isTimeToBuy = false;
                    // System.out.println("купила " + price);
                    //продаю
                    //могу продавать И
                } else if (!isTimeToBuy && (
                        //текущий элемент последний  и цена больше чем  цена акции у меня на руках ИЛИ
                        (i + 1 == prices.size() && price > assetThatIHave) ||
                                //текущий элемент  не последний   и  цена больше,  чем цена  на следующий день
                                (i + 1 < prices.size() && price > prices.get(i + 1)))) {
                    income += price - assetThatIHave;
                    assetThatIHave = 0;
                    isTimeToBuy = true;
                    // System.out.println("продала " + price);
                }
            }
            System.out.println(income);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
