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
            List<String> firstLetterList = new ArrayList<>();
            List<String> secondLetterList = new ArrayList<>();

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


            switch (input.get(0)) {
                case 2:
                    firstLetterList = two;
                    break;
                case 3:
                    firstLetterList = three;
                    break;

            }

            switch (input.get(1)) {
                case 2:
                    secondLetterList = two;
                    break;
                case 3:
                    secondLetterList = three;
                    break;
            }
            genBinary(0, firstLetterList, secondLetterList, "");
        }
    }

    static void genBinary(int counter, List<String> lettersList, List<String> secondLettersList, String prefix) {
        if (counter == 2) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < lettersList.size(); i++) {
                genBinary(counter + 1, lettersList, secondLettersList, prefix + lettersList.get(i));
            }
        }
    }


    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
