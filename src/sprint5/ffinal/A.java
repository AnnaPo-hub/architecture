package sprint5.ffinal;

    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : массив

Читаем данные из инпута построчно, создаем объекты Participant и вставляем в массив в порядке получения.
Затем представляем массив как пирамиду, применяем просеивание вниз. Наверху оказывается нужный нам лучший участник.
Переносим лучшего участника в конец массива, начав с конца массива заполнять отсортированные данные.
Уменьшаем размер массива, который еще предстоит пройти,  на 1 элемент.
Затем снова просеиваем пирамиду и получаем в корне следующего лучшего участника. Итд , пока не получим, начиная с 1-ой ячейки,
отсортированный по возрастанию массив.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Если мы идем снизу пирамиды, применяя метод просеивания вниз к каждой подпирамиде, то в итоге получаем нужный нам
самый большой элемент наверху пирамиды.
Пойдем снизу вверх: Самые нижние узлы без потомков уже являются правильными пирамидами,
тк у них нет связей, которые могли бы быть нарушены. Далее идем на уровень выше и применяем на каждом шаге метод просеивания вниз.
Получаем корректные подпирамиды с бОльшими элементами в корне. Таким образом, если на уровень ниже находятся правильные пирамиды, то при применении
метода просеивания вниз, пирамида уровнем выше тоже будет правильной.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Получение максимального значения для массива -  O(log n), зависит от глубины пирамиды.
Предыдущий шаг выполняется n раз, где n - количество участников,
которое мы получаем на вход в качестве параметра
Получаем итоговую сложность: O(n log n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
О(n) где n - количество участников, которое мы получаем на вход в качестве параметра.
Сортируем массив "in place", не создавая второй массив для отсортированных данных.

--ID успешной посылки--
https://contest.yandex.ru/contest/24810/run-report/115708053/
     */

import java.io.*;

public class A {
    private Participant[] heap;
    private int currentSize;
    private int maxSize;

    public A(int size) {
        maxSize = size;
        heap = new Participant[maxSize];
        currentSize = 0;
    }

    public void add(Participant participant, int index) {
        heap[index] = participant;
    }

    public Participant delete() {
        Participant participant = heap[0];
        heap[0] = heap[--currentSize];
        siftDown(0);

        return participant;
    }

    private void increaseSize() {
        currentSize++;
    }

    public void siftDown(int index) {
        int indexLargest;
        Participant root = heap[index];

        while (index < currentSize / 2) {

            //потомки вершины, от которой нужно сделать просеивание
            int left = 2 * index + 1;
            int right = left + 1;

            // Нет дочерних узлов, элемент не с кем просеивать
            if (left >= heap.length) {
                return;
            }
            // есть левый элемент, а правого нет, но левый меньше или равен текущему
            if ((left < heap.length) && heap[left].compareTo(heap[index]) <= 0 && right >= heap.length) {
                return;
            }
            //если есть левый и правый элемент и оба они меньше или равны текущему
            if ((left < heap.length) && right < heap.length && heap[index].compareTo(heap[left]) >= 0 && heap[index].compareTo(heap[right]) >= 0) {
                return;
            }

            // в данной точке мы точно знаем, что есть как минимум левый узел со значение больше корня
            indexLargest = left;
            // проверяем, что есть оба дочерних узла и присваиваеи большее  значение
            //переменной  indexLargest
            if (right < heap.length && heap[right].compareTo(heap[left]) > 0) {
                indexLargest = right;
            }

            //меняем местами элементы, если больший по значению потомок больше чем корень
            if (heap[indexLargest].compareTo(heap[index]) > 0) {

                heap[index] = heap[indexLargest];
                index = indexLargest;
            }
            heap[indexLargest] = root;
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int participantNumber = Integer.parseInt(reader.readLine());
            int j;

            final A heap = new A(participantNumber);

            for (int i = 0; i < participantNumber; i++) {
                final String[] currentParticipant = reader.readLine().trim().split(" ");
                heap.add(new Participant(currentParticipant[0], Integer.parseInt(currentParticipant[1]),
                        Integer.parseInt(currentParticipant[2])), i);
                heap.increaseSize();
            }

            //просеиваем вниз  массив  партисипантов
            for (j = participantNumber / 2 - 1; j >= 0; j--) {
                heap.siftDown(j);
            }

            //извлекаем лучшего партисипанта, складываем его в конец массива и снова делаем просеивание
            for (j = participantNumber - 1; j >= 0; j--) {
                Participant highestParticipant = heap.delete();
                System.out.println(highestParticipant.name);
                heap.add(highestParticipant, j);
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
        public int compareTo(Participant s) {
            if (this.score != s.score) {
                return this.score - s.score;
            } else {
                if (this.penalty != s.penalty) {
                    return s.penalty - this.penalty;
                } else {
                    return s.getName().compareTo(this.name);
                }
            }
        }
    }
}
