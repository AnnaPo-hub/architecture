package sprint4.ffinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int requestQuantity = Integer.parseInt(reader.readLine());
            for (int i = 0; i < requestQuantity; i++) {
                final String[] command = reader.readLine().trim().split(" ");

                switch (command[0]) {
                    case "get":
                        break;
                    case "put":
                        break;
                    case "delete":
                        break;
                }
            }
        }
    }
}
