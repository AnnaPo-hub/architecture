package sprint3.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class С {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            final List<String> mainLine = readList(reader);
            List<String> secondLine = readList(reader);

            System.out.println(checkLine(mainLine, secondLine) ? "True" : "False");


//            final String[] line = reader.readLine().trim().split("");
//            final String[] secondLineArray = reader.readLine().trim().split("");


            //найти где первый раз встречается первая буква, запомнить индекс
            // после этого индекса найти где впервые  встречается следующая буква, запомнить  индекс , если ее нет, то прервать
            //после этого индекса  найти  где встречается следующая буква, если ее нет, то первать

//            Arrays.sort(secondLine);
//            StringBuilder builder = new StringBuilder();
//
//            for (int i = 0; i < secondLine.length; i++) {
//                if (i == 0 || !secondLine[i - 1].equals(secondLine[i])) {
//                    builder.append(secondLine[i]);
//                }
//
//            }
//            System.out.println(builder);
//            System.out.println(builder.toString().contains(line) ? "True" : "False");


        }
    }

    public static boolean checkLine(List<String> mainLine, List<String> secondLine) {

        for (int i = 0; i < mainLine.size(); i++) {

            int index = secondLine.indexOf(mainLine.get(i));
            if (index == -1) {
                return false;
            }
            if (index == 0) {
                ++index;
            }
            secondLine = secondLine.subList(index, secondLine.size());

        }
        return true;
    }

    static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .collect(Collectors.toList());
    }
}

