package sprint3.training;
    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : массив.
Рекурсивно проходим по массиву и находим место где сортировка  элементов сдвинута.
Если сортировка не сломана, то используем бинарный поиск, передавая на вход весь массив.
Если сортировка сломана, то передаем в бинарный поиск нужную половину.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для подсчета значения выражения, записанного в постфиксной нотации,
нужно считывать выражение слева направо и, при получении на вход знака операции,
произвести  операции с предыдущими  двумя полученными операндами.
Именно это мы делаем в решении, используя стек: разбираем выражение слева направо,
полученные числа кладем в стек, при получении знака операции достаем из стека предыдущие 2 числа,
выполняем арифметическую операцию и результат кладем в стек

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность O(n),  где  n- это длина входной строки

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Если n - длина входной строки, то требуется   O(n)  памяти, так как она нужна только для хранения самих элементов стека.

--ID успешной посылки--
https://contest.yandex.ru/contest/23815/run-report/114501279/
     */

public class Solution_A {

    public static int brokenSearch(int[] arr, int k) {
        final int shiftPlace = getShiftPlace(arr, 0, arr.length - 1);

        //  если getMistakePlace вернул -1, то сортировка не сломана
        if (shiftPlace == -1) {
            return getBinary(arr, k, 0, arr.length);
        } else {
            if (k < arr[shiftPlace] && k < arr[0]) { // ищем справа
                return getBinary(arr, k, shiftPlace + 1, arr.length);
            } else if (k > arr[shiftPlace] && k > arr[0] ||//ищем слева
                    k < arr[shiftPlace] && k >= arr[0]
            ) {
                return getBinary(arr, k, 0, shiftPlace + 1);
            }
        }
        return -1;
    }

    /*
     //возвращает индекс последнего элемента  левой части  поломанного массива
    //или -1, если сортировка не смещена
     */
    private static int getShiftPlace(int[] inputArray, int from, int to) {
        int resultIndex = -1;
        if (from == to) return resultIndex;
        if (inputArray[from] < inputArray[to]) return resultIndex;

        if (from < to) {
            int middleInd = (from + to) / 2;

            if (inputArray[middleInd] > inputArray[middleInd + 1]) {  // базовый случай рекурсии
                return middleInd;
            }
            resultIndex = getShiftPlace(inputArray, from, middleInd);
            if (resultIndex == -1) {
                resultIndex = getShiftPlace(inputArray, middleInd + 1, to);
            }
        }
        return resultIndex;
    }


    private static int getBinary(int[] inputArray, int elementToFind, int from, int to) {
        if (from == to) {
            return from == elementToFind ? from : -1;
        } else if (from < to) {
            int middle = (from + to) / 2;

            if (elementToFind > inputArray[middle]) {
                return getBinary(inputArray, elementToFind, middle + 1, to); // ищем  справа
            } else if (elementToFind < inputArray[middle]) {
                return getBinary(inputArray, elementToFind, from, middle); //ищем слева
            }
            return middle;
        }
        return -1;
    }
}
