package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Heap implements Comparable<Heap> {
    double price;
    double weight;


    public Heap(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.##");
        return format.format(weight) + " " + format.format(price);
    }

    @Override
    public int compareTo(Heap heap) {
        if (this.price > heap.price)
            return -1;
        else if (this.price < heap.price)
            return 1;
        else {
            if (this.weight < heap.weight)
                return -1;
            else if (this.weight > heap.weight)
                return 1;

        }
        return 0;
    }
}

public class C {
    ArrayList<Heap> heaps;
    int bagVolume;
    int income;

    public C(int bagVolume) {
        heaps = new ArrayList<>();
        income = 0;
        this.bagVolume = bagVolume;
    }

    private void readAndSaveAllHeaps(BufferedReader reader, int lessonsQuantity) throws IOException {
        for (int i = 0; i < lessonsQuantity; i++) {
            final double[] heap = readList(reader);
            heaps.add(new Heap(heap[0], heap[1]));
        }
    }

    private int fillBag(List<Heap> heaps, int bagVolume) {
        int income = 0;
        for (Heap currHeap : heaps) {
            if (bagVolume > 0) {
                if (bagVolume - currHeap.weight >= 0) {
                    bagVolume -= currHeap.weight;
                    income += currHeap.weight * currHeap.price;
                } else {
                    income += bagVolume * currHeap.price;
                    bagVolume = 0;
                }
            }
        }
        return income;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int bagVolume = Integer.parseInt(reader.readLine());
            final int heapsQuantity = Integer.parseInt(reader.readLine());

            C c = new C(bagVolume);
            c.readAndSaveAllHeaps(reader, heapsQuantity);
            Collections.sort(c.heaps);

            System.out.println(c.fillBag(c.heaps, c.bagVolume));
        }
    }

    private static double[] readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToDouble(Double::parseDouble).toArray();
    }
}
