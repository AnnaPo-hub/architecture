package sprint5.training;

public class SolutionL {
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

        // проверяем, что есть оба дочерних узла и присваиваеи меньшее значение
        //переменной  indexSmall
        int indexSmall = left;
        if (right < heap.length && heap[right] < heap[left]) {
            indexSmall = right;
        }

        //меняем местами элементы, если меньший по значению потомок больше чем корень
        if (heap[indexSmall] < heap[index]) {
            int temp = heap[index];
            heap[index]= heap[indexSmall];
            heap[indexSmall]= heap [temp];
            siftDown(heap, indexSmall);
        }
        return indexSmall;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }
}
