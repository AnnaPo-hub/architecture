package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class C {

    List<String> color;
    int[][] matrix;

    public C(int vectorQuantity) {
        this.matrix = new int[vectorQuantity + 1][vectorQuantity + 1];
        for (int i = 1; i < vectorQuantity + 1; i++) {
            for (int j = 1; j < vectorQuantity + 1; j++) {
                matrix[i][j] = 0;
            }
        }
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

    private void initializeColor(int numVertices) { // Длина массива numVertices равна числу вершин |V|.
        color = new ArrayList<>();
        for (int i = 0; i <= numVertices; i++) {
            color.add("white");
        }
    }

    private List<Integer> outgoingEdges(Integer vertex) {
        List<Integer> outgoingEdges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[vertex][j] == 1 && !outgoingEdges.contains(j)) {
                    outgoingEdges.add(j);
                } else if (matrix[i][vertex] == 1 && !outgoingEdges.contains(i)) {
                    outgoingEdges.add(i);
                }
            }
        }
        Collections.sort(outgoingEdges);
        Collections.reverse(outgoingEdges);
        //  System.out.println(" Возвращаю смежные вершины для вершины " + vertex);
        // outgoingEdges.forEach(System.out::print);
        return outgoingEdges;
    }

//    private void mainDFS() {
//        for (int i = 0; i < color.size(); i++) {
//            // Перебираем варианты стартовых вершин, пока они существуют.
//            if (color.get(i).equals("white")) {
//                DFS(i); // Запускаем обход, стартуя с i-й вершины.
//            }
//        }
//    }

    void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();

            if (color.get(v).equals("white")) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color.set(v, "gray");
                //System.out.println(" Печатаю вершину " + v);
                System.out.print(v + " ");
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины,
                // вместо вызова рекурсии

                for (int w : outgoingEdges(v)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color.get(w).equals("white")) {
                        stack.push(w);
                    }
                }
            } else if (color.get(v).equals("gray")) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color.set(v, "black");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            C c = new C(vectorQuantity);

            c.initializeMatrix(edgesQuantity, reader);

            final int startVertex = Integer.parseInt(reader.readLine());
            // System.out.println("StartVertex : " +  startVertex);
            c.initializeColor(vectorQuantity);

            c.DFS(startVertex);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
