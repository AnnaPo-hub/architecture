package sprint5.training;

public class SolutionD {
    public static boolean treeSolution(Node head1, Node head2) {
        // Your code
     return    compareTrees(head1, head2);
    }

    private static boolean compareTrees(Node head1, Node head2) {
        if(head1.value!= head2.value){
            return false;
        }
        if (doNotHaveChildren(head1, head2)) {
            return true;
        } else if (haveTwoSameChildren(head1, head2)) {
            compareTrees(head1.right, head2.right);
            compareTrees(head1.left, head2.left);
        } else if (haveLeftSameChildren(head1, head2)) {
            compareTrees(head1.left, head2.left);
        } else if (haveRightSameChildren(head1, head2)) {
            compareTrees(head1.right, head2.right);
        }
        return true;
    }

    private static boolean haveTwoSameChildren(Node head1, Node head2) {
        return head1.left != null && head1.right != null && head2.left != null && head2.right != null;
    }

    private static boolean doNotHaveChildren(Node head1, Node head2) {
        return head1.left == null && head1.right == null && head2.left == null && head2.right == null;
    }

    private static boolean haveLeftSameChildren(Node head1, Node head2) {
        return head1.left != null && head1.right == null && head2.left != null && head2.right == null;
    }

    private static boolean haveRightSameChildren(Node head1, Node head2) {
        return head1.left == null && head1.right != null && head2.left == null && head2.right != null;
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
        //Node node2 = new Node(3, null, null);
        Node node1 = new Node(1, null, null);
        Node node0 = new Node(0, node1, null);


      //  Node node5 = new Node(3, null, null);
        Node node4 = new Node(1, null, null);
        Node node3 = new Node(0, node4, null);
        System.out.println(treeSolution(node3, node0));
        //     assert treeSolution(node3, node6);
    }
}
