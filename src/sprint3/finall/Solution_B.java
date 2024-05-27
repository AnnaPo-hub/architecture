package sprint3.finall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution_B {
    public static void main(String[] args) throws IOException {

        final ArrayList<Integer> score = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int participantsNumber = Integer.parseInt(reader.readLine());

            for (int i = 0; i < participantsNumber; i++) {
                final String[] currParticipant = reader.readLine().trim().split(" ");
                score.add(Integer.parseInt(currParticipant[1]));
            }
        }

        quicksort(score, 0, score.size() - 1);
    }

    public static void swap(ArrayList<Integer> array, int leftIndex, int rightIndex) {
        int temp = array.get(leftIndex);
        array.set(leftIndex, array.get(rightIndex));
        array.set(rightIndex, temp);
    }


    public static int partition(ArrayList<Integer> array, int leftIndex, int rightIndex) {
        int pivot = array.get(rightIndex);
        int rightCopy = rightIndex;
        while (leftIndex < rightCopy) {
            while (leftIndex < rightCopy && array.get(leftIndex) < pivot) {
                leftIndex++;
            }
            while (leftIndex < rightCopy && array.get(rightCopy) >= pivot) {
                rightCopy--;
            }
            swap(array, leftIndex, rightCopy);
        }
        swap(array, leftIndex, rightIndex);
        System.out.println(Arrays.toString(array.toArray()));
        return leftIndex;
    }

    public static void quicksort(ArrayList<Integer> array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            final int newLeftIndex = partition(array, leftIndex, rightIndex);
            quicksort(array, leftIndex, newLeftIndex - 1);
            quicksort(array, newLeftIndex, rightIndex);
        }
    }
}
