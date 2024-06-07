package sprint4.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HashTable<K, V> {

    private ArrayList<Node<K, V>> bucketArray;
    private int bucketQuantity;
    //  private int size;


    public HashTable(ArrayList<Node<K, V>> bucketArray, int bucketQuantity) {
        this.bucketArray = bucketArray;
        this.bucketQuantity = bucketQuantity;
        //  this.size = size;
    }


    private int getIndex(K key) {
        return key.hashCode() % bucketQuantity;
    }

    private void put(K key, V value) {
        bucketArray.add(getIndex(key), new Node(key, value));
    }

    private V get(K key) {
        int index = getIndex(key);
        return bucketArray.get(index).getValue();
    }

    private V delete(K key) {
        int index = getIndex(key);
        return bucketArray.remove(index).getValue();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int requestQuantity = Integer.parseInt(reader.readLine());

            final HashTable<Integer, Integer> salaryHashTable = new HashTable<>(new ArrayList<>(), 10);

            for (int i = 0; i < requestQuantity; i++) {
                final String[] command = reader.readLine().trim().split(" ");

                switch (command[0]) {
                    case "get":
                        salaryHashTable.get(Integer.parseInt(command[1]));
                        //If -1 вывести None
                        break;
                    case "put":
                        salaryHashTable.put(Integer.parseInt(command[1]), Integer.parseInt(command[1]));
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

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNextNode() {
            return nextNode;
        }
    }

}
