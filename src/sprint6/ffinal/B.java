package sprint6.ffinal;

import java.io.*;
import java.util.*;

public class B {

    byte[] color;

    public B(int cityQuantity) {
        color = new byte[cityQuantity + 1];
    }

    private List<Integer> outgoingEdges(Integer vertex, ArrayList<Integer>[] vectors) {
        Collections.sort(vectors[vertex], Collections.reverseOrder());
        return vectors[vertex];
    }

    //возвращает массив списков смежных вершин для ориентированного графа
    private ArrayList<Integer>[] getEdgesData(int cityQuantity, BufferedReader reader) throws IOException {
        ArrayList<Integer>[] vectors = new ArrayList[cityQuantity + 1];

        for (int i = 1; i < vectors.length; i++) {
            vectors[i] = new ArrayList<>();
        }

        for (int i = 1; i < cityQuantity; i++) {
            final String[] currentLine = readList(reader);
            for (int j = 1; j <= currentLine.length; j++) {
                if (currentLine[j - 1].equals("R")) {
                    vectors[i].add(j);
                } else if (currentLine[j - 1].equals("B")) {
                    vectors[j + 1].add(i);
                }
            }
        }
        return vectors;
    }

    boolean DFS(int startVertex, ArrayList<Integer>[] vectors) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();

            if (color[v] == 0) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color[v] = 1;
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины
                for (int w : outgoingEdges(v, vectors)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color[w] == 0) {
                        stack.push(w);
                    } else if (color[w] == 1) {
                        return false;
                    }

                }
            } else if (color[v] == 1) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color[v] = 2;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int cityQuantity = Integer.parseInt(reader.readLine());
            B b = new B(cityQuantity);

            final ArrayList<Integer>[] edgesData = b.getEdgesData(cityQuantity, reader);
            int startCity = 1;

            final boolean isMapOptimal = b.DFS(startCity, edgesData);
            System.out.println(isMapOptimal ? "YES" : "NO");
        }
    }

    private static String[] readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split("")).toArray(String[]::new);
    }
}