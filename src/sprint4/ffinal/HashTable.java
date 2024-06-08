package sprint4.ffinal;
    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : динамический массив
Для каждого ключа вычисляется хэш, получаем индекс позиции путем вычисления остатка от деления хэша
на количество корзин.  Для  разрешения  коллиций используется линейное пробирование: если получаем такой же хэш,
который уже был внесен в таблицу, то ищем ближайшую свободную корзину и размещаем там элемент.

put —–   по индексу  элемента кладем элемент в таблицу.

get key –— по индексу идет поиск в массиве.

delete key –— помечаем  элемент по индексу как удаленный. Это необходимо для корректного получения элементов,
которые были размещены после текущего элемента при возникновении коллизии.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Всегда можем по ключу получить то, что сохранили, тк хэш одного и того же слова всегда одинаковый.
Тк для разрешения коллизий мы использовали линейное пробирование, то при получении элемента по хэшу мы дополнительно
проверили правильность его значения по ключу.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
put —–  О(1), если сразу попадаем в пустой бакет. O(n), где n - это количество  корзин

get key –—  В лучшем случае О(1), если сразу нашли нужную корзину или  в худшем случае O(n), где n - это количество
бакетов, которые нужно перебрать

delete key –— аналогично предыдущим методам, O(1) в лучшем случае или O(n) в худшем случае

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n)  памяти, где n - это  количество корзин.

--ID успешной посылки--
https://contest.yandex.ru/contest/24414/run-report/115054528/
     */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HashTable<K, V> {

    private ArrayList<Node<K, V>> bucketArray;
    private int bucketQuantity;

    public HashTable(int bucketQuantity) {
        bucketArray = new ArrayList<>();
        this.bucketQuantity = bucketQuantity;

        for (int i = 0; i < bucketQuantity; i++) {
            bucketArray.add(null);
        }
    }

    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(), bucketQuantity);
    }

    private void put(K key, V value) {
        int index = getIndex(key);
        while (bucketArray.get(index) != null && !bucketArray.get(index).isDeleted() && !bucketArray.get(index).isKeyEquals(key)) {
            index = Math.floorMod(++index, bucketQuantity);
        }
        bucketArray.set(index, new Node(key, value));
    }

    private V get(K key) {
        int index = getIndex(key);
        while (bucketArray.get(index) != null) {
            if (bucketArray.get(index).isKeyEquals(key))
                return bucketArray.get(index).getValue();
            index = Math.floorMod(++index, bucketQuantity);
        }
        return null;
    }

    private V delete(K key) {
        int index = getIndex(key);
        while (bucketArray.get(index) != null) {
            Node<K, V> temp = bucketArray.get(index);
            if (temp.isKeyEquals(key)) {
                V res = temp.value;
                temp.setDeleted();
                return temp.getValue();
            }
            index = Math.floorMod(++index, bucketQuantity);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int requestQuantity = Integer.parseInt(reader.readLine());

            final HashTable<Integer, Integer> salaryHashTable = new HashTable<>(426389);

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
        private K key;
        public V value;
        private boolean isDeleted = false;

        public boolean isDeleted() {
            return isDeleted;
        }

        public boolean isKeyEquals(K anotherKey) {
            return key != null && key.equals(anotherKey);
        }

        public void setDeleted() {
            isDeleted = true;
            key = null;
            return;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }
    }
}