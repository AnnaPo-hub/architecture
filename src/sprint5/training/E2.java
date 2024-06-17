package sprint5.training;

public class E2 {
    public static boolean treeSolution(Node head) {
        return isSearchTree(head);
    }

    public static boolean isSearchTree(Node head) {
        if ((head.right != null && head.left != null)) {
            if (head.right.value <= head.value || head.left.value >= head.value) {
                return false;
            } else if (head.right.value >= head.value && head.left.value <= head.value) {
                return isSearchTree(head.right) && isSearchTree(head.left);
            }
        } else if (head.left != null && head.left.value <= head.value) {
            return isSearchTree(head.left);
        } else if (head.right != null && head.right.value >= head.value) {
            return isSearchTree(head.right);
        }
        return true;
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

    public static void main(String[] args) {
        Node node0 = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(8);
        node0.left = node1;
        node0.right = node2;
        System.out.println(treeSolution(node0));
    }
}

