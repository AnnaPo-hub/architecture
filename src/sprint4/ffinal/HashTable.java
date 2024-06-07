package sprint4.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HashTable<K, V> {

    private ArrayList<Node<K, V>> bucketArray;
    private int bucketQuantity;
    //  private int size;


    public HashTable(int bucketQuantity) {
        bucketArray = new ArrayList<>();
        this.bucketQuantity = bucketQuantity;

        //заполняет бакеты null
        for (int i = 0; i < bucketQuantity; i++) {
            bucketArray.add(null);
        }
    }

    private int getIndex(K key) {
        return key.hashCode() % bucketQuantity;
    }

    private void put(K key, V value) {
        int index = getIndex(key);
        while (bucketArray.get(index) != null) {
            index++;
            index %= bucketQuantity;
        }
        bucketArray.add(getIndex(key), new Node(key, value));
    }

    private V get(K key) {
        int index = getIndex(key);
        while (bucketArray.get(index) != null) {
            if (bucketArray.get(index).key == key)
                return bucketArray.get(index).getValue();
            index++;
            index %= bucketQuantity;
        }
        return null;
    }

    private V delete(K key) {
        int index = getIndex(key);
        while (bucketArray.get(index) != null) {
            if (bucketArray.get(index).key == key) {
                Node<K, V> temp = bucketArray.get(index);
                bucketArray.set(index, null);
                return temp.getValue();
            }
            index++;
            index %= bucketQuantity;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int requestQuantity = Integer.parseInt(reader.readLine());

            final HashTable<Integer, Integer> salaryHashTable = new HashTable<>(113);

            for (int i = 0; i < requestQuantity; i++) {
                final String[] command = reader.readLine().trim().split(" ");

                switch (command[0]) {
                    case "get":
                        final Integer value = salaryHashTable.get(Integer.parseInt(command[1]));
                        System.out.println(value == null ? "None" : value);
                        break;
                    case "put":
                        salaryHashTable.put(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                        break;
                    case "delete":
                        final Integer deletedValue = salaryHashTable.delete(Integer.parseInt(command[1]));
                        System.out.println(deletedValue == null ? "None" : deletedValue);
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

        public V getValue() {
            return value;
        }
    }
}