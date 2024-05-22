package sprint3.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AA {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int arraySize = Integer.parseInt(reader.readLine());
            final int elementToFind = Integer.parseInt(reader.readLine());
            final List<Integer> inputArray = readList(reader);

            //последний элемент левой части сломанного массива
            final int mistakePlace = getMistakePlace(inputArray.subList(0, inputArray.size() / 2), inputArray.subList(inputArray.size() / 2, inputArray.size()));

            System.out.println(" вывод для теста :  метод должен вернуть последний элемент левой половины сломанного массива  " +
                    "или -1 , если массив отсортирован  mistakePlace " + mistakePlace);

            //  если getMistakePlace вернул -1, то вызвать простой бинарный поиск
            if (mistakePlace == -1) {
                //   final int binary = Arrays.binarySearch(inputArray.toArray(), elementToFind);
                final int binary = getBinary(inputArray, elementToFind, 0, inputArray.size());
                System.out.println("нашелся индекс : " + binary);
            } else {
                final int mistakePlaceIndex = inputArray.indexOf(mistakePlace);
                if (elementToFind < inputArray.get(mistakePlaceIndex)) {
                    System.out.println("Финальный ответ: ");
                    System.out.println(getBinary(inputArray, elementToFind, mistakePlaceIndex + 1, inputArray.size() - 1));
                } else {
                    System.out.println("Финальный ответ: ");
                    System.out.println(getBinary(inputArray, elementToFind, 0, mistakePlaceIndex));
                }
            }
        }
    }


    //нужно найти границу  в данном массиве, где сортировка поломана
    private static int getMistakePlace(List<Integer> leftArray, List<Integer> rightArray) {

        if (leftArray.get(leftArray.size() - 1) > rightArray.get(0)) {  // базовый случай рекурсии
            return leftArray.get(leftArray.size() - 1);
        }
        if (leftArray.size() == 1 || rightArray.size() == 1) { // не найдено поломанной сортировки
            return -1;
        }
        return getMistakePlace(leftArray.subList(0, leftArray.size() / 2), rightArray.subList(rightArray.size() / 2, rightArray.size()));
    }

    private static int getBinary(List<Integer> inputArray, int elementToFind, int from, int to) {
        if (from <= to) {
            int middle = (from + to) / 2;

            if (elementToFind > inputArray.get(middle)) {
                return getBinary(inputArray, elementToFind, middle + 1, to);
            } else if (elementToFind < inputArray.get(middle)) {
                return getBinary(inputArray, elementToFind, from, middle - 1);
            }
            return middle;
        }
        return -1;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
