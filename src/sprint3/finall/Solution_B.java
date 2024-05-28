package sprint3.finall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : динамический массив.
Считываем участников и помещаем их в виде объектов в динамический массив.
Далее осуществляем быструю сортировку in-place.
По индексу выбираем опорный элемент из середины массива.
Добавляем левый и правый указатели  изначально на первый и последний элементы массива.
В соответствиями с условиями сортировки из задачи передвигаем указатели и меняем местами элементы,
перемещаем влево элементы, которые меньше опорного элемента, а вправо - которые больше.
Рекурсивно  повторяем такие переставновки, передавая в метод разные участки массива по индексам.
Таким образом, в итоге получаем отсортированный массив.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Быстрая сортировка - один из классических алгоримов, который не требует доказательств.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность быстрой сортировки зависит от выбора опорного элемента. В данной реализации в качестве опорного элемента
выбран средний элемент массива по индексу. Таким образом, скорость сортировки O(n log n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n)  памяти, где n - это  количество элементов в массиве, передаваемое в качестве параметра.

--ID успешной посылки--
https://contest.yandex.ru/contest/23815/run-report/114694236/
     */

public class Solution_B {
    public static void main(String[] args) throws IOException {

        final ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int participantsNumber = Integer.parseInt(reader.readLine());

            for (int i = 0; i < participantsNumber; i++) {
                final String[] currParticipant = reader.readLine().trim().split(" ");
                students.add(new Student(currParticipant[0], Integer.parseInt(currParticipant[1]), Integer.parseInt(currParticipant[2])));
            }
        }

        quicksort(students, 0, students.size() - 1);

        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    public static void swap(ArrayList<Student> array, int leftIndex, int rightIndex) {
        Student temp = array.get(leftIndex);
        array.set(leftIndex, array.get(rightIndex));
        array.set(rightIndex, temp);
    }


    public static int partition(ArrayList<Student> array, int leftIndex, int rightIndex) {
        int pivotIndex = (rightIndex + leftIndex) / 2;
        Student pivotStudent = array.get(pivotIndex);

        while (leftIndex <= rightIndex) {
            while (array.get(leftIndex).compareTo(pivotStudent) < 0) {
                leftIndex++;
            }
            while (array.get(rightIndex).compareTo(pivotStudent) > 0) {
                rightIndex--;
            }
            if (leftIndex >= rightIndex)
                break;
            swap(array, leftIndex++, rightIndex--);
        }
        return rightIndex;
    }

    public static void quicksort(ArrayList<Student> array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            final int pivotIndex = partition(array, leftIndex, rightIndex);
            quicksort(array, leftIndex, pivotIndex);
            quicksort(array, pivotIndex + 1, rightIndex);
        }
    }

    public static class Student implements Comparable<Student> {

        private final String name;
        private final int score;
        private final int penalty;

        public Student(String name, int score, int penalty) {
            this.name = name;
            this.score = score;
            this.penalty = penalty;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Student s) {
            if (this.score > s.score) {
                return -1;
            }
            if (this.score < s.score) {
                return 1;
            } else {
                if (this.penalty < s.penalty) {
                    return -1;
                }
                if (this.penalty > s.penalty) {
                    return 1;
                } else {
                    return this.name.compareTo(s.getName());
                }
            }
        }
    }
}
