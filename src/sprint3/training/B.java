package sprint3.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final List<Integer> input = readList(reader);
            genBinary(0, input, "");
        }
    }

    static void genBinary(int index, List<Integer> input, String prefix) {
        ArrayList<String> letters = new ArrayList<>();
        if (index == input.size()) {
            System.out.print(prefix + " ");
        } else {
            final Integer integer = input.get(index);
            switch (integer) {
                case 2:
                    letters.add("a");
                    letters.add("b");
                    letters.add("c");
                    break;
                case 3:
                    letters.add("d");
                    letters.add("e");
                    letters.add("f");
                    break;
                case 4:
                    letters.add("g");
                    letters.add("h");
                    letters.add("i");
                    break;
                case 5:
                    letters.add("j");
                    letters.add("k");
                    letters.add("l");
                    break;
                case 6:
                    letters.add("m");
                    letters.add("n");
                    letters.add("o");
                    break;
                case 7:
                    letters.add("p");
                    letters.add("q");
                    letters.add("r");
                    letters.add("s");
                    break;
                case 8:
                    letters.add("t");
                    letters.add("u");
                    letters.add("v");
                    break;
                case 9:
                    letters.add("w");
                    letters.add("x");
                    letters.add("y");
                    letters.add("z");
                    break;
            }
            letters.forEach((it) -> genBinary(index + 1, input, prefix + it));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
