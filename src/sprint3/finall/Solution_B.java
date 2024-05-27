package sprint3.finall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Solution_B {
    public static void main(String[] args) throws IOException {

        final ArrayList<Integer> score = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int participantsNumber = Integer.parseInt(reader.readLine());

            for (int i = 0; i <participantsNumber; i++) {
                final String[] currParticipant = reader.readLine().trim().split(" ");
                score.add(Integer.parseInt(currParticipant[1]));
            }
        }

        quicksort(score, 0, score.size() - 1);
    }

    public static int partition(ArrayList<Integer> array, int leftIndex, int rightIndex, int pivot) {
        while (leftIndex < rightIndex) {
            if (array.get(leftIndex) > pivot && array.get(rightIndex) < pivot) {
                int temp = array.get(leftIndex);
                array.set(leftIndex, array.get(rightIndex));
                array.set(rightIndex, temp);
            }
            leftIndex++;
            rightIndex--;
        }

        return leftIndex;
    }

    public static ArrayList<Integer> quicksort(ArrayList<Integer> array, int leftIndex, int rightIndex) {
        if (rightIndex-leftIndex <=1) {
            return array;
        } else {
            Random random = new Random();
            int pivot = array.get(random.nextInt(array.size()));
            final int newLeftIndex = partition(array, leftIndex, rightIndex, pivot);
            quicksort(array, 0, newLeftIndex);
            quicksort(array, newLeftIndex, array.size() - 1);
        }
        return array;
    }
}
