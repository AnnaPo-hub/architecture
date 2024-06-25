package sprint5.training;

public class A {

    public static int treeSolution(Node head) {
        return head.getMax();
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

        public int getMax() {
            int max = value;
            final int leftMax = left != null ? left.getMax() : 0;
            if (leftMax > max)
                max = leftMax;
            final int rightMax = right != null ? right.getMax() : 0;
            if (rightMax != 0 && rightMax > max)
                max = rightMax;
            return max;
        }
    }
    // <template>

    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(2);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(3);
        Node node5 = new Node(0);
        Node node6 = new Node(0);
        Node node7 = new Node(111);
        Node node8 = new Node(0);
        Node node9 = new Node(111);
        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node8.right = node9;
        System.out.println(treeSolution(node0));
    }
}
