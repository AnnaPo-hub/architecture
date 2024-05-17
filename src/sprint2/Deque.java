package sprint2;

import java.io.*;
 /*
    -- ПРИНЦИП РАБОТЫ --
     Используемая структура данных : массив
     Массив используется в виде кольцевого буфера. С помощью указателей на элементы массива
      представляются ссылки на конец и начало дек. Чтобы добавить , мы сохраняем элемент в ту ячейку
      куда указывает указатель начала дека, а указатель передвигаем вперед. При удалении элемента, сохраняем null в ячейку,
       которая указывет на конец дека, указатель передвигаем назад.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
push_front: Элемент будет добавлен в начало дэка, тк указатель показывает на начало массива и
 передвигается вперед  при добавлении элементов
pop_front: Элемент будет извлечен из начала  дэка, тк указатель показывает на начало массива и
 передвигается  при извлечении элементов.  Если в начале  дека элементов не было элементов,
  то указатель  перескочит на элементы из конца дэка
push_back: Элемент будет добавлен в конец дэка, тк указатель показывает на конец массива и
 передвигается  назад при добавлении элементов.
pop_back: Элемент будет извлечен из конца дэка, тк указатель показывает на конец массива и
 передвигается  при извлечении элементов.  Если в конце  дека элементов не было элементов,
  то указатель  перескочит на элементы из начала дэка

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность O(n),  где  n- это длина входной строки.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Для массива с n элементами требуется O(n)  памяти, так как она нужна только для хранения самих элементов массива.
При этом требуемый размер дека нам приходит на вход.

--ID успешной посылки--
https://contest.yandex.ru/contest/22781/run-report/113139973/
     */

public class Deque {
    private Integer[] deque;
    private int head;
    private int tail;
    private int max_n;
    private int size;

    public Deque(int n) {
        deque = new Integer[n];
        head = 0;
        tail = n - 1;
        max_n = n;
        size = 0;
    }

    public void push_front(int x) {
        if (size != max_n) {
            deque[head] = x;
            head = (head + 1) % max_n;
            size++;
        } else {
            System.out.println("error");
        }
    }

    public String pop_front() {
        if (size == 0) {
            return "error";
        } else {
            head = (head - 1 + max_n) % max_n;
            Integer x = deque[head];
            deque[head] = null;
            size--;
            return x.toString();
        }
    }

    public void push_back(int x) {
        if (size != max_n) {
            deque[tail] = x;
            tail = (tail - 1 + max_n) % max_n;
            size++;
        } else {
            System.out.println("error");
        }
    }

    public String pop_back() {
        if (size == 0) {
            return "error";
        } else {
            tail = (tail + 1) % max_n;
            Integer x = deque[tail];
            deque[tail] = null;
            size--;
            return x.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());
            final int max_n = Integer.parseInt(reader.readLine());

            Deque deque1 = new Deque(max_n);
            for (int i = 0; i < n; i++) {
                final String[] command = reader.readLine().trim().split(" ");

                switch (command[0]) {
                    case "push_front":
                        deque1.push_front(Integer.parseInt(command[1]));
                        break;
                    case "push_back":
                        deque1.push_back(Integer.parseInt(command[1]));
                        break;
                    case "pop_front":
                        System.out.println(deque1.pop_front());
                        break;
                    case "pop_back":
                        System.out.println(deque1.pop_back());
                        break;
                }
            }
        }
    }
}