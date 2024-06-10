package sprint4.ffinal;
    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : хеш-таблица
Формирование словаря:
Проходим в цикле по каждому слову из каждого  документа,
складываем в хеш-таблицу в следующем  виде:  ключ -  слово , значение в свою очередь в виде хеш-таблицы:
ключ - номер документа, значение - количество вхождений в этот документ.
Это позволяет ускорить обработку результатов, когда слово встречается несколько раз в документе.

Выбор релевантных документов:
В цикле проходим поисковые запросы, проходим каждое уникальное  слово,
получаем данные из "словаря".
Агрегируем данные по всем словам, сортируем данные в соответствии с указанной в задаче логикой релевантности и выводим результат.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для хранения данных мы пользуемся  хеш-таблицами из стандартных библиотек, поэтому не сомневаемся в том, что найдем элемент по
его значению там где его положили.
Складываем данные в хеш-таблицу, далее  по  слову из поискового запроса находим данные по вхождениям.
Если слова одинаковые, то хэш у них также одинаковый, к тому же стандартная библиотека защищена от коллизий.
Поэтому мы можем быть уверены, что найдем именно те данные по количеству вхождений слова, которые положили в таблицу.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность построения словаря -  О(n), где n количество слов по всем документам, которое в итоге внесем в таблицу.
Эта часть выполняется однократно.
Сложность поиска  в среднем  - О(L), где L количество слов в запросе.
Итоговая сложность: O(1)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n)  памяти, где n - это количество использованных ключей в хэш-таблице, то есть количество слов в "словаре"

--ID успешной посылки--
https://contest.yandex.ru/contest/24414/run-report/115048412/
     */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final HashMap<String, HashMap<Integer, Integer>> vocabulary =
                    buildVocabulary(Integer.parseInt(reader.readLine()), reader);

            final int requestQuantity = Integer.parseInt(reader.readLine());
            for (int i = 0; i < requestQuantity; i++) {
                String request = reader.readLine();
                String requestResult = "";

                List<Map.Entry<Integer, Integer>> res = getSortedResult(search(readSetOfString(request), vocabulary));
                for (int j = 0; j < Math.min(5, res.size()); j++) {
                    Map.Entry<Integer, Integer> entry = res.get(j);
                    requestResult = requestResult + entry.getKey() + " ";
                }
                writer.write(requestResult);
                writer.newLine();
            }
        }
    }

    private static HashMap<Integer, Integer> search(HashSet<String> request, HashMap<String, HashMap<Integer, Integer>> vocabulary) {
        final HashMap<Integer, Integer> occurrenceMap = new HashMap<>();

        for (String currWord : request) {
            final HashMap<Integer, Integer> listOfDocs = vocabulary.get(currWord);
            if (listOfDocs != null)
                listOfDocs.forEach((docNumber, count) -> occurrenceMap.merge(docNumber, count, Integer::sum));
        }
        return occurrenceMap;
    }

    private static HashMap<String, HashMap<Integer, Integer>> buildVocabulary(int documentQuantity, BufferedReader reader) throws IOException {
        final HashMap<String, HashMap<Integer, Integer>> vocabulary = new HashMap<>();
        for (int i = 0; i < documentQuantity; ++i) {
            final List<String> document = readListOfString(reader);
            for (String word : document) {
                if (vocabulary.containsKey(word)) {
                    vocabulary.get(word).merge(i + 1, 1, Integer::sum);
                } else {
                    HashMap<Integer, Integer> objects = new HashMap<>();
                    objects.put(i + 1, 1);
                    vocabulary.put(word, objects);
                }
            }
        }
        return vocabulary;
    }

    public static List<Map.Entry<Integer, Integer>> getSortedResult(HashMap<Integer, Integer> resultMap) {
        return resultMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0 ?
                        o1.getKey().compareTo(o2.getKey()) : (o2.getValue().compareTo(o1.getValue())))
                .collect(Collectors.toList());
    }

    private static List<String> readListOfString(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .collect(Collectors.toList());
    }

    private static HashSet<String> readSetOfString(String string) throws IOException {
        return (HashSet<String>) Arrays.stream(string.split(" "))
                .collect(Collectors.toCollection(HashSet::new));
    }
}