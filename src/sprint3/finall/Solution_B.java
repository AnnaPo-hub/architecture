package sprint3.finall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
            while (array.get(leftIndex).compareTo(pivotStudent) == -1) {
                leftIndex++;
            }
            while (array.get(rightIndex).compareTo( pivotStudent) == 1) {
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
}
