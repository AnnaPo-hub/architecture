package sprint8.ffinal;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных :  префиксное дерево, построенное с помощью массивов символов из слов из инпута,
массив для хранения промежуточного результата

Получаем все слова из инпута и заносим их  в префиксное дерево.
Далее воспользуемся  динамическим программированием:
В массиве dp будем хранить возможность создать   строку  из  "алфавита" на данном этапе прохождения по индексу.

Базовый случай: строка из 0 элементов может быть составлена из любого алфавита, поэтому   dp[0] = true;

Проходим посимвольно по тексту из инпута и идем по префиксному дереву.
Переход динамики: если текущий узел терминальный и без текущего слова результат был true, тогда записываем в ячейку true,
иначе - false.
Получаем ответ в конце массива.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Очевидно, что строка из 0 элементов может быть составлена из любого алфавита, поэтому мы стартуем, от 0-го элемента true.
Далее двигаемся по  дереву и , логично, что если мы успешно дошли до терминальной точки то, значит, мы успешно составили часть текста.
Если при этом на предыдущем этапе мы тоже это сделали, значит на этот момент, текст  может быть составлен из имеющегося алфавита.
Логично, что если так мы пройдем по всему тексту, проставляя на всех терминальных точках true, то весь текст может быть составлен из
имеющихся слов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Построение префиксного дерева - O(L), где L — суммарная длина всех слов из инпута
Проход по дереву - O(n^2), где n - количество символов в строке

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Префиксное дерево - O(L), где L — суммарная длина всех слов из инпута.
Массив занимает - O(n), где n - количество символов в строке.

--ID успешной посылки--
https://contest.yandex.ru/contest/26133/run-report/117257396/
 */

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean terminal;


    public TrieNode put(Character c) {
        if (children[c - 'a'] == null) {
            children[c - 'a'] = new TrieNode();
        }
        return children[c - 'a'];
    }
}

class Trie {
    TrieNode root = new TrieNode();
    boolean[] dp;

    public void addString(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            currentNode = currentNode.put(word.charAt(i));
        }
        currentNode.terminal = true;
    }

    public boolean isTextInside(String text) {
        dp = new boolean[text.length() + 1];
        dp[0] = true;

        for (int i = 0; i < text.length(); i++) {
            TrieNode currentNode = root;
            if (dp[i]) {
                for (int j = i; j < text.length() + 1; j++) {
                    if (currentNode.terminal)
                        dp[j] = true;

                    if ((j == text.length()) || currentNode.children[text.charAt(j) - 'a'] == null) {
                        break;
                    }
                    currentNode = currentNode.children[text.charAt(j) - 'a'];
                }
            }
        }
        return dp[dp.length - 1];
    }
}

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            int alphabetQuantity = Integer.parseInt(reader.readLine());

            Trie trie = new Trie();

            for (int i = 0; i < alphabetQuantity; i++) {
                String currWord = reader.readLine();
                trie.addString(currWord);
            }

            boolean textInside = trie.isTextInside(input);
            System.out.println(textInside ? "YES" : "NO");
        }
    }
}