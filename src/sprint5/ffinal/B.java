package sprint5.ffinal;

/*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : бинарное дерево поиска

Получаем на вход ключ, с помощью бинарного поиска находим ноду по ключу.
Далее, если требуется, то перестраиваем дерево, чтобы оно оставалось правильным деревом поиска:
Определяем есть ли у ноды потомки и какие (оба/левый/правый).  Если есть левый/правый потомок, то меняем
указатели родителя удаляемой ноды на левого/правого потомка.

Если у дерева  есть  оба потомка, то требуется перестройка дерева,
тк не можем просто заменить удаляемую вершину одним из потомков, особенно  в случае если у потомков есть свои потомки.
В этом случае для перестройки дерева находим замену - самого левого потомка в правом поддереве.
И меняем указатели в родителе удаляемой ноды, чтобы замена встала на место удаляемой ноды.

Если потомков нет, то заменяем соответствующий указатель в родителе ноды для удаления на null.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
По условиям задачи мы считаем, что на  на вход подано корректное бинарное дерево поиска.
Считаем, что бинарный поиск априори работает корректно.
При удалении узла корня поддерева мы  берем  в качестве замены узла самую левую вершину в правом поддереве,
в этом случае мы  можем быть уверены, что значение этой ноды точно не меньше,
чем любое значение левого поддерева и мы не поломаем дерево, сделав замену вершин.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(h), где h - высота дерева

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n), где n - количество узлов дерева, которое  получаем на вход в качестве параметра

--ID успешной посылки--
https://contest.yandex.ru/contest/24810/run-report/115781858/
 */
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

public class B {

    //получает на вход корень дерева и ключ
    public static Node remove(Node root, int key) {
        //текущий элемент и он же в итоге найденный искомый
        Node current = root;
        Node parent = root;

        boolean isLeftChild = false;

        if (current != null) {
            while (current.getValue() != key) {
                parent = current;
                if (key > current.getValue()) {
                    current = current.getRight();
                    isLeftChild = false;
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
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
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
            } else {
                //есть два потомка
                //находим замену для удаляемого
                final Node substitute = getSubstitute(current);
                if (current == root) {
                    root = substitute;
                } else if (isLeftChild) {
                    parent.setLeft(substitute);
                } else {
                    parent.setRight(substitute);
                }
                substitute.setLeft(current.getLeft());
            }
        }
        return root;
    }

    //находит узел для замены:  самую левую вершину в правом поддереве
    // и переставляет указатели на потомков
    private static Node getSubstitute(Node node) {
        Node substitute = node;
        Node substituteParent = node;
        Node current = node.getRight();

        while (current != null) {
            substituteParent = substitute;
            substitute = current;
            current = current.getLeft();
        }

        if (substitute != node.getRight()) {
            substituteParent.setLeft(substitute.getRight());
            substitute.setRight(node.getRight());
        }
        return substitute;
    }

    public static void test() {

        Node node5 = new Node(null, null, 3);
        Node node4 = new Node(null, null, 1);

        Node node7 = new Node(null, null, 7);
        Node node6 = new Node(null, null, 5);

        Node node3 = new Node(node6, node7, 6);
        Node node2 = new Node(node4, node5, 2);

        Node node1 = new Node(node2, node3, 4);
        Node newHead = remove(null, 4);
//        assert newHead.getValue() == 4;

    }
}
