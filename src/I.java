import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int input = Integer.parseInt(reader.readLine());
            String result = "False";

            if (input == 1 || input == 4 || Math.sqrt(input) % 4 == 0) {
                result = "True";
            }
            System.out.println(result);
        }
    }
}
