package sprint2;

// <template>
// <template>
class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

// <template>

public class B {
    public static void solution(Node<String> head) {
        // Your code
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}

