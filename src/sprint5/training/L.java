package sprint5.training;

public class L {
    /*
    Функция принимает в качестве аргументов массив,
     в котором хранятся элементы кучи, и индекс элемента,
     от которого надо сделать просеивание вниз. Функция должна вернуть индекс,
     на котором элемент оказался после просеивания.
     Также необходимо изменить порядок элементов в переданном в функцию массиве.
     */
    public static int siftDown(int[] heap, int index) {
        // Your code
        //потомки вершины, от которой нужно сделать просеивание
        int left = 2 * index;
        int right = 2 * index + 1;

        // Нет дочерних узлов, элемент не с кем просеивать
        if (left >= heap.length) {
            return index;
        }
        // есть левый элемент, а правого нет, но левый меньше или равен текущему
        if ((left < heap.length) && heap[left] <= heap[index] && right >= heap.length ) {
            return index;
        }
        //если есть левый и правый элемент и оба они менше ил равны текущему
        if ((left < heap.length) && right < heap.length && heap[left] <= heap[index] && heap[right] <= heap[index]) {
            return index;
        }

        // в данной точке мы точно знаем, что есть как миниму левый узел со значение больше корня
        int indexLargest = left;
        // проверяем, что есть оба дочерних узла и присваиваеи меньшее значение
        //переменной  indexLargest
        if (right < heap.length && heap[right] > heap[left]) {
            indexLargest = right;
        }

        //меняем местами элементы, если больший по значению потомок больше чем корень
        if (heap[indexLargest] > heap[index]) {
            int temp = heap[index];
            heap[index] = heap[indexLargest];
            heap[indexLargest] = temp;
        }

        return siftDown(heap, indexLargest);

    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }
}