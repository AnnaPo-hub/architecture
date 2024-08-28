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
    Map<Character, TrieNode> children = new HashMap<>();
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

    //scsscevscevscesscsc
    public boolean isTextInside(String text, int index) {
        TrieNode currentNode = root;

        for (int i = index; i < text.length(); i++) {
            char c = text.charAt(i);
            if (currentNode.terminal && currentNode.children.size() == 0 && i < text.length() - 1) {
                System.out.println("иду заново искать, начиная с root");

                return isTextInside(text, i);
            }
            //последний символ текста
            if (i == text.length() - 1 && !currentNode.terminal) {
                return false;
            }
            if (!currentNode.children.containsKey(c) && currentNode.terminal) {
                System.out.println("иду заново искать, начиная с root");
                int temp = i;
                return isTextInside(text, temp + 1);
            }
            if (!currentNode.children.containsKey(c)) {
                System.out.println("текущая нода" + currentNode + "не содержит потомка " + c);
                return false;
            } else {
                currentNode = currentNode.children.get(c);
                System.out.println("перешел в ноду " + c);

                char nextChar = text.charAt(i + 1);

                if (!currentNode.children.containsKey(nextChar) && currentNode.terminal) {
                    int temp = i;
                    System.out.println("иду заново искать, начиная с root");
                    return isTextInside(text, temp + 1);
                    //cледующая находится
                } else if (currentNode.children.containsKey(nextChar)) {
                    currentNode = currentNode.children.get(nextChar);
                    ++i;
                    System.out.println("перешел в ноду " + nextChar);
                }


//                } else if (currentNode.terminal && i < text.length() - 1) {
//                    return isTextInside(text, i + 1);
//
//
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

            printSorted(trie.root);

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