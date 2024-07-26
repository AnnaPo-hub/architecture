package sprint6.ffinal;

import java.io.*;
import java.util.*;

/*
-- ПРИНЦИП РАБОТЫ --
Используемые структуры данных :
для хранения цветов  ребер используем массив,
для хранения вершин в очереди на обработку  -  стек,
для хранения графа в виде списка смежности  - массив списков

Для начала переводим карту в граф: в цикле считываем данные из инпута и переводим в список смежных исходящих ребер для
каждой вершины ориентированного графа.

Затем задача сводится к поиску циклов в графе:  проходим граф в глубину, начиная с 1-ой вершины и, когда встречаем
покрашенную в серый вершину, то понимаем, что встретили цикл. В этом случае возвращаем ответ, что
карта выстроена не оптимально. В ином случае карта выстроена оптимально.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Задача сводится к поиску цикла в графе, по сути мы ищем путь из вершины v  в саму себя.
Если мы находим  в стеке серую вершину,  то для нее есть путь до вершины v, тк мы обходили граф, начиная из вершины v и  складывали вершины в стек.
Если из вершины v  существует путь в серую вершину v1,
то из вершины v1 существует путь до v  и из вершины v существует путь в v1 , состояший из одного ребра.
Эти  2 пути не  пересекаются, значит цикл существует.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Граф представлен списками смежности, каждую вершину посещаем 1 раз = O(V)
Также  проходимся по всем спискам смежности, то есть, проходим по каждому ребру 1 раз = O(E)
Получим, что итоговая сложность O(V+E), где V  - количество вершин, E - количество ребер.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Занимаемая память для списка смежности: O(V+E)
Также  храним массив цветов = O (V)
Итоговая сложность: O(V+E), где V  - количество вершин, E - количество ребер

--ID успешной посылки--
https://contest.yandex.ru/contest/25070/run-report/116387260/
 */
public class B {

    int[] color;
    private int cityQuantity = 0;

    public B(int cityQuantity) {
        this.cityQuantity = cityQuantity;
        color = new int[cityQuantity + 1];
        vectors = new ArrayList[cityQuantity + 1];
        for (short i = 1; i <= cityQuantity; i++) {
            vectors[i] = new ArrayList<>();
        }
    }

    ArrayList<Short>[] vectors = null;

    private List<Short> outgoingEdges(Short vertex) {
        return vectors[vertex];
    }

    private int[] resetColors() {
        int[] tempColor = color;
        color = new int[cityQuantity + 1];
        return tempColor;
    }

    //возвращает массив списков исходящих смежных вершин для ориентированного графа
    private void getEdgesData(int cityQuantity, BufferedReader reader) throws IOException {

        for (short i = 1; i < cityQuantity; i++) {
            final char[] currentLine = readList(reader);
            for (short j = 1; j <= currentLine.length; j++) {
                if (currentLine[j - 1] == 'R') {
                    vectors[i].add((short) (i + j));
                } else if (currentLine[j - 1] == 'B') {
                    vectors[j + i].add(i);
                }
            }
        }
    }

    boolean DFS(short startVertex) {
        Stack<Short> stack = new Stack<>();
        stack.push(startVertex);  // Добавляем стартовую вершину в стек.

        while (!stack.isEmpty()) {
            // Получаем из стека очередную вершину.
            // Это может быть как новая вершина, так и уже посещённая однажды.
            short v = stack.pop();

            if (color[v] == 0) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color[v] = 1;
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины
                for (short w : outgoingEdges(v)) {
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

    private static int[] mergeColors(int[] first, int[] second) {
        for (int i = 0; i < first.length; ++i) {
            first[i] += second[i];
        }
        return first;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int cityQuantity = Integer.parseInt(reader.readLine());
            B b = new B(cityQuantity);

            b.getEdgesData(cityQuantity, reader);
            short startCity = 1;
            boolean isMapOptimal;
            int[] colors = b.resetColors();

            while (true) {
                isMapOptimal = b.DFS(startCity);

                if (!isMapOptimal) break;

                colors = mergeColors(colors, b.resetColors());
                int firstNonWhite = 0;

                for (int i = 1; i <= cityQuantity; ++i) {
                    if (colors[i] == 0) {
                        firstNonWhite = i;
                        break;
                    }
                }
                if (firstNonWhite == 0) break;
                startCity = (short) firstNonWhite;
            }
            System.out.println(isMapOptimal ? "YES" : "NO");
        }
    }

    private static char[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().toCharArray();
    }
}