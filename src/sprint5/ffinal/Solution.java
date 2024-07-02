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
            //есть только левый потомок
        } else if (current.getRight() == null) {
            if (current == root)
                root = current.getLeft();
            else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else parent.setRight(current.getLeft());
            //есть только правый потомок
        } else if (current.getLeft() == null) {
            if (current == root)
                root = current.getRight();
            else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        }

        else {

            //есть два потомка
            //находим замену для удаляемого
            final Node substitute = getSubstitute(current);
            if (current == root) {
                root = substitute;
            }
              else  if (isLeftChild) {
                    parent.setLeft(substitute);
                } else {
                    parent.setRight(substitute);
                }
        }
        return root;
    }


    //находит узел для замены:  самую левую вершину в правом поддереве
    // + переставляет указатели на потомков
    private static Node getSubstitute(Node node) {
        Node substitute = node;
        Node substituteParent = node;
        Node current = node.getRight();

            while (current != null) {
                substituteParent=substitute;
                substitute = current;
                current = current.getLeft();
            }

            if(substitute!= node.getRight()){
                substituteParent.setLeft(substitute.getRight());
                substitute.setRight(node.getRight());
            }
        return substitute;
    }

    public static void main(String[] args) {

        Node node5 = new Node(null, null, 3);
        Node node4 = new Node(null, null, 1);

        Node node7 = new Node(null, null, 7);
        Node node6 = new Node(null, null, 5);

        Node node3 = new Node(node6, node7, 6);
        Node node2 = new Node(node4, node5, 2);

        Node node1 = new Node(node2, node3, 4);
        Node newHead = remove(node1, 2);
        assert newHead.getValue() == 4;
        System.out.println( newHead.getLeft() == node5);
        System.out.println(newHead.getLeft().getValue() == 3);
    }
}
