package sprint5.ffinal;

    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : массив

Читаем данные из инпута построчно, создаем объекты Participant и вставляем в массив в порядке получения.
Затем преобразуем массив в пирамиду при помощи просеивания вниз.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

--ID успешной посылки--

     */

import java.io.*;

public class A {
    private Participant[] heap;

    //TODO переменные для дебага, убрать
    private int maxSize;
    private int currentSize;


    public A(int size) {
        this.heap = new Participant[size + 1];
        maxSize = size + 1;
        currentSize = 0;
    }

    public void insert(Participant participant, int index) {
        heap[index] = participant;
    }

    public void displayArray() {
        for (int i = 1; i < maxSize; i++) {
            System.out.print(heap[i].getName() + " ");
            System.out.println("");
        }
    }

    public void displayHeap() {
        int nBlanks = 32;
        int itemPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = ".....................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(" ");

                    System.out.print(heap[j].getName());

                    if (++j == currentSize)
                        break;
                    if (++column == itemPerRow) {
                        nBlanks /= 2;
                        itemPerRow *= 2;
                        column = 0;
                        System.out.println();
                    } else

                        for (int l = 0; l < nBlanks * 2 - 2; k = l++) {
                            System.out.print(" ");
                        }
                    System.out.println("\n " + dots + dots);
                }
        }
    }

    public void siftDown(int index) {
        // Your code
        //потомки вершины, от которой нужно сделать просеивание
        int left = 2 * index;
        int right = 2 * index + 1;

        // Нет дочерних узлов, элемент не с кем просеивать
        if (left >= heap.length-1) {
            return;
        }
        // есть левый элемент, а правого нет, но левый меньше или равен текущему
        if ((left < heap.length-1) && heap[left].compareTo(heap[index]) <= 0 && right >= heap.length) {
            return;
        }
        // есть левый элемент и правого нет, но левый и оба они равны текущему
        if ((left < heap.length-1) && right < heap.length-1 && heap[left].compareTo(heap[index]) <= 0 && heap[right].compareTo(heap[index]) <= 0) {
            return;
        }

        // в данной точке мы точно знаем, что есть как миниму левый узел со значение больше корня
        int indexLargest = left;
        // проверяем, что есть оба дочерних узла и присваиваеи большее  значение
        //переменной  indexLargest
        if (right < heap.length && heap[right].compareTo(heap[left]) > 0) {
            indexLargest = right;
        }

        //меняем местами элементы, если больший по значению потомок больше чем корень
        if (heap[indexLargest].compareTo(heap[index]) > 0) {
            Participant temp = heap[index];
            heap[index] = heap[indexLargest];
            heap[indexLargest] = temp;
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int participantNumber = Integer.parseInt(reader.readLine());
            int j;

            final A heap = new A(participantNumber);

            for (int i = 1; i < participantNumber + 1; i++) {
                final String[] currentParticipant = reader.readLine().trim().split(" ");
                heap.insert(new Participant(currentParticipant[0], Integer.parseInt(currentParticipant[1]),
                        Integer.parseInt(currentParticipant[2])), i);
            }

            for (j = participantNumber / 2 ; j >= 1; j--) {
                heap.siftDown(j);
            }


            //TODO метод для дебага, убрать
            heap.displayArray();

            System.out.println(" Heap: ");
            heap.displayHeap();
        }
    }

    public static class Participant implements Comparable<Participant> {

        private final String name;
        private final int score;
        private final int penalty;

        public Participant(String name, int score, int penalty) {
            this.name = name;
            this.score = score;
            this.penalty = penalty;
        }

        public String getName() {
            return name;
        }

        @Override
        //TODO доделать поправить -1 как было в ревью во втором спринте
        public int compareTo(Participant s) {
            if (this.score > s.score) {
                return 1;
            }
            if (this.score < s.score) {
                return -1;
            } else {
                if (this.penalty < s.penalty) {
                    return 1;
                }
                if (this.penalty > s.penalty) {
                    return -1;
                } else {
                    return this.name.compareTo(s.getName());
                }
            }
        }
    }
}
