package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class C {

    List<Byte> color;


    //в массиве хранятся цвета вершин , белые - мы там не были ни разу, серые были на пути "туда", "черные" были на пути "обратно
    private void initializeColor(int numVertices) { // Длина массива numVertices равна числу вершин |V|.
        color = new ArrayList<>();
        for (int i = 0; i <= numVertices; i++) {
            color.add((byte) 0);
        }
    }

    private List<Integer> outgoingEdges(Integer vertex, ArrayList<Integer>[] vectors) {
        Collections.sort(vectors[vertex], Collections.reverseOrder());
        return vectors[vertex];
    }

    //возвращает массив списков смежных вершин для неориентированного графа
    private ArrayList<Integer>[] getEdgesData(int edgesQuantity, BufferedReader reader, int vectorQuantity) throws IOException {
        ArrayList<Integer>[] vectors = new ArrayList[vectorQuantity + 1];

        for (int i = 1; i < vectors.length; i++) {
            vectors[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgesQuantity; i++) {
            final List<Integer> currentLine = readList(reader);
            final Integer firstVector = currentLine.get(0);
            final Integer secondVector = currentLine.get(1);

            if (!vectors[firstVector].contains(secondVector)) {
                vectors[firstVector].add(secondVector);
            }
            if (!vectors[secondVector].contains(firstVector)) {
                vectors[secondVector].add(firstVector);
            }
        }
        return vectors;
    }

//    //на вход получает вершину, отдает список смежных вершин для обхода
//    private List<Integer> outgoingEdges(Integer vertex) {
//        List<Integer> outgoingEdges = new ArrayList<>();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                if (matrix[vertex][j] == 1 && !outgoingEdges.contains(j)) {
//                    outgoingEdges.add(j);
//                } else if (matrix[i][vertex] == 1 && !outgoingEdges.contains(i)) {
//                    outgoingEdges.add(i);
//                }
//            }
//        }
//        Collections.sort(outgoingEdges, Collections.reverseOrder());
//        //  System.out.println(" Возвращаю смежные вершины для вершины " + vertex);
//        // outgoingEdges.forEach(System.out::print);
//        return outgoingEdges;
//    }


    void DFS(int startVertex, ArrayList<Integer>[] vectors) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();

            if (color.get(v) == 0) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color.set(v, (byte) 1);
                //System.out.println(" Печатаю вершину " + v);
                System.out.print(v + " ");
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины,
                // вместо вызова рекурсии

                for (int w : outgoingEdges(v, vectors)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color.get(w) == 0) {
                        stack.push(w);
                    }
                }
            } else if (color.get(v) == 1) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color.set(v, (byte) 2);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            C c = new C();

            c.initializeColor(vectorQuantity);

            final ArrayList<Integer>[] edgesData = c.getEdgesData(edgesQuantity, reader, vectorQuantity);
            final int startVertex = Integer.parseInt(reader.readLine());
            // System.out.println("StartVertex : " +  startVertex);

            c.DFS(startVertex, edgesData);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
