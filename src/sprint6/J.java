package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class J {

    List<Byte> color;
    int[][] matrix;

    Stack<Integer> order;  // В этом стеке будет записан порядок обхода
    Stack<Integer> single;  // В этом стеке будут записаны  вершин, у которых нет смежных вершин


    public J(int vectorQuantity) {
        this.matrix = new int[vectorQuantity + 1][vectorQuantity + 1];
        for (int i = 1; i < vectorQuantity + 1; i++) {
            for (int j = 1; j < vectorQuantity + 1; j++) {
                matrix[i][j] = 0;
            }
        }
        order = new Stack<>();
        single = new Stack<>();
    }

    //инициализирует матрицу
    private void initializeMatrix(int edgesQuantity, BufferedReader reader) throws IOException {
        for (int i = 1; i <= edgesQuantity; i++) {
            final List<Integer> currentLine = readList(reader);
            final Integer firstVector = currentLine.get(0);
            final Integer secondVector = currentLine.get(1);
            matrix[firstVector][secondVector] = 1;
        }
    }

    //в массиве хранятся цвета вершин , белые - мы там не были ни разу, серые были на пути "туда", "черные" были на пути "обратно
    void initializeColor(int numVertices) { // Длина массива numVertices равна числу вершин |V|.
        color = new ArrayList<>();

        for (int i = 0; i <= numVertices; i++) {
            color.add((byte) 0);
        }

    }

//    private List<Integer> outgoingEdges(Integer vertex, ArrayList<Integer>[] vectors) {
//        Collections.sort(vectors[vertex], Collections.reverseOrder());
//        return vectors[vertex];
//    }

    private List<Integer> outgoingEdges(Integer vertex) {
        List<Integer> outgoingEdges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[vertex][j] == 1 && !outgoingEdges.contains(j)) {
                    outgoingEdges.add(j);
                }
            }
        }
        Collections.sort(outgoingEdges, Collections.reverseOrder());
        // System.out.println(" Возвращаю  исходящие смежные вершины для вершины " + vertex);
        //  outgoingEdges.forEach(System.out::print);
        return outgoingEdges;
    }

    //возвращает входящие ребра
    private List<Integer> ingoingEdges(Integer vertex) {
        List<Integer> ingoingEdges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][vertex] == 1 && !ingoingEdges.contains(i)) {
                    ingoingEdges.add(i);
                }
            }
        }
        Collections.sort(ingoingEdges, Collections.reverseOrder());
        // System.out.println(" Возвращаю входящие смежные вершины для вершины " + vertex);
        //  ingoingEdges.forEach(System.out::print);
        return ingoingEdges;
    }

    private boolean hasIncomingNeigbors(int vector, ArrayList<Integer>[] vectors) {
        return true;
    }


    void DFS(int startVertex) {

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.


        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();

            final List<Integer> outgoing = outgoingEdges(v);
            final List<Integer> incoming = ingoingEdges(v);
            if (incoming.isEmpty() && outgoing.isEmpty()) {
                //эта вершина не имеет смежных вершин
                color.set(v, (byte) 2);
                single.push(v);
            } else {
                if (color.get(v) == 0) {
                    // Красим вершину в серый. И сразу кладём её обратно в стек:
                    // это позволит алгоритму позднее вспомнить обратный путь по графу.
                    color.set(v, (byte) 1);
                    //System.out.println(" Печатаю вершину " + v);
                    //   System.out.print(v + " ");
                    stack.push(v);

                    // Теперь добавляем в стек все непосещённые соседние вершины,
                    // вместо вызова рекурсии

                    for (int w : outgoing) {
                        // Для каждого исходящего ребра (v, w):
                        if (color.get(w) == 0) {
                            stack.push(w);
                        }
                    }
                } else if (color.get(v) == 1) {
                    // Серую вершину мы могли получить из стека только на обратном пути.
                    // Следовательно, её следует перекрасить в чёрный.
                    color.set(v, (byte) 2);
                    //   leave[v] = time;// Запишем время выхода.
                    order.push(v);
                    //    time += 1;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            J c = new J(vectorQuantity);
            c.initializeMatrix(edgesQuantity, reader);

            c.initializeColor(vectorQuantity);


            for (int i = 1; i < c.color.size(); i++) {
                // Перебираем варианты стартовых вершин, пока они существуют.
                if (c.color.get(i) == 0) {
                    c.DFS(i); // Запускаем обход, стартуя с i-й вершины.
                }
            }

            for (int i = c.single.size(); i > 0; i--) {
                System.out.print(c.single.pop() + " ");
            }

            for (int i = c.order.size(); i > 0; i--) {
                System.out.print(c.order.pop() + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
