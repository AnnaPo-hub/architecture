package sprint5.ffinal;

    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : массив

Читаем данные из инпута построчно, создаем объекты Participant и вставляем в массив в порядке получения.
Затем преобразуем массив в пирамиду при помощи просеивания вниз. Наверху оказывается нужный нам  лучший партисипант.
Переносим лучшего партисипанта в конец массива, начав заполнение отсортированного массива.
Затем снова просеиваем кучу и получаем в корне лучшего участника. Итд , пока не получим, начиная с 1-ой ячейки,
отсортированный по возрастанию массив.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

--ID успешной посылки--

     */

import java.io.*;

public class A {
    private Participant[] heap;
    private int currentSize;

    public A(int size) {
        this.heap = new Participant[size + 1];
        currentSize = 0;
    }

    public void insert(Participant participant, int index) {
        heap[index] = participant;
    }

    public Participant remove() {
        Participant participant = heap[1];
        heap[1] = heap[currentSize--];
        //System.out.println("переместили в корень " + heap[1].getName());
        //TODO heap[currentSize] = null чтобы убить там участника, котоого уже выше перенесли до этого
        siftDown(1);
        return participant;
    }

    public void siftDown(int index) {
        // Your code
        //потомки вершины, от которой нужно сделать просеивание
        int left = 2 * index;
        int right = 2 * index + 1;
        // Нет дочерних узлов, элемент не с кем просеивать
        if (left >= heap.length - 1) {
            return;
        }
        // есть левый элемент, а правого нет, но левый меньше или равен текущему
        if ((left < heap.length - 1) && heap[left].compareTo(heap[index]) <= 0 && right >= heap.length) {
            return;
        }
        //если есть левый и правый элемент и оба они меньше или равны текущему
        if ((left < heap.length - 1) && right < heap.length - 1 && heap[index].compareTo(heap[left]) >= 0 && heap[index].compareTo(heap[right]) >= 0) {
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
            //todo
            //System.out.println(heap[indexLargest].getName() + "поменялись местами с " + heap[index].getName());
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int participantNumber = Integer.parseInt(reader.readLine());
            int j;

            final A heap = new A(participantNumber + 1);

            for (int i = 1; i < participantNumber + 1; i++) {
                final String[] currentParticipant = reader.readLine().trim().split(" ");
                heap.insert(new Participant(currentParticipant[0], Integer.parseInt(currentParticipant[1]),
                        Integer.parseInt(currentParticipant[2])), i);
                ++heap.currentSize;
            }

            //просеиваем вниз  массив  партисипантов
            for (j = ((participantNumber + 1) / 2 - 1); j >= 1; j--) {
                heap.siftDown(j);
            }

            //извлекаем лучшего партисипанта, складываем его в конец массива и снова делаем просеивание
            for (j = participantNumber + 1; j > 1; j--) {
                Participant highestParticipant = heap.remove();
                System.out.println(highestParticipant.name);
                heap.insert(highestParticipant, j);
            }
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
                    //TODO
                  //  System.out.println("comparing names return " + this.name.compareTo(s.getName()));
                    return this.name.compareTo(s.getName());
                }
            }
        }
    }
}
