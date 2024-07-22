package sprint6.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных : hashSet

Читаем построчно инпут и инициализируем объект Graph, в котором хранится информация о вершинах
и ребрах графа.
Берем первую вершину, сохраняем ее в остов, смотрим какие ребра связаны с этой вершиной
и сохраняем их в кучу edges с поддержкой максимума.

Берем верхнее ребро из кучи, сохраняем в остов это ребро и  вершину, которая находится на другом конце этого ребра.
Принимаем новую вершину как текущую.
Далее снова вносим в edges ребра, которые связаны с текущей вершиной и берем сверху кучи ребро с наибольшим весом,
сохраняем его в остов.
И тд, пока не закончатся необработанные вершины.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Можем начать обработку с любой вершины, тк в итоге все вершины будут обработаны.
По каждой вершине мы возьмем все связанные ребра и сложим их в кучу с поддержкой максимума.
Соответственно, мы обработаем каждую вершину и
 на каждом шаге  возьмем из кучи ребро с максимальным весом.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Получение максимального по весу ребра -  O(log V)
Итоговая сложность O((log V) *E), где V -количество вершин, E - количество ребер.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(V+E), где V -количество вершин, E - количество ребер.

--ID успешной посылки--

     */

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
        if (graph.edges.isEmpty() && notAddedVertices.size() > 1) {
            return null;
        } else {

            notAddedVertices.addAll(graph.vertices);

            // Берём первую попавшуюся вершину
            final Vertex vertex = graph.vertices.iterator().next();
            addVertex(vertex);

            while (!notAddedVertices.isEmpty() && !edges.isEmpty()) {
                Edge edge = extractMaximum(edges);
                if (notAddedVertices.contains(edge.end)) {
                    maximumSpanningTree.add(edge);
                    addVertex(edge.end);
                }
            }
        }
        return maximumSpanningTree;
    }


    //  извлекает и возвращает  максимальное ребро из массива рёбер
    private Edge extractMaximum(List<Edge> edges) {
        Edge maxWeight = edges.stream().max(Comparator.comparing(Edge::getWeight)).get();
        edges.remove(maxWeight);
        return maxWeight;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            final List<Integer> quantity = readList(reader);
            int verticesQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            final List<Edge> maxST;
            if (edgesQuantity > 0) {
                final A a = new A();
                a.graph = a.initializeGraph(edgesQuantity, reader);
                maxST = a.findMaxST(a.graph);
            } else {
                maxST = null;
            }
            printResult(maxST, verticesQuantity);
        }
    }

    private static void printResult(List<Edge> maxST, int verticesQuantity) {
        if (maxST == null) {
            System.out.println(verticesQuantity > 1 ? "Oops! I did it again" : "0");
        } else {
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