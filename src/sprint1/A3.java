package sprint1;

import java.io.*;

public class A3 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer reader = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        final int emptyPlaceQuant = nextInt(reader);

        int[] placesNumbers = readList(reader, emptyPlaceQuant);

        calculate(placesNumbers, emptyPlaceQuant);

        for (Integer place : placesNumbers) {
            writer.write(place + " ");
        }
        writer.flush();
    }

    private static int[] readList(StreamTokenizer in, int n) throws IOException {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = nextInt(in);
        }
        return data;
    }

    private static void calculate(int[] source, int quant) {
        int steps = quant;

        for (int i = 0; i < quant; ++i) {
            if (source[i] == 0) {
                steps = 0;
            } else {
                source[i] = steps;
            }
            ++steps;
        }
        steps = quant;
        for (int i = quant - 1; i >= 0; --i) {
            if (source[i] == 0) {
                steps = 0;
            } else {
                source[i] = Math.min(source[i], steps);
            }
            ++steps;
        }
    }

    private static int nextInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}