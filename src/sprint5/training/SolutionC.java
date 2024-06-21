package sprint5.training;

public class SolutionC {
    public static boolean treeSolution(Node head) {
        if (head.left != null && head.right != null) {
            return compareTrees(head.left, head.right);
        } if (head.left==null&&head.right!=null||head.left!=null&&head.right==null){
            return false;
        }
        return true;
    }

    private static boolean compareTrees(Node head1, Node head2) {
        if (head1.value != head2.value) {
            return false;
        }
        if (doNotHaveChildren(head1, head2)) {
            return true;
        } else if (haveTwoChildren(head1, head2)) {
            return compareTrees(head1.right, head2.left) && compareTrees(head1.left, head2.right);
        } else if (head1hasLeftChild(head1, head2)) {
            return compareTrees(head1.left, head2.right);
        } else if (head1hasRightChild(head1, head2)) {
            return compareTrees(head1.right, head2.left);
        }
        return false;
    }

    private static boolean haveTwoChildren(Node head1, Node head2) {
        return head1.left != null && head1.right != null && head2.left != null && head2.right != null;
    }

    private static boolean doNotHaveChildren(Node head1, Node head2) {
        return head1.left == null && head1.right == null && head2.left == null && head2.right == null;
    }

    private static boolean head1hasLeftChild(Node head1, Node head2) {
        return head1.left != null && head1.right == null && head2.left == null && head2.right != null;
    }

    private static boolean head1hasRightChild(Node head1, Node head2) {
        return head1.left == null && head1.right != null && head2.left != null && head2.right == null;
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
        Node node2 = new Node(3, null, null);
        Node node3 = new Node(3, null, null);
        Node node1 = new Node(2, node2, node3);
        Node node0 = new Node(1, null, node1);
        System.out.println(treeSolution(node0));
     //   assert treeSolution(node0);
    }
}
