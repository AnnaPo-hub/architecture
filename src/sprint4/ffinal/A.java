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
Агрегируем данные по всем словам, выбираем топ-5 в соответствие с указанной в задаче логикой релевантности и выводим результат.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для хранения данных мы пользуемся  хеш-таблицами из стандартных библиотек, поэтому не сомневаемся в том, что найдем элемент по
его значению там где его положили.
Складываем данные в хеш-таблицу, далее  по  слову из поискового запроса находим данные по вхождениям.
Если слова одинаковые, то хэш у них также одинаковый, к тому же стандартная библиотека защищена от коллизий.
Поэтому мы можем быть уверены, что найдем именно те данные по количеству вхождений слова, которые положили в таблицу.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
A - Сложность получается путем выполнения m (количество запросов) операций A,B . O(m*n)+m*n)=O(m*n). Где
	m - количество запросов
	n - количество проиндексированных документов, то есть количество документов поданных на вход программы в первой части
B - Получение слова из словаря - 0(1), получение для всех слов запроса, O(k) - k среднее количество слов в запросе.
	Слияние данных в итоговом массиве проводится для каждого слова из запроса  O(k*n), где n-количество проиндексированных
	документов (документ = строка из нескольких слов)
	0(k+k*n) = 0(k*(1+n)) = 0(k*n)

C - получение максимального значения выполняется за O(n), где n-количество проиндексированных документов
 (документ = строка из нескольких слов). В индексе всегда все документы.
 Тут НЕ имеется ввиду документы, в которых найдены слова из запроса.
 Получение максимального значения выполняется по структуре содержащей (номер документ; количество вхождений всех
 слов запроса в этот документов)
 Получение первых пяти - 5*O(n), что мы приравниваем к O(n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Общая память для всех запросов:
O(n)  памяти, где n - это количество использованных ключей, то есть количество уникальных слов во всех документах.
Память для одного запроса:
O(k)*O(L)- k - количество уникальных слов в запросе. L- количество документов.

--ID успешной посылки--
https://contest.yandex.ru/contest/24414/run-report/115117154/
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
                StringBuilder requestResult = new StringBuilder();

                List<Map.Entry<Integer, Integer>> res = getTop5Result2(search(readSetOfString(request), vocabulary));
                res.forEach(entry -> requestResult.append(entry.getKey()).append(" "));
                writer.write(requestResult.toString());
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

    public static List<Map.Entry<Integer, Integer>> getTop5Result(HashMap<Integer, Integer> resultMap) {
        return resultMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0 ?
                        o1.getKey().compareTo(o2.getKey()) : (o2.getValue().compareTo(o1.getValue())))
                .limit(5)
                .collect(Collectors.toList());
    }


    public static List<Map.Entry<Integer, Integer>> getTop5Result2(HashMap<Integer, Integer> resultMap) {

        List<Map.Entry<Integer, Integer>> result = new ArrayList<>();

        for (int i = 0; i < 5 && !resultMap.isEmpty(); ++i) {
            Optional<Map.Entry<Integer, Integer>> maxValue = resultMap.entrySet().stream().min((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0 ?
                    o1.getKey().compareTo(o2.getKey()) : (o2.getValue().compareTo(o1.getValue())));
            if (maxValue.isPresent()) {
                result.add(maxValue.get());
                resultMap.remove(maxValue.get().getKey());
            }
        }
        return result;
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