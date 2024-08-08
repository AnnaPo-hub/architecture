import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int input = Integer.parseInt(reader.readLine());
// 2,3,5,7,9
            // StringBuilder result = new StringBuilder();
            int val = 2;
            while (input % val == 0) {
                input = input / val;
                System.out.print(val);
                System.out.print(" ");
            }

            int maxvalue = input / 2;

            for (int i = 1; i <= maxvalue; ++i) {
                val = i * 2 + 1;
                while (input % val == 0) {
                    input = input / val;
                    System.out.print(val);
                    System.out.print(" ");


                    // result.append(val + " ");
                }
                if (input == 1) break;
            }
            // System.out.println(result.toString().trim());
        }
    }
}


