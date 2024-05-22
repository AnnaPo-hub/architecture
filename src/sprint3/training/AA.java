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

            final int mistakePlace = getMistakePlace(inputArray.subList(0, inputArray.size() / 2), inputArray.subList(inputArray.size() / 2, inputArray.size()));

            System.out.println(" вывод для теста :  метод должен вернуть первый элемент правой половины сломанного массива  " +
                    "или -1 , если массив отсортирован" + mistakePlace);

            //  если getMistakePlace вернул -1, то вызвать простой бинарный поиск
            if (mistakePlace == -1) {
                final int binary = getBinary(inputArray, elementToFind);
                System.out.println("нашелся индекс : " + binary);
            }
        }
    }

    private static int getIndex(List<Integer> inputArray, int elementToFind, int arraySize) {

        return 0;
    }

    //нужно найти границу  в данном массиве, где сортировка поломана
    private static int getMistakePlace(List<Integer> leftArray, List<Integer> rightArray) {

        if (leftArray.get(leftArray.size() - 1) > rightArray.get(0)) {  // базовый случай рекурсии
            return rightArray.get(0);
        }
        if (leftArray.size() == 1 || rightArray.size() == 1) { // не найдено поломанной сортировки
            return -1;
        }
        return getMistakePlace(leftArray.subList(0, leftArray.size() / 2), rightArray.subList(rightArray.size() / 2, rightArray.size()));
    }

    private static int getBinary(List<Integer> inputArray, int elementToFind) {
        final Integer integer = inputArray.get(inputArray.size() / 2);
        if (elementToFind == integer) {
            return inputArray.indexOf(integer);
        } else if (elementToFind > integer) {
            List<Integer> rightSide = inputArray.subList(inputArray.size() / 2, inputArray.size());
            getBinary(rightSide, elementToFind);
        } else if (elementToFind < integer) {
            List<Integer> leftSide = inputArray.subList(0, inputArray.size() / 2);
            getBinary(leftSide, elementToFind);
        }
        return elementToFind;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
