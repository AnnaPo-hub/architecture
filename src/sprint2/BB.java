package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BB {
    /*
    -- ПРИНЦИП РАБОТЫ --
     Используемая структура данных : стек.
     Для решения задачи входящая строка преобразована в лист строк.
     Далее проходим по каждому элементу листа. Если это операнд, то кладем элемент на вершину стека.
     Если это знак операции, то выполняем данную операцию, извлекая 2 верхних операнда из стека.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для подсчета значения выражения, записанного в постфиксной нотации,
нужно считывать выражение слева направо и, при получении на вход знака операции,
произвести  операции с предыдущими  двумя полученными операндами.
Именно это мы делаем в решении, используя стек: разбираем выражение слева направо,
полученные числа кладем в стек, при получении знака операции достаем из стека предыдущие 2 числа,
выполняем арифметическую операцию и результат кладем в стек

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность O(n),  где  n- это длина входной строки

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Если n - длина входной строки, то требуется   O(n)  памяти, так как она нужна только для хранения самих элементов стека.

--ID успешной посылки--
https://contest.yandex.ru/contest/22781/run-report/114174110/
     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final Stack<Integer> stack = new Stack<>();
            final List<String> strings = readList(reader);

            for (String string : strings) {
                switch (string) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        final Integer subtrahend = stack.pop();
                        final Integer minuend = stack.pop();
                        stack.push(minuend - subtrahend);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        final Integer divider = stack.pop();
                        final Integer dividend = stack.pop();
                        stack.push(Math.floorDiv(dividend, divider));
                        break;
                    default:
                        stack.push(Integer.parseInt(string));
                        break;
                }
            }
            System.out.println(stack.peek());
        }
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
    }
}