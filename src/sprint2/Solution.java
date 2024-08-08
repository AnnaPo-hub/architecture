package sprint2;

public class Solution {
    //    public static int solution(Node<String> head, String elem) {
//        // Your code
//        int indexCount = 0;
//        while (head != null) {
//            if (head.value.equals(elem)) {
//                return indexCount;
//            } else {
//                ++indexCount;
//                head = head.next;
//            }
//        }
//        return -1;
//    }
    public static Node<String> solution(Node<String> head) {
        // Your code

        Node<String> result = null;
        while (head != null) {
            result = head;
            swapNode(head);
            head = head.prev;
        }
        return result;
    }

    private static void swapNode(Node<String> node) {
        Node<String> tmpNode;
        tmpNode = node.next;
        node.next = node.prev;
        node.prev = tmpNode;
    }

    public static void main(String[] args) {
//        Node<String> node3 = new Node<>("node3", null);
//        Node<String> node2 = new Node<>("node2", node3);
//        Node<String> node1 = new Node<>("node1", node2);
//        Node<String> node0 = new Node<>("node0", node1);
//        int idx = solution(node0, "node0");
//        assert idx == 0;

        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node<String> newNode = solution(node0);
        /* result is :*/
        assert newNode == node3;
        assert node3.next == node2;
        assert node2.next == node1;
        assert node2.prev == node3;
        assert node1.next == node0;
        assert node1.prev == node2;
        assert node0.prev == node1;
    }
}



