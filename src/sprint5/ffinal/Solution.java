package sprint5.ffinal;

// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
// <template>

public class Solution {

    //получает на вход корень дерева и ключ
    public static Node remove(Node root, int key) {
        // Your code
        //искомый элемент - корневой , его удаление приводит к очистке дерева
        if (root.getValue() == key) {
            return null;
        }
        //текущий элемент и он же в итоге найденный искомый
        Node current = root;
        Node parent = root;

        boolean isLeftChild = false;

        while (current.getValue() != key) {
            parent = current;
            if (key > current.getValue()) {
                current = current.getRight();
            } else {
                current = current.getLeft();
                isLeftChild = true;
            }
            //искомый элемент не найден
            if (current == null) {
                return root;
            }
        }

        // нашли узел
        //первый вариант P — лист дерева, у него нет собственных детей.
        if (current.getLeft() == null && current.getRight() == null) {
            if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (current.getRight() == null) {
            if (current == root)
                root = current.getLeft();
            else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else parent.setRight(current.getLeft());

        } else if (current.getLeft() == null) {
            if (current == root)
                root = current.getRight();
            else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        }

        //есть два потомка
        //находим замену
        final Node substitute = getSubstitute(current);

        if (isLeftChild) {
            parent.setLeft(substitute);
        } else {
            parent.setRight(substitute);
        }

        return null;
    }


    //находит узел для замены:  самую правую вершину в левом поддереве или самую левую вершину в правом поддереве
    //TODO в этой точке мы знаем, что есть 2 потомка, подумать какого все-таки лучше брать потомка
    private static Node getSubstitute(Node root) {
        Node substitute = null;
        if (root.getLeft() != null) {
            substitute = root.getLeft();
            while (substitute.getRight() != null) {
                substitute = substitute.getRight();
            }
        }
//        else if (root.getRight()  !=null) {
//           substitute = root.getRight();
//            while (substitute.getRight() != null) {
//                substitute = substitute.getLeft();
//            }
//        }
        return substitute;
    }

    public static void main(String[] args) {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        System.out.println(newHead.getValue() == 5);
        System.out.println(newHead.getRight() == node5);
        System.out.println(newHead.getRight().getValue() == 8);
    }
}

