package sprint6.ffinal;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class Edge {
    int weight;
    Vertex start;
    Vertex end;

    public Edge(Vertex start, Vertex end, int weight) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }
}

class Vertex {
    int value;

    public Vertex(int value) {
        this.value = value;
    }
}

class Graph {
    List<Vertex> vertices;
    List<Edge> edges;
}

public class A {
    Graph graph;
    ArrayList<Edge> maximumSpanningTree = new ArrayList<>();
    ArrayList<Vertex> notAdded = new ArrayList<>();  // Множество вершин, ещё не добавленных в остов.
    ArrayList<Vertex> added = new ArrayList<>();// Множество вершин, уже добавленных в остов.
    ArrayList<Edge> edges = new ArrayList<>();//Массив рёбер, исходящих из остовного дерева.


    //читает инпут, заполняет граф
    private Graph initializeGraph(int edgesQuantity, BufferedReader reader) throws IOException {
        final Graph graph = new Graph();
        for (int i = 1; i <= edgesQuantity; i++) {
            final List<Integer> currentLine = readList(reader);
            final Vertex firstVertex = new Vertex(currentLine.get(0));
            final Vertex secondVertex = new Vertex(currentLine.get(1));
            final Integer weight = currentLine.get(2);

            final Edge edge = new Edge(firstVertex, secondVertex, weight);
            graph.vertices.add(firstVertex);
            graph.vertices.add(secondVertex);
            graph.edges.add(edge);
        }
        return graph;
    }

    private void addVertex(Vertex vertex) {
        added.add(vertex);
        notAdded.remove(vertex);

        edges.addAll(graph.edges.stream().filter((it) ->
                it.start == vertex && notAdded.contains(it.end)).collect(Collectors.toList()));
    }

    private List<Edge> findMaxST(Graph graph) {
        notAdded.addAll(graph.vertices);

        // Берём первую попавшуюся вершину.
        final Vertex vertex = graph.vertices.get(0);
        addVertex(vertex);

        while (!notAdded.isEmpty() && !edges.isEmpty()) {
            // Подразумеваем, что extractMaximum  извлекает максимальное ребро
            // из массива рёбер и больше данного ребра в массиве не будет
            Edge edge = extractMaximum(edges);
            if (notAdded.contains(edge.end)) {
                maximumSpanningTree.add(edge);
                addVertex(edge.end);
            }
        }

        if (!notAdded.stream().isParallel()) {
            System.out.println("Oops! I did it again");
        } else
            //TODO
            return maximumSpanningTree;
        return maximumSpanningTree;
    }

    private Edge extractMaximum(List<Edge> edges) {
        Edge maxWeight = edges.stream().max(Comparator.comparing(Edge::getWeight)).get();
        edges.remove(maxWeight);
        return maxWeight;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            final A a = new A();
            a.initializeGraph(edgesQuantity, reader);


        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
