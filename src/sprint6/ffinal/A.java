package sprint6.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных :
для хранения графа в виде списка смежности используем массив списков,
дополнительно используются : hashSet, приоритетная очередь

Читаем построчно инпут и инициализируем объект Graph, в котором хранится информация о вершинах
и ребрах графа.
Берем первую вершину, сохраняем ее в остов, смотрим какие ребра связаны с этой вершиной
и сохраняем их в приоритетную очередь  edges.

Берем максимальное ребро из очереди, сохраняем в остов это ребро и  вершину, которая находится на другом конце этого ребра.
Принимаем новую вершину как текущую.
Удаляем из edges ребра, обе вершины  которых уже добавлены в остов.
Далее снова вносим в edges ребра, которые связаны с текущей вершиной и берем из очереди ребро с наибольшим весом,
сохраняем его в остов.
И тд, пока не закончатся необработанные вершины.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Можем начать обработку с любой вершины, тк в итоге все вершины будут обработаны.
По каждой вершине мы возьмем все связанные ребра и сложим их в приоритетную очередь.
Соответственно, мы обработаем каждую вершину и
 на каждом шаге  возьмем из приоритетной очереди ребро с максимальным весом, получив максимальный остов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Получение максимального по весу ребра -  O(1)
Операции с ребрами (добавление/удаление) - O(log E)
Операции с узлами (добавлени) - O(1)
Проходим в алгоритме каждую вершину - O(V)
Итоговая сложность O((log E) *V), где V -количество вершин, E - количество ребер.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(V+E), где V -количество вершин, E - количество ребер.

--ID успешной посылки--
https://contest.yandex.ru/contest/25070/run-report/116292084/
     */

class Edge implements Comparable<Edge> {
    int weight;
    int start;
    int end;

    boolean startAdded = false;
    boolean endAdded = false;

    public Edge(int start, int end, int weight) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public void markEndAsAdded(int vertex) {
        if (start == vertex)
            startAdded = true;
        if (end == vertex)
            endAdded = true;
    }

    public int getEndInList(HashSet<Integer> vertices) {
        return vertices.contains(end) ? end : vertices.contains(start) ? start : null;
    }

    @Override
    public int compareTo(Edge edge) {
        return -this.weight + edge.weight;
    }

    boolean isBothEndAdded() {
        return startAdded && endAdded;
    }

    boolean isOneOrTwoEndsNotAdded() {
        return (!startAdded) || (!endAdded);
    }
}

class Graph {
    ArrayList<Edge>[] vertices;
    int edgesQuantity = 0;

    public Graph(int verticesQuantity, int edgesQuantity) {
        this.edgesQuantity = edgesQuantity;
        this.vertices = (ArrayList<Edge>[]) new ArrayList[verticesQuantity + 1];
    }
}

public class A {
    Graph graph;
    HashSet<Integer> notAddedVertices;  // Множество вершин, ещё не добавленных в остов.
    PriorityQueue<Edge> edges;//Массив рёбер, исходящих из остовного дерева.

    public A() {
        this.notAddedVertices = new HashSet<>();
        this.edges = new PriorityQueue<>();
    }

    //читает инпут, заполняет граф
    private void initializeGraph(int verticesQuantity, int edgesQuantity, BufferedReader reader) throws IOException {
        graph = new Graph(verticesQuantity, edgesQuantity);
        for (int i = 1; i <= edgesQuantity; i++) {
            final List<Integer> currentLine = readList(reader);
            final int firstVertex = currentLine.get(0);
            final int secondVertex = currentLine.get(1);
            final Integer weight = currentLine.get(2);

            final Edge edge = new Edge(firstVertex, secondVertex, weight);
            if (graph.vertices[firstVertex] == null) {
                graph.vertices[firstVertex] = new ArrayList<>();
            }
            graph.vertices[firstVertex].add(edge);

            if (graph.vertices[secondVertex] == null) {
                graph.vertices[secondVertex] = new ArrayList<>();
            }
            graph.vertices[secondVertex].add(edge);

            notAddedVertices.add(firstVertex);
            notAddedVertices.add(secondVertex);
        }
    }

    private void addVertex(int vertex) {
        //добавляем в  остов

        //убираем из недобавленных в остов
        notAddedVertices.remove(vertex);

        //Добавим ко множеству потенциально добавляемых рёбер все,
        // которые исходят из новой вершины и входят в вершины, ещё не включённые в остов

        graph.vertices[vertex].forEach(edge -> edge.markEndAsAdded(vertex));
        graph.vertices[vertex].stream().filter(Edge::isBothEndAdded).forEach(edge -> edges.remove(edge));
        graph.vertices[vertex].stream().filter(Edge::isOneOrTwoEndsNotAdded).forEach(edge -> edges.add(edge));
    }

    private Integer findMaxST(Graph graph) {
        Integer sum = 0;
        if (graph.edgesQuantity == 0 && notAddedVertices.size() > 1) {
            return null;
        } else {
            // Берём первую попавшуюся вершину
            Integer vertex = notAddedVertices.iterator().next();
            addVertex(vertex);

            while (!notAddedVertices.isEmpty() && !edges.isEmpty()) {

                Edge edge = extractMaximum();
                vertex = edge.getEndInList(notAddedVertices);
                sum += edge.weight;
                addVertex(vertex);
            }
        }
        return notAddedVertices.isEmpty() ? sum : null;
    }

    //  извлекает и возвращает  максимальное ребро из массива рёбер
    private Edge extractMaximum() {
        Edge maxWeight = edges.peek();
        edges.remove(maxWeight);
        return maxWeight;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            final List<Integer> quantity = readList(reader);
            int verticesQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            final Integer maxST;
            if (edgesQuantity > 0) {
                final A a = new A();
                a.initializeGraph(verticesQuantity, edgesQuantity, reader);
                maxST = a.findMaxST(a.graph);
            } else {
                maxST = null;
            }
            printResult(maxST, verticesQuantity);
        }
    }

    private static void printResult(Integer maxSt, int verticesQuantity) {
        if (maxSt == null) {
            System.out.println(verticesQuantity > 1 ? "Oops! I did it again" : "0");
        } else {
            System.out.println(maxSt);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}