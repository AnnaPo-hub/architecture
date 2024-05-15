package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

public class StackMax {
    final Stack<Integer> stack;

    public StackMax() {
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public void pop() {
        try {
            stack.pop();
        } catch (EmptyStackException ex) {
            System.out.println("error");
        }
    }

    public Integer get_max() {

        Integer max = -10000000;
        try {
            while (true) {
                if (stack.size() == 1) {
                    return stack.peek();
                } else {
                    final Integer peek = stack.peek();
                    if (peek > max) {
                        max = peek;
                    }
                }
            }
        } catch (EmptyStackException ex) {
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            final StackMax stackMax = new StackMax();
            final int commandQuantity = Integer.parseInt(reader.readLine());

            for (int i = 0; i < commandQuantity; i++) {
                final String[] command = reader.readLine().trim().split(" ");
                switch (command[0]) {
                    case "push":
                        stackMax.push(Integer.parseInt(command[1]));
                        break;
                    case "pop":
                        stackMax.pop();
                        break;
                    case "get_max":
                        final Integer max = stackMax.get_max();
                        System.out.println(max == null ? "None" : max);
                        break;
                }
            }
        }
    }
}
