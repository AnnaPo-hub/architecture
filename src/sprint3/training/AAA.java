package sprint3.training;

import java.io.*;

public class AAA {

    public static int brokenSearch(int[] arr, int k) {
        // Your code
        // “ヽ(´▽｀)ノ”
        final int mistakePlace = getMistakePlace(arr, 0, arr.length - 1);

        //  если getMistakePlace вернул -1, то вызвать простой бинарный поиск
        if (mistakePlace == -1) {
            //   final int binary = Arrays.binarySearch(inputArray.toArray(), elementToFind);
            return getBinary(arr, k, 0, arr.length);
        } else {
            if (k < arr[mistakePlace]) {
                return getBinary(arr, k, mistakePlace + 1, arr.length - 1);
            } else {
                return getBinary(arr, k, 0, mistakePlace);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        StreamTokenizer reader = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        final int arraySize = nextInt(reader);
        final int elementToFind = nextInt(reader);

        int[] inputArray = readList(reader, arraySize);

        //индекс последнего элемента левой части сломанного массива
        final int mistakePlace = getMistakePlace(inputArray, 0, inputArray.length - 1);

        System.out.println(" вывод для теста :  метод должен вернуть индекс последнего элемента левой половины сломанного массива  " +
                "или -1 , если массив отсортирован  mistakePlace " + mistakePlace);

        //  если getMistakePlace вернул -1, то вызвать простой бинарный поиск
        if (mistakePlace == -1) {
            //   final int binary = Arrays.binarySearch(inputArray.toArray(), elementToFind);
            final int binary = getBinary(inputArray, elementToFind, 0, inputArray.length);
            System.out.println("нашелся индекс : " + binary);
        } else {
            if (elementToFind < inputArray[mistakePlace]) {
                System.out.println("Финальный ответ: ");
                System.out.println(getBinary(inputArray, elementToFind, mistakePlace + 1, inputArray.length - 1));
            } else {
                System.out.println("Финальный ответ: ");
                System.out.println(getBinary(inputArray, elementToFind, 0, mistakePlace));
            }
        }
    }

    private static int[] readList(StreamTokenizer in, int n) throws IOException {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = nextInt(in);
        }
        return data;
    }

    private static int nextInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }


    //найти границу  в данном массиве, где сортировка поломана
    //возвращает индекс последнего элемента в левой части  поломанного массива
    private static int getMistakePlace(int[] inputArray, int from, int to) {
        int resultIndex = -1;
        if (from <= to) {
            int middle = (from + to) / 2;  //индекс середины входящего массива

            if (inputArray[middle] > inputArray[middle + 1]) {  // базовый случай рекурсии
                return middle;
            }
//            } else if (inputArray.length == 2) { // не найдено поломанной сортировки
//                return -1;
//            }
            resultIndex = getMistakePlace(inputArray, 0, middle);
            if (resultIndex == -1) {
                resultIndex = getMistakePlace(inputArray, middle, inputArray.length - 1);
            }
        }
        return resultIndex;
    }


    private static int getBinary(int[] inputArray, int elementToFind, int from, int to) {
        if (from <= to) {
            int middle = (from + to) / 2;

            if (elementToFind > inputArray[middle]) {
                return getBinary(inputArray, elementToFind, middle + 1, to);
            } else if (elementToFind < inputArray[middle]) {
                return getBinary(inputArray, elementToFind, from, middle - 1);
            }
            return middle;
        }
        return -1;
    }
}
