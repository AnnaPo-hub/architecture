package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
Вам дан неориентированный граф. Найдите его компоненты связности.

Формат ввода
В первой строке дано количество вершин n (1≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 2 ⋅ 105). В каждой из следующих m строк записано по ребру в виде пары вершин 1 ≤ u, v ≤ n.

Гарантируется, что в графе нет петель и кратных рёбер.

Формат вывода
Выведите все компоненты связности в следующем формате: в первой строке выведите общее количество компонент.

Затем на отдельных строках выведите вершины каждой компоненты, отсортированные по возрастанию номеров. Компоненты между собой упорядочивайте по номеру первой вершины.

Пример 1
Ввод	Вывод
6 3
1 2
6 5
2 3


3
1 2 3
4
5 6

 */

public class E {

    byte[] color;
    byte componentCount = 1;

    //в массиве хранятся цвета вершин , вершины одного цвета принадлежат к одной компоненте связности
    void initializeColor(int numVertices) { // Длина массива numVertices равна числу вершин |V|.
        color = new byte[numVertices + 1];
        for (int i = 0; i <= numVertices; i++) {
            color[i] = -1;
        }
    }

    private List<Integer> outgoingEdges(Integer vertex, ArrayList<Integer>[] vectors) {
        Collections.sort(vectors[vertex], Collections.reverseOrder());
        return vectors[vertex];
    }

    //возвращает массив списков смежных вершин для неориентированного графа
    ArrayList<Integer>[] getEdgesData(int edgesQuantity, BufferedReader reader, int vectorQuantity) throws IOException {
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


    void DFS(int startVertex, ArrayList<Integer>[] vectors) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {  // Пока стек не пуст:
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            int v = stack.pop();

            if (color[v] == -1) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color[v] = ((byte) 1);
                //System.out.println(" Печатаю вершину " + v);
                // System.out.print(v + " ");
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины,
                // вместо вызова рекурсии

                for (int w : outgoingEdges(v, vectors)) {
                    // Для каждого исходящего ребра (v, w):
                    if (color[w] == -1) {
                        stack.push(w);
                    }
                }
            } else if (color[v] == 1) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color[v] = componentCount;
            }
        }
        componentCount += 1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            E e = new E();

            e.initializeColor(vectorQuantity);

            final ArrayList<Integer>[] edgesData = e.getEdgesData(edgesQuantity, reader, vectorQuantity);


            for (int i = 1; i < e.color.length; i++) {
                // Перебираем варианты стартовых вершин, пока они существуют.
                if (e.color[i] == -1) {
                    e.DFS(i, edgesData); // Запускаем обход, стартуя с i-й вершины.
                }
            }

            System.out.println(e.componentCount-1);
            for (int j = 1; j < e.color.length; j++) {
                for (int i = 1; i < e.color.length; i++) {
                    if (e.color[i] == j) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
