package sprint8.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : очередь на базе связанного списка для обработки элементов из инпута,
массив для сохранения получившихся после распаковки слов

Разбираем каждое слово из инпута посимвольно и складываем в очередь.
Затем рекурсивно производим распаковку: берем символ из очереди, если это цифра, то сохраняем его во временную переменную,
если буква- то сохраняем в стринг, если открывающая скобка - вызываем метод рекурсивно, если закрывающая скобка, то возвращаем
полученный стринг, а после выхода записываем в результат такое количество получившихся стрингов, сколько  было сохранено во
временной переменной.
Получившийся после распаковки стринг сохраняем в массив.

Передаем массив распакованных стрингов в метод getMaxPrefix  для получения  наибольшего общего префикса.
Берём первую строку массива и принимаем её за самый длинный общий префикс.

Устанавливаем 2 указателя:  первый  показывает  на индекс последнего символа,
который совпал с нашим общим префиксом , второй указатель перемещается по строкам посимвольно.
Перебираем оставшиеся элементы массива посимвольно, если элементы совпадают , то передвигаем первый  указатель , если не совпадают, то сразу выходим,
тк дальше нет смысла проверять.

Ответом будет сабстринг от 0-го элемента до указателя 1.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Не имеет значения какой элемент принять за самый длинный префикс, тк мы будем продолжать сравнение до тех пора пока второй индикатор меньше
чем длина эталона и длина очередного стринга.
Если выбранное слово самое короткое, то оно и будет самым большим  общим префиксом, тк самый длинный префикс слова - это и есть само слово,
 а если оно самое длинное, то лишняя часть "отрежется".
Мы сравнимаем символы, пока символы совпадают - логично, что  префиксы одинаковые,
 если символы не совпадают, логично, что с этого элемента префиксы не совпадают.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Распаковка слов :
Будем складывать в очередь и затем извлекать символы  для распаковки строк  - эти операции занимают константное время O(1).
Также будем проводить сравнение по каждому символу (определяя цифра это, буква или открывающая/закрывающая скобка).
То есть, получим О(k*n) -  где n - количество символов в  строке, а k -количество строк

Поиск общего префикса:
Будем по индексу доставать из массива каждую строку, затем перебирать каждый символ по индексу,
каждая из этих операций занимает константное время O(1).
Это значит, что мы получим сложность О(kl) - где k - количество строк, l - количество символов в распакованной строке.
В худшем случае придется сделать k-1 сравнений строк по l символов.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n)  - где n - количество слов в инпуте, которое мы получаем в качестве первого параметра.

--ID успешной посылки--
https://contest.yandex.ru/contest/26133/run-report/117154103/
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lineQuantity = Integer.parseInt(reader.readLine());

            String[] strings = new String[lineQuantity];
            for (int i = 0; i < lineQuantity; i++) {
                Character[] chars = readList(reader);
                Queue<Character> queue = new LinkedList<>();

                Collections.addAll(queue, chars);
                String s = parseLine(queue);
                strings[i] = s;
            }
            System.out.println(getMaxPrefix(strings));
        }
    }

    private static String parseLine(Queue<Character> queue) {
        StringBuilder builder = new StringBuilder();
        int temp = 0;

        while (!queue.isEmpty()) {
            Character currElement = queue.poll();
            if (Character.isDigit(currElement)) {
                temp = Integer.parseInt(String.valueOf(currElement));
            } else if (Character.isLetter(currElement)) {
                builder.append(currElement);
            } else if (currElement == ('[')) {
                String s = parseLine(queue);
                for (int j = 0; j < temp; j++) {
                    builder.append(s);
                }
            } else if (currElement == ']') {
                return builder.toString();
            }
        }
        return builder.toString();
    }

    private static Character[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    }

    private static String getMaxPrefix(String[] strings) {
        if (strings.length == 0) {
            return "";
        } else if (strings.length == 1) {
            return strings[0];
        } else {
            return getCommonPrefix(strings);
        }
    }

    private static String getCommonPrefix(String[] strings) {
        String longestPrefix = strings[0];
        for (int i = 1; i < strings.length; i++) {
            String currString = strings[i];
            int ind1 = 0;
            int ind2 = 0;

            while (ind2 < longestPrefix.length() && ind2 < currString.length()) {
                if (longestPrefix.charAt(ind2) == currString.charAt(ind2)) {
                    ++ind1;
                } else {
                    break;
                }
                ++ind2;
            }
            longestPrefix = longestPrefix.substring(0, ind1);
        }
        return longestPrefix;
    }
}
