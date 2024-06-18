package sprint5.training;

public class F {
    public static int treeSolution(Node head) {
        return getLongestWay(head);
    }

    public static int getLongestWay(Node head) {
        int longestWay = 1;
        if(head.right!=null&&head.left!=null){
            longestWay++;
            getLongestWay(head.right);
            getLongestWay(head.left);
        }

        if(head.right!=null&&head.left==null){
            longestWay++;
            getLongestWay(head.right);
        }
        if(head.left!=null&&head.right==null){
            longestWay++;
            getLongestWay(head.left);
        }
        return ++longestWay;
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
        Node node3 = new Node(2, null, null);

        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, node3);
        Node node0 = new Node(1, node1, node2);

        System.out.println(treeSolution(node0));
    }
}