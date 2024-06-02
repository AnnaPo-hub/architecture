package sprint3.finall;
    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : массив.
Рекурсивно проходим по массиву и находим место где сортировка  элементов сдвинута (где начало и конец массива оказались рядом).
Если сортировка не сломана, то используем бинарный поиск, передавая на вход весь массив.
Если сортировка сломана, то передаем в бинарный поиск нужную половину массива.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
В алгоритме используется бинарный поиск,один из стандартных алгоритмов, результативность  которого считаем доказанной априори.

Ситуация 1. Если массив отсортирован, то есть, нулевой элемент меньше последнего,
 то при использовании бинарного поиска мы точно найдем/не найдем искомый элемент.

Ситуация 2. Если сортировка имеет смещение, то мы можем найти в массиве место,
где конец первого подмассива больше начала второго подмассива. То есть, мы можем быть уверены, что в этом месте находится смещение сортировки в массиве.
После разделения массива на 2 части мы может проверить,
что массив корректно разделен на 2 отсортированных подмассива. После выбора нужного подмассива доказательством того,
что мы выбрали нужную часть массива служит то, что искомый элемент находится внутри граничных значений подмассива.
Далее, после выбора нужной  части массива приводим задачу к ситуации 1.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Поиск места поломки O(log n)
Поиск элемента в массиве за O(log n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n)  памяти, где n - это  количество элементов в массиве, передаваемое в качестве параметра.

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
