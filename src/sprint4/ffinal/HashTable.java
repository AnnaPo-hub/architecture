package sprint4.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class HashTable<K, V> {

    private ArrayList<Node<K, V>> bucketArray;
    private int bucketQuantity;
    private int size;

    private int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int getIndex(K key) {
        int hashCode = hashCode(key);
        return hashCode % bucketQuantity;
    }

    private void put(Node node) {

    }

    private Integer get(Integer key) {
//если не найден вернуть -1
        return 0;
    }

    private Integer delete(Integer key) {
//вывести хранимое по данному ключу значение и удалить ключ  или -1
        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int requestQuantity = Integer.parseInt(reader.readLine());

            final HashTable<Integer, Integer> salaryHashTable = new HashTable<>();

            for (int i = 0; i < requestQuantity; i++) {
                final String[] command = reader.readLine().trim().split(" ");

                switch (command[0]) {
                    case "get":
                        salaryHashTable.get(Integer.parseInt(command[1]));
                        //If -1 вывести None
                        break;
                    case "put":
                        salaryHashTable.put(new Node<>(Integer.parseInt(command[1]), Integer.parseInt(command[1])));
                        break;
                    case "delete":
                        //if  -1, вывести None
                        salaryHashTable.delete(Integer.parseInt(command[1]));
                        break;
                }
            }
        }
    }

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> nextNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
