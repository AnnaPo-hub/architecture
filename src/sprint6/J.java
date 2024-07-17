package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class J {

    List<Byte> color;

    Stack<Integer> order;  // В этом стеке будет записан порядок обхода
    Stack<Integer> single;  // В этом стеке будут записаны  вершины, у которых нет смежных вершин


    public J() {
        order = new Stack<>();
        single = new Stack<>();
    }

    //в массиве хранятся цвета вершин , белые (0) - мы там не были ни разу, серые(1) -  были на пути туда, черные (2) -  были на пути обратно
    void initializeColor(int numVertices) { // Длина массива numVertices равна числу вершин |V|.
        color = new ArrayList<>();

        for (int i = 0; i <= numVertices; i++) {
            color.add((byte) 0);
        }
    }

    private List<Integer> outgoingEdges(Integer vertex, ArrayList<Integer>[] vectors) {
        if (vectors[vertex] != null) {
            Collections.sort(vectors[vertex], Collections.reverseOrder());
            return vectors[vertex];
        }
      return new ArrayList<>();
    }

    //возвращает массив списков смежных вершин для неориентированного графа
    ArrayList<Integer>[] getEdgesData(int edgesQuantity, BufferedReader reader, int vectorQuantity) throws IOException {
        ArrayList<Integer>[] vectors = new ArrayList[vectorQuantity + 1];

        //TODO  убрать эту инициализацию?
//        for (int i = 1; i < vectors.length; i++) {
//            vectors[i] = new ArrayList<>();
//        }

        //массив для сохранения вершин в которые есть входяшие ребра
        ArrayList<Integer> ingoing = new ArrayList<>();

        for (int i = 0; i < edgesQuantity; i++) {
            final List<Integer> currentLine = readList(reader);
            final Integer firstVector = currentLine.get(0);
            final Integer secondVector = currentLine.get(1);
            if (vectors[firstVector] == null) {
                vectors[firstVector] = new ArrayList<>();
            }
            vectors[firstVector].add(secondVector);
            ingoing.add(secondVector);
        }

        vectors[0] = ingoing;
        return vectors;
    }

    void DFS(int startVertex, ArrayList<Integer>[] vectors) {
        final List<Integer> startOutgoing = outgoingEdges(startVertex, vectors);

        if (startOutgoing == null || (startOutgoing.isEmpty() && !vectors[0].contains(startVertex))) {
            //эта вершина не имеет смежных вершин
            color.set(startVertex, (byte) 2);
            single.push(startVertex);
        } else {
            Stack<Integer> stack = new Stack<>();
            stack.push(startVertex);  // Добавляем стартовую вершину в стек.

            while (!stack.isEmpty()) {  // Пока стек не пуст:
                // Получаем из стека очередную вершину.
                // Это может быть как новая вершина, так и уже посещённая однажды.
                int v = stack.pop();

                final List<Integer> outgoing = outgoingEdges(v, vectors);

                if (color.get(v) == 0) {
                    // Красим вершину в серый. И сразу кладём её обратно в стек:
                    // это позволит алгоритму позднее вспомнить обратный путь по графу.
                    color.set(v, (byte) 1);
                    //System.out.println(" Печатаю вершину " + v);
                    //   System.out.print(v + " ");
                    stack.push(v);

                    // Теперь добавляем в стек все непосещённые соседние вершины,
                    // вместо вызова рекурсии

                    for (int w : outgoing) {
                        // Для каждого исходящего ребра (v, w):
                        if (color.get(w) == 0) {
                            stack.push(w);
                        }
                    }
                } else if (color.get(v) == 1) {
                    // Серую вершину мы могли получить из стека только на обратном пути.
                    // Следовательно, её следует перекрасить в чёрный.
                    color.set(v, (byte) 2);
                    order.push(v);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            final List<Integer> quantity = readList(reader);
            int vectorQuantity = quantity.get(0);
            int edgesQuantity = quantity.get(1);

            J j = new J();

            final ArrayList<Integer>[] edgesData = j.getEdgesData(edgesQuantity, reader, vectorQuantity);

            j.initializeColor(vectorQuantity);


            for (int i = 1; i < j.color.size(); i++) {
                // Перебираем варианты стартовых вершин, пока они существуют.
                if (j.color.get(i) == 0) {
                    j.DFS(i, edgesData); // Запускаем обход, стартуя с i-й вершины.
                }
            }

            for (int i = j.single.size(); i > 0; i--) {
                System.out.print(j.single.pop() + " ");
            }

            for (int i = j.order.size(); i > 0; i--) {
                System.out.print(j.order.pop() + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
