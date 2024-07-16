package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class H {

    byte[] color;
    int time = 0;
    int[] entry;
    int[] leave;

    public H(int vectorQuantity) {
        color = new byte[vectorQuantity + 1];
        entry = new int[vectorQuantity + 1];
        leave = new int[vectorQuantity + 1];
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

            vectors[firstVector].add(secondVector);
        }
        return vectors;
    }

    void DFS(int startVertex, ArrayList<Integer>[] vectors) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();
            //   System.out.print(time + " ");


            if (color[v] == 0) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                entry[v] = time;
                color[v] = 1;
                time += 1;
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины

                for (int w : outgoingEdges(v, vectors)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color[w] == 0) {
                        stack.push(w);
                    }
                }
            } else if (color[v] == 1) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                // Перед выходом из вершины время снова обновляется.
                leave[v] = time;  // Запишем время выхода.
                color[v] = 2;
                time += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            H h = new H(vectorQuantity);

            final ArrayList<Integer>[] edgesData = h.getEdgesData(edgesQuantity, reader, vectorQuantity);

            h.DFS(1, edgesData);

            //выводим результат
            for (int i = 1; i < vectorQuantity + 1; i++) {
                System.out.println(h.entry[i] + " " + h.leave[i]);
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
