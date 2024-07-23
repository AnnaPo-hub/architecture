package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class D {

    byte[] color;
    int[] previous;
    int[] distance;

    public D(int vectorQuantity) {
        color = new byte[vectorQuantity + 1];
        previous = new int[vectorQuantity + 1];
        distance = new int[vectorQuantity + 1];
    }

    private List<Integer> outgoingEdges(Integer vertex, ArrayList<Integer>[] vectors) {
        Collections.sort(vectors[vertex]);
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
            vectors[secondVector].add(firstVector);
        }
        return vectors;
    }

    void bfs(int s, ArrayList<Integer>[] edgesData) {
        // Создадим очередь вершин и положим туда стартовую вершину.
        Queue<Integer> planned = new LinkedList<>();
        planned.add(s);
        color[s] = (int) 1;
        distance[s] = 0;

        while (!planned.isEmpty()) {
            // Получаем из очереди очередную вершину.
            int u = planned.poll();
            System.out.print(u + " ");

            final List<Integer> outgoingEdges = outgoingEdges(u, edgesData);
            for (int v : outgoingEdges) {
                // Для каждого исходящего ребра (v, w):
                if (color[v] == 0) {
                    distance[v] = distance[u] + 1;
                    previous[v] = u;
                    color[v] = (int) 1;
                    planned.add(v);  // Запланируем посещение вершины.
                }
            }
            color[u] = (int) 2;  // Теперь вершина считается обработанной.
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            D d = new D(vectorQuantity);

            final ArrayList<Integer>[] edgesData = d.getEdgesData(edgesQuantity, reader, vectorQuantity);

            final int startVertex = Integer.parseInt(reader.readLine());
            d.bfs(startVertex, edgesData);

//            //выводим результат
//            for (int i = 1; i < vectorQuantity + 1; i++) {
//                System.out.println(d.previous[i] + " " + d.distance[i]);
//            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
