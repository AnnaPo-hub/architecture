package sprint4.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final HashMap<String, List<Integer>> vocabulary =
                    buildVocabulary(Integer.parseInt(reader.readLine()), reader);

            final int requestQuantity = Integer.parseInt(reader.readLine());

            for (int i = 0; i < requestQuantity; i++) {

                findThat(readListOfString(reader), vocabulary);
            }

        }
    }

    private static List<Integer> findThat(List<String> request, HashMap<String, List<Integer>> vocabulary) {
        final ArrayList<String> checkedWord = new ArrayList<>();
        final ArrayList<Integer> result = new ArrayList<>();

        final HashMap<Integer, Integer> occurrenceMap = new HashMap<>();

        for (String currWord : request) {
            if (checkedWord.contains(currWord)) {

            } else {
                checkedWord.add(currWord);

                System.out.println("currWord is " + currWord);
             //   final List<Integer> listOfDocs = vocabulary.get(currWord.hashCode());
                //получаем вхождения в документ [1, 2, 2],
                final List<Integer> listOfDocs = vocabulary.get(currWord);

                if (listOfDocs == null) {
                    return null;
                } else {

                    for (int i = 0; i < listOfDocs.size(); i++) {
                        if (occurrenceMap.containsKey(listOfDocs.get(i))) {
                            final Integer integer = occurrenceMap.get(listOfDocs.get(i));
                            occurrenceMap.put(listOfDocs.get(i), integer+ 1);
                        } else {
                            occurrenceMap.put(listOfDocs.get(i), 1);
                        }
                    }
                    System.out.println(
                            occurrenceMap.entrySet()
                    );
                }

            }
        }
        return result;
    }

    private static HashMap<String, List<Integer>> buildVocabulary(int documentQuantity, BufferedReader reader) throws IOException {
        final HashMap<String, List<Integer>> vocabulary = new HashMap<>();
        for (int i = 0; i < documentQuantity; i++) {
            int lineNumber = i + 1;
            final List<String> document = readListOfString(reader);
            for (String word : document) {

                final int hash = word.hashCode();
                if (vocabulary.containsKey(word)) {
                    vocabulary.get(word).add(lineNumber);
                } else {
                    ArrayList<Integer> objects = new ArrayList<>();
                    objects.add(lineNumber);
                    vocabulary.put(word, objects);
                }
            }
        }
        System.out.println(vocabulary.entrySet());
        return vocabulary;
    }

    private static List<String> readListOfString(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }
}
