package sprint3.finall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : динамический массив.
Считываем участников и помещаем их в виде объектов в динамический массив.
Далее осуществляем быструю сортировку in-place.
По индексу выбираем опорный элемент из середины массива.
Добавляем левый и правый указатели  изначально на первый и последний элементы массива.
В соответствиями с условиями сортировки из задачи передвигаем указатели и меняем местами элементы,
перемещаем влево элементы, которые меньше опорного элемента, а вправо - которые больше.
Для сравнения сложного объекта, класс реализует Comparable. Таким образом выполняется сортировка по нескольким полям.
Рекурсивно  повторяем такие перестановки, передавая в метод разные участки массива по индексам.
Таким образом, в итоге получаем отсортированный массив.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Очевидно,что алгоритм корректно сортирует массивы с нулевым или одним элементом,
так как массив из одного элемента уже отсортирован, а массив из нуля элементов не требует сортировки.

Для массивов более одного элемента: алгоритм выбирает опорный элемент из массива и разбивает массив на две подгруппы - элементы, меньше опорного, и элементы, больше опорного.
Не требуется доказывать, что JVM корректно сравнивает элементы.
Далее алгоритм рекурсивно применяется к обеим частям массива, то есть, он также отсортирует каждую из этих подгрупп, пока в подгруппах не останется 0 или 1 элемент,
который мы считаем отсортированным в соответствии с п.1.

Таким образом, доказывается корректность алгоритма quicksort: он разбивает массив на подгруппы, сортирует их рекурсивно.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность быстрой сортировки зависит от выбора опорного элемента. В данной реализации в качестве опорного элемента
выбран средний элемент массива по индексу. Таким образом, скорость сортировки O(n log n).
В худшем случае временная сложность будет О(n^2).

Итоговая временная сложность - O(n), где n - количество команд, подаваемых на вход.


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n)  памяти, где n - это  количество элементов в массиве, передаваемое в качестве параметра,
так как при реализация алгоритма новые массивы не создаются.
Для получения подмассивов используются указатели на начало и конец массива/подмассива.

Относительно дополнительной памяти:
В текущей реализации используется стек вызовов для хранения аргументов функции.
В среднем и в лучшем случае, пространственная  сложность пропорциональна O(log n).
В наихудшем случае сортировка выполнит O(n) вложенных рекурсивных вызовов и
 для нее потребуется O(n) дополнительного места.

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
            Collections.swap(array, leftIndex++, rightIndex--);
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
