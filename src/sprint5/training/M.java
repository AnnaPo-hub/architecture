package sprint5.training;

public class M {
    /*
   Функция принимает в качестве аргументов массив,
    в котором хранятся элементы кучи, и индекс элемента,
    от которого надо сделать просеивание вверх.
    Функция должна вернуть индекс, на котором элемент оказался после просеивания.
    Также необходимо изменить порядок элементов в переданном в функцию массиве.
     */
    public static int siftUp(int[] heap, int index) {
        //находимся в корне
        if (index == 1) {
            return index;
        }

        //индекс родителя
        int parentIndex = index / 2;

        //если родитель меньше элемента из ввода , то меняем их местами
        if (heap[parentIndex] < heap[index]) {
            int temp = heap[parentIndex];
            heap[parentIndex] = heap[index];
            heap[index] = temp;
        } else return index;
        return siftUp(heap, parentIndex);
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }
}
