package sprint5.training;

public class F {
    public static int treeSolution(Node head) {
        return getLongestWay(head);
    }

    public static int getLongestWay(Node head) {
        if (head.left == null && head.right == null) {
            return 1;

        } else if (head.left != null && head.right == null) {
            return 1 + getLongestWay(head.left);
        } else if (head.left == null && head.right != null) {
            return 1 + getLongestWay(head.right);
        } else {
            return 1 + Math.max(getLongestWay(head.left), getLongestWay(head.right));
        }
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }
    // <template>

    public static void main(String[] args) {
        Node node3 = new Node(1, null, null);
        Node node2 = new Node(2, node3, null);
        Node node1 = new Node(1, node2, null);

        System.out.println(treeSolution(node1));
    }
}