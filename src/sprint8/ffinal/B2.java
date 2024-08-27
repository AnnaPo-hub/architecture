package sprint8.ffinal;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : trie - префиксное дерево для хранения слов


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

--ID успешной посылки--
 */

class TrieNode {
    Map<Character, TrieNode> children = new TreeMap<>();
    boolean terminal;
}

class Trie {
    TrieNode root = new TrieNode();

    public TrieNode addString(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            // На каждом шаге работаем с одним символом
            char c = word.charAt(i);

            if (!currentNode.children.containsKey(c)) {
                currentNode.children.put(c, new TrieNode());
            }
            currentNode = currentNode.children.get(c);
        }
        currentNode.terminal = true;
        return currentNode;
    }

    public boolean findNode(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!currentNode.children.containsKey(c)) {
                return false;
            } else {
                currentNode = currentNode.children.get(c);
            }
        }
        return true;
    }

    public boolean isTextInside(String text, int index) {
        TrieNode currentNode = root;

        for (int i = index; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!currentNode.children.containsKey(c)) {
                return false;
            } else {
                currentNode = currentNode.children.get(c);
                if (currentNode.terminal) {
                    return isTextInside(text, i + 1);
                }
            }
        }
        return true;
    }
}

public class B2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            int alphabetQuantity = Integer.parseInt(reader.readLine());

            Trie trie = new Trie();

            for (int i = 0; i < alphabetQuantity; i++) {
                String currWord = reader.readLine();
                trie.addString(currWord);
            }

            //  System.out.println(trie.findNode("i"));
            //   printSorted(trie.root);

            boolean textInside = trie.isTextInside(input, 0);
            System.out.println(textInside ? "YES" : "NO");
        }
    }

    public static void printSorted(TrieNode node) {
        printSorted2(node, 0);
    }

    static Map<Integer, String> levelSpacesMap = new HashMap<Integer, String>();

    static String getSpace(int level) {
        String result = levelSpacesMap.get(level);
        if (result == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            result = sb.toString();
            levelSpacesMap.put(level, result);
        }
        return result;
    }

    private static void printSorted2(TrieNode node, int level) {
        for (Character ch : node.children.keySet()) {
            System.out.println(getSpace(level) + ch);
            printSorted2(node.children.get(ch), level + 1);
        }
        if (node.terminal) {
            System.out.println();
        }
    }
}