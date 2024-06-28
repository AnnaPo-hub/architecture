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
    private  int maxSize;

    public A(int size) {
        maxSize = size;
        heap = new Participant[maxSize];
        currentSize = 0;
    }

    public void addParticipant(Participant participant, int index) {
        heap[index] = participant;
    }

    public Participant delete() {
        Participant participant = heap[0];
        heap[0] = heap[--currentSize];
        //TODO
       // System.out.println("переместили в корень " + heap[1].getName());
            siftDown(0);

        return participant;
    }

    private void increaseSize(){
        currentSize++;
    }

    public void siftDown(int index) {
        int indexLargest;
        Participant root = heap[index];

        while (index< currentSize/2) {

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
            if ((left < heap.length) && right < heap.length  && heap[index].compareTo(heap[left]) >= 0 && heap[index].compareTo(heap[right]) >= 0) {
                return;
            }

            // в данной точке мы точно знаем, что есть как миниму левый узел со значение больше корня
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

                //todo
              //  System.out.println(heap[indexLargest].getName() + "поменялись местами с " + heap[index].getName());
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
                heap.addParticipant(new Participant(currentParticipant[0], Integer.parseInt(currentParticipant[1]),
                        Integer.parseInt(currentParticipant[2])), i);
                heap.increaseSize();
            }

            //просеиваем вниз  массив  партисипантов
            for (j = participantNumber/ 2 - 1; j >= 0; j--) {
                heap.siftDown(j);
            }

            //извлекаем лучшего партисипанта, складываем его в конец массива и снова делаем просеивание
            for (j = participantNumber-1; j >= 0; j--) {
                Participant highestParticipant = heap.delete();
                System.out.println(highestParticipant.name);

                heap.addParticipant(highestParticipant, j);
                //TODO
               // System.out.println(highestParticipant.name + "inserted at index " + j);
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
