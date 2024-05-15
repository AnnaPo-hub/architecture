package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

public class StackMax {
    final Stack<Integer> stack;
    Integer max;

    public StackMax() {
        stack = new Stack<>();
        max = null;
    }

    public void push(int x) {
        stack.push(x);
        if (max == null || x > max) {
            max = x;
        }
    }

    public void pop() {
        try {
            final Integer peek = stack.peek();
            if (peek.equals(max)&&stack.size()==1){
                max=null;
                stack.pop();

            }
             else if (peek.equals(max)&&stack.size()!=1) {
                stack.pop();
                max = find_max();
            }

        } catch (EmptyStackException ex) {
            System.out.println("error");
        }
    }

    public Integer find_max() {
        final Stack<Integer> cloneStack = (Stack<Integer>) stack.clone();
        int newMax = -100000000;

        while (!cloneStack.isEmpty()){
            final Integer pop = cloneStack.pop();
            if (pop> newMax){
                newMax = pop;
            }
        }
        return newMax;
    }

    public Integer get_max() {
        return stack.isEmpty() ? null : max;
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
