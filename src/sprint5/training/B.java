package sprint5.training;

public class B {
    public static boolean treeSolution(Node head) {
        return isBalanced(head);
    }

    public static boolean isBalanced(Node head) {
        if (head.right != null && head.left != null) {
            return isBalanced(head.right) && isBalanced(head.left);
        } else if (head.left != null) {
            return !hasChildren(head.left);
        } else if (head.right != null) {
            return !hasChildren(head.right);
        } else {
            return true;
        }
    }

    public static boolean hasChildren(Node head) {
        return head.left != null || head.right != null;
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
        // <template>
    }

    private static void test() {
        Node node0 = new Node(1);
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
    }
}

