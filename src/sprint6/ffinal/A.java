package sprint6.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;
        return this.value == v.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

class Graph {
    HashSet<Vertex> vertices;
    HashSet<Edge> edges;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }


}

public class A {
    Graph graph;
    ArrayList<Edge> maximumSpanningTree;
    ArrayList<Vertex> notAddedVertices;  // Множество вершин, ещё не добавленных в остов.
    ArrayList<Vertex> addedVertices;// Множество вершин, уже добавленных в остов.
    ArrayList<Edge> edges;//Массив рёбер, исходящих из остовного дерева.

    public A() {
        this.maximumSpanningTree = new ArrayList<>();
        this.notAddedVertices = new ArrayList<>();
        this.addedVertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

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
        //добавляем в  остов
        addedVertices.add(vertex);
        //убираем из недобавленных в остов
        notAddedVertices.remove(vertex);

        //Добавим ко множеству потенциально добавляемых рёбер все,
        // которые исходят из новой вершины и входят в вершины, ещё не включённые в остов
        for (Edge edge : graph.edges) {
            if (edge.start.equals(vertex) && notAddedVertices.contains(edge.end)) {
                edges.add(edge);
            }
        }
    }

    private List<Edge> findMaxST(Graph graph) {
        notAddedVertices.addAll(graph.vertices);

        // Берём первую попавшуюся вершину
        final Vertex vertex = graph.vertices.iterator().next();
        addVertex(vertex);

        while (!notAddedVertices.isEmpty() && !edges.isEmpty()) {
            // Подразумеваем, что extractMaximum  извлекает максимальное ребро
            // из массива рёбер и больше данного ребра в массиве не будет
            Edge edge = extractMaximum(edges);
            if (notAddedVertices.contains(edge.end)) {
                maximumSpanningTree.add(edge);
                addVertex(edge.end);
            }
        }

        if (!notAddedVertices.isEmpty()) {
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
            a.graph = a.initializeGraph(edgesQuantity, reader);

            final List<Edge> maxST = a.findMaxST(a.graph);

            int sum = 0;
            for (Edge edge : maxST) {
                sum += edge.weight;
            }
            System.out.println(sum);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
