package sprint5.training;

public class E2 {
    public static boolean treeSolution(Node head) {
        return isSearchTree(head);
    }

    public static boolean isSearchTree(Node head) {
        if ((head.right != null && head.left != null)) {
            if (head.right.value <= head.value || head.left.value >= head.value) {
                return false;
            } else if (head.right.value > head.value && head.left.value < head.value) {
                return isSearchTree(head.right) && isSearchTree(head.left);
            }
        } else if (head.left != null) {
            if (head.left.value < head.value) {
                return isSearchTree(head.left);
            } else {
                return false;
            }

        } else if (head.right != null){
            if (head.right.value > head.value) {
                return isSearchTree(head.right);
            } else {
                return false;
            }
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

        Node node3 = new Node(1);
        Node node4 = new Node(3);

        node1.left =  node3;
        node1.right =  node4;
        Node node5 = new Node(6);
        Node node6 = new Node(9);
        node2.right =  node6;
        node2.left =  node5;
        System.out.println(treeSolution(node0));
    }
}

