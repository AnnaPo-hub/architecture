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

            List<String> two = new ArrayList<>();
            two.add("a");
            two.add("b");
            two.add("c");

            List<String> three = new ArrayList<>();
            three.add("d");
            three.add("e");
            three.add("f");

            List<String> four = new ArrayList<>();
            four.add("g");
            four.add("h");
            four.add("i");

            List<String> five = new ArrayList<>();
            five.add("j");
            five.add("k");
            five.add("l");

            List<String> six = new ArrayList<>();
            six.add("m");
            six.add("n");
            six.add("o");

            List<String> seven = new ArrayList<>();
            seven.add("p");
            seven.add("q");
            seven.add("r");
            seven.add("s");

            List<String> eight = new ArrayList<>();
            eight.add("t");
            eight.add("u");
            eight.add("v");


            List<String> nine = new ArrayList<>();
            nine.add("w");
            nine.add("x");
            nine.add("y");
            nine.add("z");


            genBinary(input, "");
        }
    }

    static void genBinary(List<Integer> input, String prefix) {

        ArrayList<String> letters = new ArrayList<>();
        if (input.isEmpty()) {
            System.out.println(prefix);
        } else {
            final Integer integer = input.get(0);
            input.remove(0);

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
            }
            letters.forEach((it) -> genBinary(input, prefix + it));
        }
    }


    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
